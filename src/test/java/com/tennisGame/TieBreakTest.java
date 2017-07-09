package com.tennisGame;

import static org.junit.Assert.*;

import org.junit.*;


public class TieBreakTest {

	private static final String A_ZERO = "Current tie break status : 0-0";
	private static final String EXCEPTION_MSG = "java.lang.IllegalStateException: joueur 1 already won the game";
	private static final String DEUX_A_DEUX = "Current tie break status : 2-2";
	private static final String SEPT_A_TROIS = "Current tie break status : 7-3";
	private Player player1 ;
	private Player player2 ;
	private TieBreak tieBreak;
	
	@Before
	public void setUp() {
		player1 = new Player("joueur 1");
		player2 = new Player("joueur 2");
		tieBreak = new TieBreak(player1, player2);
	  }

	@Test
	  public void generalTest() {
			
	    assertEquals(tieBreak.toString(), A_ZERO );
	    tieBreak.pointTo(player1);
	    assertNull(tieBreak.getWinner());
	    tieBreak.pointTo(player1);
	    tieBreak.pointTo(player2);
	    tieBreak.pointTo(player2);
	    assertEquals(tieBreak.toString(), DEUX_A_DEUX );
	    assertNull(tieBreak.getWinner());
	    tieBreak.pointTo(player1);
	    tieBreak.pointTo(player1);
	    tieBreak.pointTo(player1);
	    tieBreak.pointTo(player1);
	    tieBreak.pointTo(player2);
	    tieBreak.pointTo(player1);
	    assertNotNull(tieBreak.getWinner());
	    assertEquals(tieBreak.getWinner(), player1 );
	    assertEquals(tieBreak.toString(), SEPT_A_TROIS );
	    
	    
	  }
	
	
	@Test
	  public void exceptionTest() {
		boolean exceptionThrown = false;
			
	    for(int i=0;i<TennisRule.MINIMUM_TO_WIN_TIE_BREAK+1;i++){

	    	try{
	    		tieBreak.pointTo(player1);
	    	} catch(Exception e){
	    	    assertEquals(e.toString(), EXCEPTION_MSG );
	    		exceptionThrown=true;
	    	}
	    }

	    assertTrue(exceptionThrown);
	    
	    
	  }
	
}
