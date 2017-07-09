package com.tennisGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MatchTest {

	private Player player1 ;
	private Player player2 ;
	private Match match ;
	private static final String SAUT_A_LA_LIGNE = "\n";
	private static final String FAHD_VS_ROGER = "Player 1 : Fahd Rachid"+SAUT_A_LA_LIGNE
							+"Player 2 : Roger Federer"+SAUT_A_LA_LIGNE
							+"Score : (0-6) (0-6) (0-6) "+SAUT_A_LA_LIGNE
							+"Match Status : Player2 wins";

	private static final Integer[] MATCH_ONE = {10,1,10,5,4,8,4,8,4,5,10,1,10,0,4,1,1,1};
	
	private static final Integer[] MATCH_ONE_PLAYER_ONE = {10,10,4,4,4,10,10,4,1};
	private static final Integer[] MATCH_ONE_PLAYER_TWO = {1 ,5 ,8,8,5,1 ,0 ,1,1};
	
	private static final Integer[] FIRST_2_SETS_PLAYER_ONE = {10,10,4,4,4,10,10};
	private static final Integer[] FIRST_2_SETS_PLAYER_TWO = {1 ,5 ,8,8,5,1 };
	
	private static final Integer[] MATCH_1_PLAYER_ONE_CONTINUE = {4,1};
	private static final Integer[] MATCH_1_PLAYER_TWO_CONTINUE = {1,1};

	private static final Integer[] MATCH_2_PLAYER_ONE_CONTINUE = {0,1,1,1,1,1};
	private static final Integer[] MATCH_2_PLAYER_TWO_CONTINUE = {1,1,1,1,1};
	
	private static final Integer[] MATCH_3_PLAYER_ONE_CONTINUE = {0,1,1,1,1,1,1};
	private static final Integer[] MATCH_3_PLAYER_TWO_CONTINUE = {1,1,1,1,1,0};
	
	private static final Integer[] MATCH_4_PLAYER_ONE_CONTINUE = {0,1,1,1,1,1,2,8,8,5};
	private static final Integer[] MATCH_4_PLAYER_TWO_CONTINUE = {1,1,1,1,1,0,1,2,3};

	private static final Integer[] MATCH_5_SET_3_PLAYER_ONE = {0,4,6};
	private static final Integer[] MATCH_5_SET_3_PLAYER_TWO = {9,15,4};
	private static final Integer[] MATCH_5_SET_4_PLAYER_ONE = {4,4,12,7};
	private static final Integer[] MATCH_5_SET_4_PLAYER_TWO = {12,4,4,5};
	private static final Integer[] MATCH_5_SET_4_PLAYER_ONE_TIE_BREAK = {3,3,1};
	private static final Integer[] MATCH_5_SET_4_PLAYER_TWO_TIE_BREAK = {4,2,3};
	private static final Integer[] MATCH_5_SET_5_PLAYER_ONE = {0,4 ,6,6,3};
	private static final Integer[] MATCH_5_SET_5_PLAYER_TWO = {9,14,1,3,2};
	

	private static final String EXAMPLE_1 = "Player 1 : nom du joueur1"+SAUT_A_LA_LIGNE
			+"Player 2 : nom du joueur2"+SAUT_A_LA_LIGNE
			+"Score : (6-1) (7-5) (1-0) "+SAUT_A_LA_LIGNE
			+"Current game status : 15-30"+SAUT_A_LA_LIGNE
			+"Match Status : in progress";
	
	private static final String EXAMPLE_2 = "Player 1 : nom du joueur1"+SAUT_A_LA_LIGNE
			+"Player 2 : nom du joueur2"+SAUT_A_LA_LIGNE
			+"Score : (6-1) (7-5) (0-0) "+SAUT_A_LA_LIGNE
			+"Current game status : deuce"+SAUT_A_LA_LIGNE
			+"Match Status : in progress";
	
	private static final String EXAMPLE_3 = "Player 1 : nom du joueur1"+SAUT_A_LA_LIGNE
			+"Player 2 : nom du joueur2"+SAUT_A_LA_LIGNE
			+"Score : (6-1) (7-5) (0-0) "+SAUT_A_LA_LIGNE
			+"Current game status : advantage"+SAUT_A_LA_LIGNE
			+"Match Status : in progress";
	
	private static final String EXAMPLE_4 = "Player 1 : nom du joueur1"+SAUT_A_LA_LIGNE
			+"Player 2 : nom du joueur2"+SAUT_A_LA_LIGNE
			+"Score : (6-1) (7-5) (6-0) "+SAUT_A_LA_LIGNE
			+"Match Status : Player1 wins";
	
	private static final String EXAMPLE_5 = "Player 1 : nom du joueur1"+SAUT_A_LA_LIGNE
			+"Player 2 : nom du joueur2"+SAUT_A_LA_LIGNE
			+"Score : (6-1) (7-5) (2-6) (6-7) (4-6) "+SAUT_A_LA_LIGNE
			+"Match Status : Player2 wins";
	private static final Object EXCEPTION_MSG = "java.lang.IllegalStateException: Roger Federer already won the match";
	

	
	@Before
	public void setUp() {
		player1 = new Player("nom du joueur1");
		player2 = new Player("nom du joueur2");
		match = new Match(player1, player2);
	  }
	@Test
	  public void fahdVsRoger() {

		Player fahd = new Player("Fahd Rachid");
		Player roger = new Player("Roger Federer");
		match = new Match(fahd, roger);
		while(match.getWinner()==null){
		    assertNull(match.getWinner());
			match.pointTo(roger);
		}
		assertEquals(match.getWinner(),roger);
	    assertEquals(match.toString(), FAHD_VS_ROGER );
		
	}
	@Test
	  public void fahdVsRogerExceptionTest() {

		Player fahd = new Player("Fahd Rachid");
		Player roger = new Player("Roger Federer");
		match = new Match(fahd, roger);
		while(match.getWinner()==null){
		    assertNull(match.getWinner());
			match.pointTo(roger);
		}
		assertEquals(match.getWinner(),roger);
	    assertEquals(match.toString(), FAHD_VS_ROGER );

		boolean exceptionThrown = false;
		try{
			match.pointTo(roger);
    	} catch(Exception e){
    	    assertEquals(e.toString(), EXCEPTION_MSG );
    		exceptionThrown=true;
    	}
	    assertTrue(exceptionThrown);
		
	}
	
	@Test
	  public void match1() {

		TennisUtils.processMatch(match,MATCH_ONE);
	    assertEquals(match.toString(), EXAMPLE_1 );

		match = new Match(player1, player2);
		TennisUtils.processMatch(match,MATCH_ONE_PLAYER_ONE, MATCH_ONE_PLAYER_TWO);
	    assertEquals(match.toString(), EXAMPLE_1 );
	    
		match = new Match(player1, player2);
		TennisUtils.processMatch(match,FIRST_2_SETS_PLAYER_ONE, FIRST_2_SETS_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_1_PLAYER_ONE_CONTINUE, MATCH_1_PLAYER_TWO_CONTINUE);
	    assertEquals(match.toString(), EXAMPLE_1 );
	}
	
	@Test
	  public void match2() {

		
		match = new Match(player1, player2);
		TennisUtils.processMatch(match,FIRST_2_SETS_PLAYER_ONE, FIRST_2_SETS_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_2_PLAYER_ONE_CONTINUE, MATCH_2_PLAYER_TWO_CONTINUE);
	    assertEquals(match.toString(), EXAMPLE_2 );
	}
	
	@Test
	  public void match3() {

		match = new Match(player1, player2);
		TennisUtils.processMatch(match,FIRST_2_SETS_PLAYER_ONE, FIRST_2_SETS_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_3_PLAYER_ONE_CONTINUE, MATCH_3_PLAYER_TWO_CONTINUE);
	    assertEquals(match.toString(), EXAMPLE_3 );
	}

	@Test
	  public void match4() {

		match = new Match(player1, player2);
		TennisUtils.processMatch(match,FIRST_2_SETS_PLAYER_ONE, FIRST_2_SETS_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_4_PLAYER_ONE_CONTINUE, MATCH_4_PLAYER_TWO_CONTINUE);
	    assertEquals(match.toString(), EXAMPLE_4 );
	}
	
	@Test
	  public void match5() {

		match = new Match(player1, player2);
		TennisUtils.processMatch(match,FIRST_2_SETS_PLAYER_ONE, FIRST_2_SETS_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_5_SET_3_PLAYER_ONE, MATCH_5_SET_3_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_5_SET_4_PLAYER_ONE, MATCH_5_SET_4_PLAYER_TWO);
		TennisUtils.processMatch(match,MATCH_5_SET_4_PLAYER_ONE_TIE_BREAK, MATCH_5_SET_4_PLAYER_TWO_TIE_BREAK);
		TennisUtils.processMatch(match,MATCH_5_SET_5_PLAYER_ONE, MATCH_5_SET_5_PLAYER_TWO);
	    assertEquals(match.toString(), EXAMPLE_5 );
	}
}
