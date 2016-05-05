package ija.project2015.boardgame.game;
/**
 * TODO 
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
import ija.project2015.boardgame.board.Board;

public class Game {
	protected Board board = null;
	protected Player white = null;
	protected Player black = null;
	protected Boolean whiteTurn = false;
	public Game(Board board){
		this.board = board;
		whiteTurn = board.getRules().whiteStart();
	}
	
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
	
	public Player currentPlayer(){
		if (whiteTurn) return white;
		else return black;
	}
	
	public Player nextPlayer(){
		whiteTurn = !whiteTurn;
		return currentPlayer();
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Player getWinningPlayer(){
		int whiteCount = board.getStonesCount(true);
		int blackCount = board.getStonesCount(false);
		System.out.println(String.valueOf(whiteCount) + "/" + String.valueOf(blackCount));
		if(whiteCount==blackCount) return null;
		else if (whiteCount>blackCount) return white;
		else return black;
	}
}
