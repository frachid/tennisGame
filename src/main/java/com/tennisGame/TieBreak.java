package com.tennisGame;


public class TieBreak extends AbstractGame {


	//-----------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Constructor----------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//
	protected TieBreak(Player player1, Player player2) {
		super(player1, player2);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder(CURRENT_TIE_BREAK_STATUS);
		toReturn.append(pointScorePlayer1);
		toReturn.append(TennisUtils.DASH);
		toReturn.append(pointScorePlayer2);
		return toReturn.toString();
	}




	/* (non-Javadoc)
	 * @see com.tennisGame.AbstractGame#calculateGameWinner()
	 */
	@Override
	protected Player calculateGameWinner() {
		Player tieBreakWinner = null;
		if((pointScorePlayer1>=TennisRule.MINIMUM_TO_WIN_TIE_BREAK) && (pointScorePlayer1-pointScorePlayer2>=TennisRule.TENNIS_DIFFERENCE)){
			tieBreakWinner = player1;
		} else if((pointScorePlayer2>=TennisRule.MINIMUM_TO_WIN_TIE_BREAK) && (pointScorePlayer2-pointScorePlayer1>=TennisRule.TENNIS_DIFFERENCE)){
			tieBreakWinner = player2;
		}
		return tieBreakWinner;
	}

}

