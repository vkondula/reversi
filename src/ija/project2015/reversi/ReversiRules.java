package ija.project2015.reversi;
/**
 * Class represents set of rules for reversi games
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.BoardField;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.boardgame.board.Disk;
import java.util.ArrayList;


public class ReversiRules implements Rules{
	protected int size;
	protected ArrayList<Field> turned;
	/**
	 * Constructor
	 * @param size of board
	 */
	public ReversiRules(int size){
		this.size = size;
	}
	
	/**
	 * Creates new field and also new Boardfield
	 * @param row index
	 * @param col index
	 */
	public Field createField(int row, int col){
		return new BoardField(row, col, this);
	}
	/**
	 * Provides size of board
	 */
	public int getSize(){
		return size;
	}
	/**
	 * Number of disk are Infite
	 */
	public int numberDisks(){
		return -1;
	}
	
	/**
	 * return first 4 psitions of stones
	 * @param isWhite color of player
	 */
	public int[][] getLayout(boolean isWhite){
		int[][] layout = new int[2][2];
		int start = size/2;
		if (isWhite){
			layout[0][0] = start;
			layout[0][1] = start;
			layout[1][0] = start+1;
			layout[1][1] = start+1;
		} else {
			layout[0][0] = start;
			layout[0][1] = start+1;
			layout[1][0] = start+1;
			layout[1][1] = start;
		}
		return layout;
		
	}
	
	/**
	 * Black player doeasnt start
	 */
	public boolean whiteStart(){
		return false;
	}
	/**
	 * Check whether disk with certain color can be placed on field
	 * @param field desired field
	 * @param disk disk to be placed
	 */
	public boolean canPutDisk(Field field, Disk disk){
		if (field.getDisk()!=null) return false;
		boolean white = disk.isWhite();
		for (Field.Direction dirs : Field.Direction.values()){
			boolean ready = false;
			Field searched = field.nextField(dirs);
			while(searched != null && !searched.isEmpty()){
				Disk next = searched.getDisk();
				if(next != null){
					if(ready && next.isWhite()==white) 
						return true;
					else if(next.isWhite()!=white){ 
						ready = true;
						searched = searched.nextField(dirs);
					} else 
						break;
				} else {
					break;
				}
			}
		}
		return false;
	}
	/**
	 * Turns stones after placing disk
	 * every turned disk is added to arraylist 
	 * @param field 
	 */
	public void postPhase(Field field){
		Disk disk = field.getDisk();
		if(disk==null) return;
		Boolean white = disk.isWhite();
		turned = new ArrayList<Field>();
		for (Field.Direction dirs : Field.Direction.values()){
			ArrayList<Field> buffer = new ArrayList<Field>();
			boolean ready = false;
			Field searched = field.nextField(dirs);
			while(searched != null && !searched.isEmpty()){
				Disk next = searched.getDisk();
				if(next != null){
					if(ready && next.isWhite()==white){
						turned.addAll(buffer);
						break;
					} else if(next.isWhite()!=white){ 
						buffer.add(searched);
						ready = true;
						searched = searched.nextField(dirs);
					} else 
						break;
				} else {
					break;
				}
			}
		}
		for (Field toTurn : turned){
			toTurn.getDisk().turn();
		}
		
	}
	/**
	 * returns array that contains turned stones
	 */
    public ArrayList<Field> getTurned() {
        return turned;
    }
}
