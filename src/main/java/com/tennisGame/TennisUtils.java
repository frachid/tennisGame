package com.tennisGame;


public class TennisUtils {

	public static final String DASH = "-";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";


	/**
	 * @param player1
	 * @param player2
	 * @param gameScorePlayer1
	 * @param gameScorePlayer2
	 * @return a new TieBreak if the score is (6-6), else a new Game.
	 */
	public static AbstractGame createNewGame(Player player1, Player player2, Integer gameScorePlayer1, Integer gameScorePlayer2) {
		AbstractGame newGame ;
		if(tieBreakShouldBeStarted(gameScorePlayer1,gameScorePlayer2)){
			newGame = new TieBreak(player1,player2);
		} else{
			newGame = new Game(player1,player2);
		}
		
		return newGame;
	}

	/**
	 * @param gameScorePlayer1
	 * @param gameScorePlayer2
	 * @return true if the score is (6-6)
	 */
	private static boolean tieBreakShouldBeStarted(Integer gameScorePlayer1, Integer gameScorePlayer2) {
		
		return gameScorePlayer1.equals(gameScorePlayer2) && gameScorePlayer1.equals(TennisRule.MINIMUM_GAMES_TO_WIN_SET);
	}


	/**
	 * The matchPoints indicates the number of points earned in a raw by a {@link Player}, starting by the player1
	 * @param match
	 * @param matchPoints
	 */
	public static void processMatch(Match match, Integer[] matchPoints) {
		boolean givePointsToPlayerOne = true;
		for(Integer pointsToWin:matchPoints){
			if(givePointsToPlayerOne){
				givePointsToPlayer(match,pointsToWin,match.getPlayer1());
				givePointsToPlayerOne=false;
			}else{
				givePointsToPlayer(match,pointsToWin,match.getPlayer2());
				givePointsToPlayerOne=true;
			}
		}
		
	}

	/**
	 * call match.pointTo(player), pointsToWin time.
	 * @param match
	 * @param pointsToWin
	 * @param player
	 */
	private static void givePointsToPlayer(Match match, Integer pointsToWin, Player player) {
		for(int i=0;i<pointsToWin;i++){
			match.pointTo(player);
		}
	}

	/**
	 * Second way to processMatch, more readable than the first
	 * @param match
	 * @param pointsPlayerOne
	 * @param pointsPlayerTwo
	 */
	public static void processMatch(Match match, Integer[] pointsPlayerOne, Integer[] pointsPlayerTwo) {
		int max = Math.max(pointsPlayerOne.length, pointsPlayerTwo.length);
		for(int i=0;i<max;i++){
			if(i<pointsPlayerOne.length){
				givePointsToPlayer(match,pointsPlayerOne[i],match.getPlayer1());
			}
			if(i<pointsPlayerTwo.length){
				givePointsToPlayer(match,pointsPlayerTwo[i],match.getPlayer2());
			}
		}
		
	}

}
