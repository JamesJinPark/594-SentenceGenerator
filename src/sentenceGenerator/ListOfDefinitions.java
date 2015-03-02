package sentenceGenerator;
import java.util.ArrayList;

/**
 * A <code>ListOfDefinitions</code> object consists of a list of alternatives
 * (each of which is a list of terminals and/or nonterminals), but
 * does not include the thing being defined.
 * 
 * @author James Park
 */
public class ListOfDefinitions extends ArrayList<SingleDefinition> {
	
    /**
     * Constructs an empty list of definitions.
     */
    ListOfDefinitions() {}
    
    /**
     * Returns a string containing the contents of this <code>ArrayList</code>,
     * separated by <code>" | "</code> symbols.
     * 
     * @see java.util.AbstractCollection#toString()
     */
    @Override
    public String toString() {
    	String result = "";
    	for (int i = 0; i < this.size(); i++){
    		for (int j = 0; j < this.get(i).size(); j++){
    			if (i == 0 && j == 0){
    				result = result + this.get(i).get(j);
    			}
    			else {
    				result = result + " " + this.get(i).get(j);
    			}
    		}
        }
        return result;
    }
}
