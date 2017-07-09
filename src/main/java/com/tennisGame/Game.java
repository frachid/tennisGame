package com.tennisGame;

public class Game extends AbstractGame {

	private static final String DEUCE = "deuce";
	private static final String ADVANTAGE = "advantage";
	private static final Object VICTORY = "victory to : ";

	//-----------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Constructor----------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//
	public Game(Player player1, Player player2) {
		super(player1, player2);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder(CURRENT_GAME_STATUS);
		if(winner!=null){
			toReturn.append(VICTORY);
			toReturn.append(winner.getName());
		}else{
			if(isInDeuceState()){
				toReturn.append(DEUCE);
			} else if(isInAdvantageState()){
				toReturn.append(ADVANTAGE);
			} else{
				toReturn.append(TennisRule.tennisScore().get(pointScorePlayer1));
				toReturn.append(TennisUtils.DASH);
				toReturn.append(TennisRule.tennisScore().get(pointScorePlayer2));
			}
		}
		
		
		return toReturn.toString();
	}
	
	
	/**
	 * @return true if the score is advantage
	 */
	private boolean isInAdvantageState() {
		boolean isInAdvantageState = false;
		if(Math.max(pointScorePlayer1, pointScorePlayer2) > TennisRule.THREE && Math.abs(pointScorePlayer1-pointScorePlayer2) == 1){
			isInAdvantageState = true;
		}
		return isInAdvantageState;
	}

	/**
	 * @return true if the score is advantage
	 */
	private boolean isInDeuceState() {
		boolean isInDeuceState = false;
		if(pointScorePlayer1 >= TennisRule.THREE && pointScorePlayer1==pointScorePlayer2){
			isInDeuceState = true;
		}
		return isInDeuceState;
	}

	
	/* (non-Javadoc)
	 * @see com.tennisGame.AbstractGame#calculateGameWinner()
	 */
	@Override
	protected Player calculateGameWinner() {
		Player gameWinner = null;
		if((pointScorePlayer1>=TennisRule.MINIMUM_TO_WIN_GAME) && (pointScorePlayer1-pointScorePlayer2>=TennisRule.TENNIS_DIFFERENCE)){
			gameWinner = player1;
		} else if((pointScorePlayer2>=TennisRule.MINIMUM_TO_WIN_GAME) && (pointScorePlayer2-pointScorePlayer1>=TennisRule.TENNIS_DIFFERENCE)){
			gameWinner = player2;
		}
		return gameWinner;
	}


}
