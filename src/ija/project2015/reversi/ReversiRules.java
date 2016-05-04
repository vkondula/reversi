package ija.project2015.reversi;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.BoardField;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.boardgame.board.Disk;
import java.util.ArrayList;


public class ReversiRules implements Rules{
	protected int size;
	protected ArrayList<Field> turned;
	public ReversiRules(int size){
		this.size = size;
	}
	
	public Field createField(int row, int col){
		return new BoardField(row, col, this);
	}
	
	public int getSize(){
		return size;
	}
	
	public int numberDisks(){
		// ignore
		return size*size/2;
	}
	
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
	
	public boolean whiteStart(){
		return false;
	}
	
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
	
    public ArrayList<Field> getTurned() {
        return turned;
    }
}
