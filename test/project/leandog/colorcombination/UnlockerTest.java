package project.leandog.colorcombination;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnlockerTest {

	private final Unlocker unlocker = new Unlocker();
	
	
	@Test public void unlockerFailsWithoutInput(){
		unlocker.unlock(new UnlockerInput());
		assertUnlockFailed(unlocker);
	}
	
	@Test public void testInvalidInputFails() {
		UnlockerInput input = buildInput("red", "green");
		unlocker.unlock(input);
		assertUnlockFailed(unlocker);
	}
	
	@Test public void testSingleChipPasses() {
		UnlockerInput input = buildInput("red", "green", "red", "green");
		String expected = buildPairs("red", "green");
		
		unlocker.unlock(input);
		assertEquals(expected, unlocker.getResult());
	}
	
	
	@Test public void testSingleReversedChipPasses() {
		UnlockerInput input = buildInput("red", "green", "green", "red");
		String expected = buildPairs("red", "green");
		
		unlocker.unlock(input);
		assertEquals(expected, unlocker.getResult());
	}
	
	@Test public void testTwoChips(){
		UnlockerInput input = buildInput("red", "green", "green", "red", "red", "red");
		String expected = buildPairs("red", "red", "red", "green");
		
		unlocker.unlock(input);
		assertEquals(expected, unlocker.getResult());
	}
	
	@Test public void exampleOne(){
		UnlockerInput input = buildInput(
				"blue", "green", 
				"blue", "yellow",
				"red", "orange",
				"red", "green",
				"yellow", "red",
				"orange", "purple");
		
		unlocker.unlock(input);
		assertUnlockFailed(unlocker);
	}
	
	@Test public void exampleTwo() {
		UnlockerInput input = buildInput(
				"blue", "green",
				"blue", "yellow",
				"orange", "red",
				"red", "green",
				"yellow", "red",
				"orange", "red");

		String expected = buildPairs(
				"blue", "yellow",
				"yellow", "red",
				"red", "orange",
				"orange", "red",
				"red", "green");
		
		unlocker.unlock(input);
		assertEquals(expected, unlocker.getResult());
	}
	
	@Test public void example3(){
		UnlockerInput input = buildInput(
				"blue", "red",
				"red", "blue",
				"orange", "red",
				"green", "green",
				"orange", "yellow",
				"red", "yellow");
		unlocker.unlock(input);
		assertUnlockFailed(unlocker);
	}
	
	private UnlockerInput buildInput(String... colors) {
		UnlockerInput input = new UnlockerInput();
		input.parse(buildPairs(colors));
		return input;
	}

	private String buildPairs(String... colors) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < colors.length; i++){
			sb.append(colors[i]);
			//figure out separator by position
			if(i % 2 == 0){
				sb.append(",");
			} else {
				sb.append("\n");
			}
		}		
		return sb.toString().trim();
	}

	private void assertUnlockFailed(Unlocker unlocker){
		assertEquals(Unlocker.UNLOCK_FAILED_MESSAGE, unlocker.getResult());
	}
}
