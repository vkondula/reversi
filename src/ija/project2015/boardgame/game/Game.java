package ija.project2015.boardgame.game;

import ija.project2015.boardgame.board.Board;
/**
 * Class represents the game
 * @author Vaclav Kondula, xkondu00
 * @author Martin Kranak, xkrajn02
 */
public class Game {
	protected Board board = null;
	protected Player white = null;
	protected Player black = null;
	protected Boolean whiteTurn = false;
	protected String result = null;
	
	/**
	 * Creates new game
	 * @param board
	 * with provided 
	 */
	public Game(Board board){
		this.board = board;
		whiteTurn = board.getRules().whiteStart();
	}
	/**
	 * Adds player to game
	 * @param player is player to be added
	 * @return success of operation
	 */
	public boolean addPlayer(Player player){
		if(player.isWhite() && white == null){
			white = player;
		} else if (!player.isWhite() && black == null) {
			black = player;
		} else return false;
		player.init(board);
		board.historyClear();
		return true;
	}
	
	/**
	 * Return player which has current turn
	 * @return color of player on turn
	 */
	public Player currentPlayer(){
		if (whiteTurn) return white;
		else return black;
	}
	/**
	 * Changes players turn
	 * @return nextPlayer
	 */
	public Player nextPlayer(){
		whiteTurn = !whiteTurn;
		return currentPlayer();
	}
	/**
	 * Provides board
	 * @return board
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Returns which players has more stones
	 * @return color of player
	 */
	public Player getWinningPlayer(){
		int whiteCount = board.getStonesCount(true);
		int blackCount = board.getStonesCount(false);
		result = String.valueOf(whiteCount) + " W/B " + String.valueOf(blackCount);
		if(whiteCount==blackCount) return null;
		else if (whiteCount>blackCount) return white;
		else return black;
	}
	/**
	 * State of game
	 * @return string which represents state of game
	 */
	public String getResult(){
		return result;
	}
}
