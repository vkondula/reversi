package ija.project2015.boardgame.board;

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
	
}
