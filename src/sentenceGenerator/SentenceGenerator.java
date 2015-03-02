package sentenceGenerator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;


/**
 * This is my version of a CIT594 assignment to read in a BNF grammar
 * and produce sentences from that grammar.
 */
public class SentenceGenerator {
    private Grammar grammar;
    private Random random = new Random();

    /**
     * Prompts the user for a file containing a BNF grammar, then
     * generates several sentences from that grammar.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {
        try {
            new SentenceGenerator().run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Does the work of this class.
     * 
     * @throws IOException If an input exception occurs.
     */
    private void run() throws IOException {    
    	try {
    		BufferedReader openFile = getFileReader();
    		grammar = new Grammar(openFile);
    		//after reading in grammar
            grammar.print();
            //before printing out sentences
            System.out.print("\n");
            for(int i = 1; i < 21; i++){
            	System.out.printf("%d. ", i);
            	printAsSentence(generate("<sentence>"));
            }
    	}
    	catch (IOException e) {
        	throw new Error(e);
        }
    	catch(NullPointerException n){
    		System.out.print("No input file chosen!");
    	}
    }
    
    /**
     * Expands the given term into a list of terminals. If the given
     * term is already a terminal, a list containing this single term
     * is returned.
     * 
     * @param term A terminal or nonterminal to expand into a list.
     * @return A list of terminals.
     */
    List<String> generate(String term) {
    	List<String> result = new ArrayList<String>();
        if (!grammar.isNonterminal(term)){
        	result.add(term);
        }
        else {
        	ListOfDefinitions tempList = grammar.getDefinitions(term);
        	SingleDefinition singleList= (SingleDefinition)chooseRandomElement(tempList);        	
        	for (String item : singleList){
        		result.addAll(generate(item));
        	}
        }
        return result;
    }

    /**
     * Randomly choose and return one element from a list.
     * 
     * @param list The list from which the selection is to be made.
     * @return The randomly selected element.
     */
    private Object chooseRandomElement(List<?> list) throws NullPointerException{
        try{
        	return (list.get(random.nextInt(list.size())));
        }
        catch (NullPointerException e){
        	String temp = "NULL";
        	SingleDefinition tempList = new SingleDefinition();
        	tempList.add(temp);
        	return(tempList);
        }
        
    }

    /**
     * Prints the given list of words as a sentence. The first word is
     * capitalized, and a period is printed at the end.
     * 
     * @param list The words to be printed.
     */
    void printAsSentence(List<String> list) {
    	System.out.print(list.get(0).substring(0, 1).toUpperCase() + list.get(0).substring(1));
        for (int i = 1; i < list.size(); i++){
        	System.out.print(" ");
        	System.out.print(list.get(i));
        }
    	System.out.print(".");
    	System.out.print("\n");
    }
    
    /**
     * Prompts the user to choose a file, which should contain a BNF grammar.
     * 
     * @return The chosen file, or <code>null</code> if none is chosen.
     */
    private BufferedReader getFileReader() {
    	BufferedReader reader = null;
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.showOpenDialog(null);
        FileReader fileRead;
		try {
			fileRead = new FileReader(fileOpen.getSelectedFile().getPath());
			reader = new BufferedReader(fileRead);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return reader;
    }
}