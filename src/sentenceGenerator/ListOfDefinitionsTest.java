package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author James Park
 */
public class ListOfDefinitionsTest {

	SingleDefinition nouns = new SingleDefinition();
	SingleDefinition verbs = new SingleDefinition();
	ListOfDefinitions listOfDef = new ListOfDefinitions();
	
    /**
     * Test method for {@link sentenceGenerator.ListOfDefinitions#toString()}.
     */
    @Test
    public final void testToString() {
    	nouns.add("boy");
    	nouns.add("girl");
    	nouns.add("man");
    	nouns.add("woman");
    	nouns.add("dog");
    	verbs.add("ran");
    	verbs.add("swam");
    	verbs.add("biked");
    	verbs.add("walked");
    	verbs.add("laughed");
        listOfDef.add(nouns);
        listOfDef.add(verbs);
        Object o = "boy girl man woman dog ran swam biked walked laughed";
        Object f = "This should fail the test";
        assertTrue(listOfDef.toString().equals(o.toString()));

        assertFalse(listOfDef.toString() == (o.toString()));
        assertFalse(listOfDef.toString().equals(null));
        assertFalse(listOfDef.toString().equals(f.toString()));
    }
}
