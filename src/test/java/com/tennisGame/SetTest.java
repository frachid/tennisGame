package com.tennisGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

	private static final String SCORE_INITIAL = "(0-0)";
	private static final String QUATRE_DEUX = "(4-2)";
	private static final String QUATRE_SIX = "(4-6)";
	private static final Object EXCEPTION_1_MSG = "java.lang.IllegalStateException: Only finished games could be added to set";
	private static final String EXCEPTION_2_MSG = "java.lang.IllegalStateException: joueur 1 already won the set";
	private Player player1 ;
	private Player player2 ;
	private List<AbstractGame> listGame;
	private Set set ;
	
	
	@Before
	public void setUp() {
		player1 = new Player("joueur 1");
		player2 = new Player("joueur 2");
		listGame = new ArrayList<AbstractGame>();
		set = new Set(player1, player2, listGame);
		
	  }
	

	@Test
	  public void generalTest() {
	    assertEquals(set.toString(), SCORE_INITIAL );
	    Game gameWonBy1 = new Game(player1, player2);
	    Game gameWonBy2 = new Game(player1, player2);
	    gameWonBy1.setWinner(player1);
	    gameWonBy2.setWinner(player2);
	    set.add(gameWonBy1);
	    set.add(gameWonBy2);
	    set.add(gameWonBy1);
	    set.add(gameWonBy2);
	    set.add(gameWonBy1);
	    set.add(gameWonBy1);
	    assertEquals(set.toString(), QUATRE_DEUX );
	    assertNull(set.getWinner());
	    while(set.getWinner()==null){
	    	set.pointTo(player2);
	    }
	    assertEquals(set.toString(), QUATRE_SIX );
	  }

	@Test
	  public void exceptionTest1() {
	    Game gameOngoing = new Game(player1, player2);
		boolean exceptionThrown = false;
		try{
		    set.add(gameOngoing);
		}catch(Exception e){
			assertEquals(e.toString(), EXCEPTION_1_MSG );
    		exceptionThrown=true;
		}
	    assertTrue(exceptionThrown);
	  }

	@Test
	  public void exceptionTest2() {
		boolean exceptionThrown = false;
	    Game gameWonBy1 = new Game(player1, player2);
	    gameWonBy1.setWinner(player1);

	    for(int i=0;i<TennisRule.MINIMUM_GAMES_TO_WIN_SET;i++){
		    set.add(gameWonBy1);
	    }
	    try{
		    set.add(gameWonBy1);
		}catch(Exception e){
			assertEquals(e.toString(), EXCEPTION_2_MSG );
    		exceptionThrown=true;
		}
	    assertTrue(exceptionThrown);
	  }
	
	@Test
	  public void exceptionTest3() {
		boolean exceptionThrown = false;
	    Game gameWonBy1 = new Game(player1, player2);
	    gameWonBy1.setWinner(player1);

	    for(int i=0;i<TennisRule.MINIMUM_GAMES_TO_WIN_SET;i++){
		    set.add(gameWonBy1);
	    }
	    try{
		    set.pointTo(player1);
		}catch(Exception e){
			assertEquals(e.toString(), EXCEPTION_2_MSG );
			exceptionThrown=true;
		}
	    assertTrue(exceptionThrown);
	  }
}
