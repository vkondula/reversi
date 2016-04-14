package ija.project2015.boardgame.game;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.board.Disk;
import ija.project2015.boardgame.board.Board;

public class Player {
	protected boolean white;
	protected Rules rules = null;
	protected int pool = 0;
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
		rules.postPhase(field);
		return retval;
	}
	
	public void init(Board board){
		rules = board.getRules();
		pool = rules.numberDisks();
		int[][] coor = rules.getLayout(white);
		int count = (coor.length);
		for(int i=0; i<count; i++){
			Field field = board.getField(coor[i][0], coor[i][1]);
			putDisk(field);
		}
	}
	
	@Override
	public String toString(){
		if(white) return "white";
		else return "black";
	}
}
