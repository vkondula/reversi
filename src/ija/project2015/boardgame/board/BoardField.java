package ija.project2015.boardgame.board;

import ija.project2015.boardgame.game.Rules;
import java.util.HashMap;
/**
 * Represents the part of board where the player can put disk
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
public class BoardField implements Field{
	protected int row;
	protected int col;
	protected Rules rules = null;
	protected HashMap<Field.Direction, Field> neighbours;
	protected Disk disk = null;
	/**
	 * Creates new boardField
	 * @param row index 
	 * @param col index
	 */
	public BoardField(int row, int col){
		this.row = row;
		this.col = col;
		this.neighbours = new HashMap<Direction, Field>();
	}
	/**
	 * Creates new boardField
	 * @param row index 
	 * @param col index
	 * @param rules representation
	 */
	public BoardField(int row, int col, Rules rules){
		this(row, col);
		this.rules = rules;
	}
	/**
	 * Adds field 
	 * @param dirs is direction where field is added
	 * @param field is field which is added
	 */
	public void addNextField(Field.Direction dirs, Field field){
		neighbours.put(dirs, field);
	}
	/**
	 * Return field
	 * @param dirs is direction in which desired field is
	 */
	public Field nextField(Field.Direction dirs){
		return neighbours.get(dirs);
	}
	/**
	 * Provides disk which is on selected field
	 * @return disk placed on field
	 */
	public Disk getDisk(){
		return disk;
	}
	/**
	 * Tries to put disk on field
	 * @param disk to be placed
	 * @return boolean value which represents success of operation
	 */
	public boolean putDisk(Disk disk){
		if (this.disk==null){
			this.disk = disk;
			rules.postPhase(this);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Decides if two object are equal
	 * @param obj is compared object
	 * @return true is objects are equal otherwise false
	 */
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof BoardField))
			return false;
		BoardField other = (BoardField) obj;
		if (row == other.getRow() && col == other.getCol())
			return true;
		else
			return false;
	}
	/**
	 * Provides index to hashtable
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		return row*5+col*17;
	}
	/**
	 * Provides index of field
	 * @return row
	 */
	public int getRow(){
		return this.row;
	}
	/**
	 * Provides index of field
	 * @return column
	 */
	public int getCol(){
		return this.col;
	}
	/**
	 * Check if field is empty
	 * @return boolean value returned by comparison
	 */
	public boolean	isEmpty(){
		return this.disk == null;
	}
	/**
	 * Check is disk can be placed on this field
	 * @param disk to be placed
	 * @return boolean value which indicates if disk can be placed on field
	 */
	public boolean canPutDisk(Disk disk){
		return rules.canPutDisk(this, disk);
	}
	/**
	 * Removed disk from field
	 */
	public void removeDisk(){
		this.disk = null;
	}
	
}
