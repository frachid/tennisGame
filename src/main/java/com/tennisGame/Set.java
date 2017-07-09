package com.tennisGame;

import java.util.ArrayList;
import java.util.List;

public class Set {

	
	private static final String SET_FINISHED = " already won the set";
	private static final String ONLY_FINISHED = "Only finished games could be added to set";
	private Player player1;
	private Player player2;
	private Player winner;
	private List<AbstractGame> listGames;
	private Integer gameScorePlayer1;
	private Integer gameScorePlayer2;
	

	//-----------------------------------------------------------------------------------------------------------------------//
	//-------------------------------------------------Constructors----------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//
	public Set(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		gameScorePlayer1 = 0;
		gameScorePlayer2 = 0;
	    listGames = new ArrayList<AbstractGame>();
		listGames.add(new Game(player1,player2));
	}
	public Set(Player player1, Player player2, List<AbstractGame> listGames) {
		this.player1 = player1;
		this.player2 = player2;
		gameScorePlayer1 = 0;
		gameScorePlayer2 = 0;
		this.listGames = listGames;
		updateGameScores();
		AbstractGame astractGame = TennisUtils.createNewGame(player1,player2,gameScorePlayer1,gameScorePlayer2);
		this.listGames.add(astractGame);
	}

	//-----------------------------------------------------------------------------------------------------------------------//
	//----------------------------------------------Getters and Setters------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public List<AbstractGame> getlistGames() {
		return listGames;
	}
	public void setlistGames(List<AbstractGame> listGames) {
		this.listGames = listGames;
	}
	
	/**
	 * @return The Last {@link AbstractGame} in the listGames which corresponds to the actual game played
	 */
	public AbstractGame getActualGame() {
		return listGames.get(listGames.size()-1);
	}
	
	/**
	 * Add one score point to the player
	 * @param {@link Player} : the player which is winning the point
	 */
	public void pointTo(Player player) {
		assertSetIsOnGoing();
		getActualGame().pointTo(player);
		checkActualGame();
	}
	


	/**
	 * Called in case the actual game is over
	 * Set the {@link Set} winner if the {@link Set} is finished
	 * adding a new {@link AbstractGame} if not.
	 */
	private void checkSetWinner(){
		Player setWinner = calculateSetWinner();
		if(setWinner!=null){
			setWinner(setWinner);
		} else{
			AbstractGame astractGame = TennisUtils.createNewGame(player1,player2,gameScorePlayer1,gameScorePlayer2);
			this.listGames.add(astractGame);
		}
		
		
	}
	
	/**
	 * @return the {@link Player} who won the {@link Set}. null if the {@link Set} is not won yet
	 */
	private Player calculateSetWinner() {
		Player setWinner = null;
		if((gameScorePlayer1==TennisRule.MINIMUM_GAMES_TO_WIN_SET+1) || 
		((gameScorePlayer1==TennisRule.MINIMUM_GAMES_TO_WIN_SET) && gameScorePlayer1-gameScorePlayer2>=TennisRule.TENNIS_DIFFERENCE)){
			setWinner = player1;
		} else if((gameScorePlayer2==TennisRule.MINIMUM_GAMES_TO_WIN_SET+1) || 
		((gameScorePlayer2==TennisRule.MINIMUM_GAMES_TO_WIN_SET) && gameScorePlayer2-gameScorePlayer1>=TennisRule.TENNIS_DIFFERENCE)){
			setWinner = player2;
		}
		return setWinner;
	}
	
	/**
	 * called after when adding a point to a {@link Player}
	 * update Game scores and check if the {@link Set} is over in case the actual {@link AbstractGame} is over
	 */
	private void checkActualGame() {
		if(getActualGame().getWinner()!=null){
			updateGameScores(getActualGame());
			checkSetWinner();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder(TennisUtils.OPEN_PARENTHESIS);
		toReturn.append(gameScorePlayer1);
		toReturn.append(TennisUtils.DASH);
		toReturn.append(gameScorePlayer2);
		toReturn.append(TennisUtils.CLOSE_PARENTHESIS);
		return toReturn.toString();
	}
	
	/**
	 * Used only in JUnits
	 * allows us to add directly a {@link AbstractGame} in a {@link Set}
	 * @param abstractGame
	 */
	public void add(AbstractGame abstractGame){
		assertSetIsOnGoing();
		assertGameToBeAddedIsFinished(abstractGame);
		AbstractGame actualGame = getActualGame();
		listGames.remove(listGames.size()-1);
		listGames.add(abstractGame);
		updateGameScores(abstractGame);
		checkSetWinner();
		if(winner==null){
			listGames.add(actualGame);
		}

	}
	
	/**
	 * Called when trying to add a point in a {@link Set} to one of the 2 players
	 * Or a {@link AbstractGame} to a {@link Set}
	 * @throws IllegalStateException: in case the game is already won by one of the players
	 */
	private void assertSetIsOnGoing() throws IllegalStateException{
		if(winner!=null){
			throw new IllegalStateException(winner.getName()+SET_FINISHED);
		}
		
	}
	
	/**
	 * Called when Trying to add an {@link AbstractGame} to a {@link Set}
	 * @param abstractGame
	 * @throws IllegalStateException
	 */
	private void assertGameToBeAddedIsFinished(AbstractGame abstractGame) throws IllegalStateException{
		if(abstractGame.getWinner()==null){
			throw new IllegalStateException(ONLY_FINISHED);
		}
		
	}
	
	/**
	 * Called in the constructor
	 * Calculate the game points
	 */
	private void updateGameScores() {
		for(AbstractGame game :listGames){
			updateGameScores(game);
			
		}
		
	}
	
	
	/**
	 * depends on the winner, increments gameScorePlayer1 or gameScorePlayer2
	 * @param game
	 */
	private void updateGameScores(AbstractGame game) {
		if(player1.equals(game.getWinner())){
			gameScorePlayer1++;
		} else if(player2.equals(game.getWinner())){
			gameScorePlayer2++;
		}
	}
	
}
