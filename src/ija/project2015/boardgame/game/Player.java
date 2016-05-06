package ija.project2015.boardgame.game;

import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.Disk;

import ija.project2015.boardgame.board.Board;
/**
 * CLass declare player
 * @author Vaclav Kondula, xkondu00
 * @author Martin Krajnak, xkrajn02
 */
public class Player {
	
	protected boolean white;
	protected Rules rules = null;
	protected int pool = 0;
	protected Board board;
	/**
	 * Creates new player 
	 * @param isWhite adds color of stones
	 */
	public Player(boolean isWhite){
		this.white = isWhite;
	}
	
	/**
	 * Return color of player
	 * @return color of player
	 */
	public boolean isWhite(){
		return white;
	}
	
	/**
	 * Check if disk can be placed to desired location
	 * @param field is location
	 * @return boolean value
	 */
	public boolean canPutDisk(Field field){
		if (rules != null && !emptyPool()){
			return rules.canPutDisk(field, new Disk(white));
		} else {
			return false;
		}
	}
	
	/**
	 * Check if pool is empty
	 * @return boolean value
	 */
	public boolean emptyPool(){
		return pool==0;
	}
	
	/**
	 * Will place disk to desired location
	 * @param field represents location
	 * @return boolean value which represents success of operation
	 */
	public boolean putDisk(Field field){
		pool -= 1;
		Disk disk = new Disk(white);
		boolean retval = field.putDisk(disk);
		board.addTurn(field, rules.getTurned());
		//rules.postPhase(field);
		return retval;
	}
	
	/**
	 * Initialize player properties 
	 * @param board provides board and rules 
	 */
	public void init(Board board){
		this.board = board;
		this.rules = board.getRules();
		this.pool = rules.numberDisks();
		int[][] coor = rules.getLayout(white);
		int count = (coor.length);
		for(int i=0; i<count; i++){
			Field field = board.getField(coor[i][0], coor[i][1]);
			putDisk(field);
		}
	}
	
	/**
	 * Check if turn by player is possible
	 * @return boolean value
	 */
	public boolean canPlay(){
		int size = board.getSize();
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				if (canPutDisk(board.getField(i, j))){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Converts boolean value to string
	 * @return boolean value
	 */
	@Override
	public String toString(){
		if(white) return "White";
		else return "Black";
	}
}
