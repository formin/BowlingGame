 
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class BowlingGameTest {
	
	BowlingGame BG;
	 
	@Before
    public void setup() {
		BG = new BowlingGame();
    }
	
	/*
	 * 1구에 입력되는 사용자문자를 숫자점수로 리턴한다. START->
	 */
	@Test
	public void play_odd1() {
		int result = BG.play_odd("x"); 
		assertEquals(10,result);
	}

	@Test
	public void play_odd2() {
		int result = BG.play_odd("f"); 
		assertEquals(0,result);
	}

	@Test
	public void play_odd3() {
		int result = BG.play_odd("g"); 
		assertEquals(0,result);
	}

	@Test
	public void play_odd4() {
		int result = BG.play_odd("5"); 
		assertEquals(5,result);
	} 

	/*
	 * 2구에 입력되는 사용자문자를 숫자점수로 리턴한다. START->
	 */
	@Test
	public void play_even1() {
		int result = BG.play_even("/",2); 
		assertEquals(8,result);
	}

	@Test
	public void play_even2() {
		int result = BG.play_even("-",2); 
		assertEquals(0,result);
	}

	@Test
	public void play_even3() {
		int result = BG.play_even("3",2); 
		assertEquals(3,result);
	}
	 
	
}
