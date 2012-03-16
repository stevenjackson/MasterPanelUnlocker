package project.leandog.colorcombination;

import static org.junit.Assert.assertEquals;

public class UnlockerTestUtil {

	public static void assertUnlockFailed(Unlocker unlocker){
		assertEquals(Unlocker.UNLOCK_FAILED_MESSAGE, unlocker.getResultAsString());
	}
	
	public static UnlockerInput buildInput(String... colors) {
		return UnlockerInputParser.parse(buildPairs(colors));
	}

	public static String buildPairs(String... colors) {
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

}
