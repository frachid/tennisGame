
package com.tennisGame;

import static org.junit.Assert.*;

import org.junit.*;


public class GameTest {

	private static final String TRENTA = "Current game status : 30-30";
	private static final String QUARANTE_TRENTE = "Current game status : 40-30";
	private static final String A_ZERO = "Current game status : 0-0";
	private static final String EGALITE = "Current game status : deuce";
	private static final String AVANTAGE = "Current game status : advantage";
	private static final String EXCEPTION_MSG = "java.lang.IllegalStateException: joueur 1 already won the game";
	private Player player1 ;
	private Player player2 ;
	private Game game;
	
	@Before
	public void setUp() {
		player1 = new Player("joueur 1");
		player2 = new Player("joueur 2");
		game = new Game(player1, player2);
	  }

	@Test
	  public void generalTest() {
			
	    assertEquals(game.toString(), A_ZERO );
	    game.pointTo(player1);
	    assertNull(game.getWinner());
	    game.pointTo(player1);
	    game.pointTo(player2);
	    game.pointTo(player2);
	    assertEquals(game.toString(), TRENTA );
	    assertNull(game.getWinner());
	    game.pointTo(player1);
	    assertEquals(game.toString(), QUARANTE_TRENTE );
	    assertNull(game.getWinner());
	    game.pointTo(player1);
	    assertNotNull(game.getWinner());
	    assertEquals(game.getWinner(), player1 );
	    
	    
	  }
	
	@Test
	  public void avantageEgaliteTest() {

	    assertEquals(game.toString(), A_ZERO );
	    game.pointTo(player1);
	    game.pointTo(player1);
	    game.pointTo(player2);
	    game.pointTo(player2);
	    assertEquals(game.toString(), TRENTA );
	    assertNull(game.getWinner());
	    game.pointTo(player1);
	    assertEquals(game.toString(), QUARANTE_TRENTE );
	    assertNull(game.getWinner());
	    game.pointTo(player2);
	    assertEquals(game.toString(), EGALITE );
	    assertNull(game.getWinner());
	    game.pointTo(player2);
	    assertEquals(game.toString(), AVANTAGE );
	    assertNull(game.getWinner());
	    game.pointTo(player2);
	    assertNotNull(game.getWinner());
	    assertEquals(game.getWinner(), player2 );
	  }
	
	@Test
	  public void exceptionTest() {
		boolean exceptionThrown = false;
			
	    for(int i=0;i<TennisRule.MINIMUM_TO_WIN_GAME+1;i++){

	    	try{
			    game.pointTo(player1);
	    	} catch(Exception e){
	    	    assertEquals(e.toString(), EXCEPTION_MSG );
	    		exceptionThrown=true;
	    	}
	    }

	    assertTrue(exceptionThrown);
	    
	    
	  }
	
}
