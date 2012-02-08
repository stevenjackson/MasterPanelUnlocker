package project.leandog.colorcombination;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnlockerInputTest {
	
	@Test(expected=RuntimeException.class) 
	public void noInputThrowsException() {
		UnlockerInputParser.parse("");
	}
	
	@Test public void parsesStartAndEndColors() {
		UnlockerInput input = UnlockerInputParser.parse("blue, green");
		assertEquals("blue", input.firstColor());
		assertEquals("green", input.lastColor());
	}
	
	@Test public void singleLineNotValidInput() {
		UnlockerInput input = UnlockerInputParser.parse("blue, green");
		assertFalse("Expected input to invalid", input.isValid());
	}
	
	@Test public void parseMultipleLinesIsValid() {
		UnlockerInput input = UnlockerInputParser.parse("blue, green" +
			 "\n" + "blue, green"
		);
		assertTrue("Expected input to be valid", input.isValid());
	}
}
