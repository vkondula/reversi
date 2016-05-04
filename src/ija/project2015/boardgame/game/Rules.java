package ija.project2015.boardgame.game;
import ija.project2015.boardgame.board.Field;

import java.util.ArrayList;

import ija.project2015.boardgame.board.Disk;

public interface Rules {
	Field createField(int row, int col);
	int getSize();
	int numberDisks();
	int[][] getLayout(boolean isWhite);
	boolean whiteStart();
	boolean canPutDisk(Field field, Disk disk);
	void postPhase(Field field);
	public ArrayList<Field> getTurned();
}
