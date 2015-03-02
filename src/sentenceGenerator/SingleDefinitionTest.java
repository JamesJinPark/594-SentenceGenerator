package sentenceGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author <your name goes here>
 */
public class SingleDefinitionTest {

	SingleDefinition nouns = new SingleDefinition();
    
    /**
     * Test method for {@link sentenceGenerator.SingleDefinition#toString()}.
     */
    @Test
    public final void testToString() {
        nouns.add("boy");
        nouns.add("girl");
        nouns.add("man");
        nouns.add("woman");
        nouns.add("dog");
        Object o = "boy girl man woman dog";
        Object f = "This is clearly wrong";
        assertTrue(nouns.toString().equals(o.toString()));
        assertFalse(nouns.toString().equals(f.toString()));
        assertFalse(nouns.toString() == o.toString());
    }
}
