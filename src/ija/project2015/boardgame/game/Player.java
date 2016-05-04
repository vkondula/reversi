package ija.project2015.boardgame.game;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.Disk;
import ija.project2015.boardgame.board.Board;

public class Player {
	protected boolean white;
	protected Rules rules = null;
	protected int pool = 0;
	protected Board board;
	public Player(boolean isWhite){
		this.white = isWhite;
	}
	
	public boolean isWhite(){
		return white;
	}
	
	public boolean canPutDisk(Field field){
		if (rules != null && !emptyPool()){
			return rules.canPutDisk(field, new Disk(white));
		} else {
			return false;
		}
	}
	
	public boolean emptyPool(){
		return pool==0;
	}
	
	public boolean putDisk(Field field){
		pool -= 1;
		Disk disk = new Disk(white);
		boolean retval = field.putDisk(disk);
		board.addTurn(field, rules.getTurned());
		//rules.postPhase(field);
		return retval;
	}
	
	public void init(Board board){
		this.board = board;
		rules = board.getRules();
		pool = rules.numberDisks();
		int[][] coor = rules.getLayout(white);
		int count = (coor.length);
		for(int i=0; i<count; i++){
			Field field = board.getField(coor[i][0], coor[i][1]);
			putDisk(field);
		}
	}
	
	public boolean canPlay(){
		int size = board.getSize();
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				if (canPutDisk(board.getField(i, j))){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		if(white) return "White";
		else return "Black";
	}
}
