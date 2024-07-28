package Mohammed_AlMashdli_22670310196_mnx;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BooleReplica_Mohammed_AlMashdli_22670310196 {
    String functionName;
    String[] variables;
    String[] terms;
    int[][] truthTable;

    public BooleReplica_Mohammed_AlMashdli_22670310196() {
        String filePath = "C:\\Users\\Mohammed AL-Mashdli\\eclipse\\mantiksal\\src\\mantiksal\\bool.txt";

        TreeSet<String> varSet = new TreeSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            functionName = line.split(" = ")[0];
            String function = line.split(" = ")[1].replaceAll("[^a-zA-Z]", "");

            for (int i = 0; i < function.length(); i++) {
                    varSet.add("" + function.charAt(i));
               
            }

            this.terms = line.split(" = ")[1].split(" \\+ ");
            this.variables = new String[4];
            varSet.toArray(this.variables);

        } catch (IOException e) {
            e.printStackTrace();
        }
   
         System.out.println(String.join(" ", variables)+" "+functionName);

        generateTruthTable();
        
    }
    public void foncsyon(int i,int[]row,Map<String,ArrayList> ex){
    
    	if(or(row)) {
    		ex.get("Terms").add(banrytominterms(row));
    		ex.get("shortmentioners").add(""+i);
    	}else {
    		ex.get("maxterms").add(banrytomaxterms(row));
    		ex.get("shortmaxterms").add(""+i);
    	}

    	
    }
    public String banrytomaxterms(int[] row) {
    	String maxterm = "";
    	for (int i = 0; i < row.length-1; i++) {
			if(row[i]==0) {
				maxterm +="+" + variables[i];
				
			}else {
				maxterm +="+" + variables[i]+"'";
				
			}
		}
    	return "("+maxterm.replaceFirst("\\+", "")+")";
    }
    
    public String banrytominterms(int[] row) {
    	String minterims = "";
    	for (int i = 0; i < row.length-1; i++) {
			if(row[i]==1) {
				minterims +=variables[i];
				
			}else {
				minterims +=variables[i]+"'";
				
			}
		}
    	return minterims;
    }


    public int letterIndex(String letter) {
        for (int i = 0; i < this.variables.length; i++) {
            if (letter.equals(this.variables[i]))
                return i;
        }
        return 0;
    }
    

    public void generateTruthTable() {
        this.truthTable = new int[][]{
        	{0, 0, 0, 0, 0},
        	{0, 0, 0, 1, 0},
        	{0, 0, 1, 0, 0},
        	{0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0}, 
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 1, 0}};
            
        	Map<String,ArrayList> map = new TreeMap<String,ArrayList>();
        	map.put("Terms", new ArrayList());
        	map.put("shortmentioners", new ArrayList());
        	map.put("maxterms", new ArrayList());
        	map.put("shortmaxterms", new ArrayList());

        for (int i = 0; i < this.truthTable.length; i++) {
            this.truthTable[i][4] = evaluate(this.truthTable[i]);
            this.foncsyon(i, this.truthTable[i],map);
            
            String line = Arrays.stream(this.truthTable[i])
          		  .mapToObj(String::valueOf)
          		  .collect(Collectors.joining(String.valueOf(' ')));
            
            System.out.println(line);
        }
        

        System.out.println(functionName+"="+String.join ("+",map.get("Terms")));
        System.out.println(functionName+ "=" + "Σ(" +String.join (",",map.get("shortmentioners"))+")");
        System.out.println(functionName+ "=" + String.join(".",map.get("maxterms")));
        System.out.println(functionName+ "=" + "∏(" + String.join(",",map.get("shortmaxterms"))+")");
        
    }    
    public boolean and (String term, int []row) {
    	
    	boolean and = true;
        for (int j = 0; j < term.length(); j++) {
            char c = term.charAt(j);
            if (j + 1 < term.length() && !Character.isLetter(term.charAt(j + 1))) {
                and &= row[letterIndex("" + c)] == 1 ? false : true;
                j++;  
            } else {
                and &= row[letterIndex("" + c)] == 1 ? true : false;
            }
        }
    	
    	return and;
    }
    public boolean or(int [] row) {
    	boolean or = false;
        for (String term : this.terms) {
            
            or |= and(term,row);
        }
    	
    	return or;
    }

    public int evaluate(int[] row) {
        
        return or(row) ? 1 : 0;
    }
    
    	    
    public static void main(String[] args) {
    
    	new BooleReplica_Mohammed_AlMashdli_22670310196();
    }
}

