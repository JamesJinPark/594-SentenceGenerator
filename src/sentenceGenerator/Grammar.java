package sentenceGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

/**
 * Represents a BNF grammar.
 * @author James Park
 */
/**
 * @author James
 *
 */
public class Grammar {
	private Map<String, ListOfDefinitions> grammar; // rules for all the
													// nonterminals

	/**
	 * Constructs a new, empty grammar.
	 */
	public Grammar() {
		grammar = new TreeMap<>();
	}

	public Map<String, ListOfDefinitions> getMap() {
		return grammar;
	}

	/**
	 * Uses the supplied BufferedReader to read and parse a file containing a
	 * BNF grammar. Each grammar rule must be on a single line, but may have
	 * multiple alternatives. The same nonterminal may be defined in multiple
	 * rules. Example:
	 * 
	 * <pre>
	 * &lt;np&gt; ::= &lt;det&gt; &lt;n&gt; | &lt;det&gt; &lt;adjs&gt; &lt;n&gt;
	 * &lt;np&gt; ::= &lt;det&gt; &lt;n&gt; &lt;pp&gt;
	 * </pre>
	 * 
	 * @throws IOException
	 *             If there is an error reading the file.
	 */
	public Grammar(BufferedReader reader) throws IOException {
		grammar = new TreeMap<>();
		try {
			String readLine = reader.readLine();
			while (readLine != null) {
				addRule(readLine);
				readLine = reader.readLine();
			}
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	/**
	 * Adds definitions for a single nonterminal to this grammar. The input text
	 * should be in the form:
	 * <ul>
	 * <li>A single nonterminal (the thing being defined),</li>
	 * <li>The symbol "::=", and</li>
	 * <li>A list of zero or more definitions, separated by the "|" symbol.</li>
	 * </ul>
	 * 
	 * @param ruleText
	 *            The text to be parsed and kept as definitions.
	 * @throws IllegalArgumentException
	 *             If the input parameter has a syntax error.
	 */
	public void addRule(String ruleText) throws IllegalArgumentException {
        BnfTokenizer tokenizer = new BnfTokenizer(ruleText);
        try {     
        	String key = tokenizer.nextToken(); 
        	if (!isNonterminal(key)) syntaxError(key);	
        	SingleDefinition ruleVal = new SingleDefinition();
        	if (!("EOF").equals(key)){
        		String throwAway = tokenizer.nextToken();
        		if (!"::=".equals(throwAway)) syntaxError(throwAway);
        		String value = tokenizer.nextToken();
        		while(!("EOF").equals(value)) {
        			if(!("|").equals(value)){
        				ruleVal.add(value);
        				
        			}
//        			else if(){
//        				
//        			}
        			else{
        				addToGrammar(key, ruleVal);
        				ruleVal = new SingleDefinition();
        			}
        			//if("EOF".equals(value)) break;
        			value = tokenizer.nextToken();
        		}
        		addToGrammar(key, ruleVal);
        	}
       	}
        catch (IllegalArgumentException e) { 
        	throw new Error(e); 
        } 
    }

	/**
	 * Adds a single definition to this <code>Grammar</code>. If the nonterminal
	 * has already been defined, the new definition is appended to the existing
	 * definitions.
	 * 
	 * @param lhs
	 *            The nonterminal being defined.
	 * @param singleDefinition
	 *            The new definition.
	 */
	private void addToGrammar(String lhs, SingleDefinition singleDefinition) {
		ListOfDefinitions tempList = new ListOfDefinitions();
		if (grammar.containsKey(lhs)) {
			tempList = grammar.get(lhs);
		}
		tempList.add(singleDefinition);
		grammar.put(lhs, tempList);
	}

	/**
	 * Throws an <code>IllegalArgumentException</code>, with the input parameter
	 * as part of the exception message.
	 * 
	 * @param rule
	 *            The text to be included in the exception.
	 * @throws IllegalArgumentException
	 *             To indicate a syntax error.
	 */
	private void syntaxError(String rule) {
		throw new IllegalArgumentException("Syntax error in rule: " + rule);
	}

	/**
	 * Returns a list of definitions for the given nonterminal.
	 * 
	 * @param nonterminal
	 *            The nonterminal whose definitions are to be returned.
	 * @return The definitions of the given nonterminal.
	 */
	public ListOfDefinitions getDefinitions(String nonterminal) {
		return grammar.get(nonterminal);
	}

	/**
	 * Prints this Grammar.
	 */
	public void print() {
		Set<String> set = grammar.keySet();
		for (String item : set) {
			System.out.print(item + " ::= ");
			for(int i = 0; i < getDefinitions(item).size(); i++){
				System.out.print(getDefinitions(item).get(i));
				if(i < (getDefinitions(item).size() - 1)) System.out.print(" | ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * @return the key set of a grammar treemap
	 */
	public Set<String> getKeySet() {
		return grammar.keySet();
	}

	/**
	 * Returns <code>true</code> if the given string is a nonterminal, as
	 * indicated by an initial <code>'&lt;'</code>.
	 * 
	 * @param s
	 *            The token to be tested.
	 * @return <code>true</code> if <code>s</code> is a nonterminal.
	 */
	public boolean isNonterminal(String s) {
		return s.startsWith("<");
	}
}