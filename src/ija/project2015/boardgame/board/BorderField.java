package ija.project2015.boardgame.board;
/**
 * TODO 
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
public class BorderField implements Field {
	public BorderField(){
	}
	public void addNextField(Field.Direction dirs, Field field){
		
	}
	public Field nextField(Field.Direction dirs){
		return null;
	}
	public Disk getDisk(){
		return null;
	}
	public boolean putDisk(Disk disk){
		return false;
	}
	public boolean	canPutDisk(Disk disk){
		return false;
	}
	public boolean	isEmpty(){
		return false;
	}
	public int getRow(){
		return 0;
	}
	public int getCol(){
		return 0;
	}
	public void removeDisk(){

	}
	
}
