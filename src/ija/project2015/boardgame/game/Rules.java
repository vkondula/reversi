package ija.project2015.boardgame.game;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.Disk;

public interface Rules {
	Field createField(int row, int col);
	int getSize();
	int numberDisks();
	int[][] getLayout(boolean isWhite);
	boolean whiteStart();
	boolean canPutDisk(Field field, Disk disk);
	void postPhase(Field field);
}
