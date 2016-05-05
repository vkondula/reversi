package ija.project2015.boardgame.board;
/**
 * Interface provides methods for fields located on board
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
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
