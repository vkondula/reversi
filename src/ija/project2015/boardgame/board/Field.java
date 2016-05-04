package ija.project2015.boardgame.board;

public interface Field{
	void addNextField(Field.Direction dirs, Field field);
	Field nextField(Field.Direction dirs);
	boolean putDisk(Disk disk);
	Disk getDisk();
	boolean	isEmpty();
	boolean	canPutDisk(Disk disk);
	int getRow();
	int getCol();
	public void removeDisk();
	public static enum Direction{
		L,D,R,U,LD,LU,RD,RU;
	}
}
