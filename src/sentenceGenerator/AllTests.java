package sentenceGenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BnfTokenizerTest.class, GrammarTest.class,
		ListOfDefinitionsTest.class, SingleDefinitionTest.class })
public class AllTests {

}
