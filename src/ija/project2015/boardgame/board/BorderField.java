package ija.project2015.boardgame.board;
/**
 * Represents the border of the board
 * @author Václav Kondula, xkondu00
 * @author Martin Krajňák, xkrajn02
 */
public class BorderField implements Field {
	/**
	 * Creates new BorderField
	 */
	public BorderField(){
	}
	/**
	 * Adds new field
	 * @param dirs represents direction in which field will be created
	 * @param field to be created
	 */
	public void addNextField(Field.Direction dirs, Field field){
		
	}
	/**
	 * Provides nextfield
	 * @param dirs direction 
	 * @return null
	 */
	public Field nextField(Field.Direction dirs){
		return null;
	}
	/**
	 * Provides disk
	 * @return null
	 */
	public Disk getDisk(){
		return null;
	}
	/**
	 * You cannot place disk on borderfield
	 * @param disk to be placed
	 * @return false
	 */
	public boolean putDisk(Disk disk){
		return false;
	}
	/**
	 * You cannot place disk on borderfield
	 * @param disk is disk to be placed
	 * @return false
	 */
	public boolean	canPutDisk(Disk disk){
		return false;
	}
	/**
	 * Border field is always empty
	 * @return false
	 */
	public boolean	isEmpty(){
		return false;
	}
	/**
	 * Gets row
	 * @return 0
	 */
	public int getRow(){
		return 0;
	}
	/**
	 * Gets col
	 * @return 0
	 */
	public int getCol(){
		return 0;
	}
	/**
	 * is Empty
	 */
	public void removeDisk(){
	}
	/**
	 * Freeze and lock disk
	 */
	public void freeze(){
	}
	/**
	 * Unlock field
	 * Called at the end of timer
	 */
	public void unlock(){
	}
	/**
	 * Unfreeze field
	 * Called at the end of every turn
	 * Unfreezed only if it was unlocked first by the timer
	 */
	public void unfreeze(){
	}
	/**
	 * Return whether field is frozen or not
	 */
	public boolean isFrozen(){
		return false;
	}
	public boolean isLocked(){
		return false;
	}
}
