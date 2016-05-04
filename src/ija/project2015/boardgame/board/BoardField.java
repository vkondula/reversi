package ija.project2015.boardgame.board;
import ija.project2015.boardgame.game.Rules;
import java.util.HashMap;

public class BoardField implements Field{
	protected int row;
	protected int col;
	protected Rules rules = null;
	protected HashMap<Field.Direction, Field> neighbours;
	protected Disk disk = null;
	public BoardField(int row, int col){
		this.row = row;
		this.col = col;
		this.neighbours = new HashMap<Direction, Field>();
	}
	public BoardField(int row, int col, Rules rules){
		this(row, col);
		this.rules = rules;
	}
	public void addNextField(Field.Direction dirs, Field field){
		neighbours.put(dirs, field);
	}
	public Field nextField(Field.Direction dirs){
		return neighbours.get(dirs);
	}
	public Disk getDisk(){
		return disk;
	}
	public boolean putDisk(Disk disk){
		if (this.disk==null){
			this.disk = disk;
			rules.postPhase(this);
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof BoardField))
			return false;
		BoardField other = (BoardField) obj;
		if (row == other.getRow() && col == other.getCol())
			return true;
		else
			return false;
	}
	@Override
	public int hashCode() {
		return row*5+col*17;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public boolean	isEmpty(){
		return disk == null;
	}
	public boolean canPutDisk(Disk disk){
		return rules.canPutDisk(this, disk);
	}
	public void removeDisk(){
		disk = null;
	}
	
}
