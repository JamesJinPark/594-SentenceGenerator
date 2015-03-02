package sentenceGenerator;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

import org.junit.Test;

/**
 * @author James Park
 */
public class GrammarTest {

	Grammar g = new Grammar();
	
	SingleDefinition testString1 = new SingleDefinition();
	SingleDefinition testString2 = new SingleDefinition();
	SingleDefinition testString3 = new SingleDefinition();

	ListOfDefinitions testList1 = new ListOfDefinitions();
	ListOfDefinitions testList2 = new ListOfDefinitions();
	ListOfDefinitions testList3 = new ListOfDefinitions();
	
    /**
     * Test method for {@link sentenceGenerator.Grammar#addRule(java.lang.String)}.
     */
    @Test
    public final void testAddRule() {
    	g.addRule("<conjecture> ::= and | but | or");
    	
    	testString1.add("and");
    	testString2.add("but");
    	testString3.add("or");
    	
    	testList1.add(testString1);
    	testList1.add(testString2);
    	testList1.add(testString3);
    	
    	testList2 = g.getMap().get("<conjecture>");
    	
    	assertTrue(testList1.get(0).get(0).equals(testList2.get(0).get(0)));
    	assertTrue(testList1.get(1).get(0).equals(testList2.get(1).get(0)));
    	assertTrue(testList1.get(2).get(0).equals(testList2.get(2).get(0)));
    	
    	assertFalse(testList1.get(2).get(0).equals(testList2.get(1).get(0)));
    }

    /**
     * @throws java.lang.Error
     */
    @Test(expected=java.lang.Error.class)
    public final void testAddRuleIllegalArgumentException(){
    	g.addRule("fail ::= this | should | fail");
    	g.addRule("pizza>");
    	g.addRule("");
    }
    
    /**
     * Test method for {@link sentenceGenerator.Grammar#getDefinitions(java.lang.String)}.
     */
    @Test
    public final void testGetDefinitions() {
    	g.addRule("<noun> ::= boy | girl | man");
    	g.addRule("<verb> ::= runs | swims | bikes");
    	testList1 = g.getDefinitions("<noun>");
    	testList2 = g.getDefinitions("<verb>");
    	testString1.add("boy");
    	testString1.add("girl");
    	testString1.add("man");
    	testString2.add("runs");
    	testString2.add("swims");
    	testString2.add("bikes");
    	testList3.add(testString1);
    	testList3.add(testString2);
    	assertTrue(testList3.get(0).get(0).equals(testList1.get(0).get(0)));
    	assertTrue(testList3.get(0).get(1).equals(testList1.get(1).get(0)));
    	assertTrue(testList3.get(0).get(2).equals(testList1.get(2).get(0)));
    	assertTrue(testList3.get(1).get(0).equals(testList2.get(0).get(0)));
    	assertTrue(testList3.get(1).get(1).equals(testList2.get(1).get(0)));
    	assertTrue(testList3.get(1).get(2).equals(testList2.get(2).get(0)));
    
    	assertFalse(testList3.get(1).get(1).equals(testList1.get(0).get(0)));
    	assertFalse(testList3.get(1).get(2).equals(testList1.get(2).get(0)));
    	assertFalse(testList3.get(1).get(2) == (testList2.get(2).get(0)));
    }

    /**
     * Test method for {@link sentenceGenerator.Grammar#isNonterminal(java.lang.String)}.
     */
    @Test
    public final void testIsNonterminal(){
    	String string1 = "<true>";
    	String string2 = "<good>";
    	String string3 = "fail";
    	String string4 = "not good";
    	assertTrue(g.isNonterminal(string1) == true);
    	assertTrue(g.isNonterminal(string2) == true);
    	assertTrue(g.isNonterminal(string3) == false);
    	assertTrue(g.isNonterminal(string4) == false);
    }
    
    /**
     * Test method for {@link sentenceGenerator.Grammar#getKeySet()}.
     */
    @Test
    public final void testGetKeySet(){
    	g.addRule("<conjecture> ::= and | but | or");
    	g.addRule("<noun> ::= boy | girl | man");
    	g.addRule("<verb> ::= runs | swims | bikes");
    	Set<String> tempSet = new HashSet<String>();
    	tempSet.add("<conjecture>");
    	tempSet.add("<noun>");
    	tempSet.add("<verb>");
    	assertTrue(g.getKeySet().equals(tempSet));
    }
}
