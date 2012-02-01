package project.leandog.colorcombination;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnlockerInputTest {

	private final UnlockerInput input = new UnlockerInput();
	
	@Test(expected=RuntimeException.class) 
	public void noInputThrowsException() {
		input.parse("");
	}
	
	@Test public void parsesStartAndEndColors() {
		input.parse("blue, green");
		assertEquals("blue", input.firstColor());
		assertEquals("green", input.lastColor());
	}
	
	@Test public void singleLineNotValidInput() {
		input.parse("blue, green");
		assertFalse("Expected input to invalid", input.isValid());
	}
	
	@Test public void parseMultipleLinesIsValid() {
		input.parse("blue, green" +
			 "\n" + "blue, green"
		);
		assertTrue("Expected input to be valid", input.isValid());
	}
}
