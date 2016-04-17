package ija.project2015.boardgame.board;
import ija.project2015.boardgame.game.Rules;

public class Board {
	protected int size;
	protected Field[][] board;
	protected Rules rules = null;
	public Board(Rules rules){
		this.rules = rules;
		this.size = rules.getSize();
		this.board = new Field[size+2][size+2];
		for (int i=0; i<size+2; i++){
			for (int j=0; j<size+2; j++){
				if(i==0 || j==0 || i==size+1 || j==size+1){
					board[i][j] = new BorderField();
				} else {
					board[i][j] = rules.createField(i, j);
				}
			}
		}
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				for (Field.Direction dirs : Field.Direction.values()){
					if (dirs==Field.Direction.D)
						board[i][j].addNextField(dirs, board[i+1][j]);
					else if (dirs==Field.Direction.U)
						board[i][j].addNextField(dirs, board[i-1][j]);
					else if (dirs==Field.Direction.R)
						board[i][j].addNextField(dirs, board[i][j+1]);
					else if (dirs==Field.Direction.L)
						board[i][j].addNextField(dirs, board[i][j-1]);
					else if (dirs==Field.Direction.LD)
						board[i][j].addNextField(dirs, board[i+1][j-1]);
					else if (dirs==Field.Direction.LU)
						board[i][j].addNextField(dirs, board[i-1][j-1]);
					else if (dirs==Field.Direction.RD)
						board[i][j].addNextField(dirs, board[i+1][j+1]);
					else if (dirs==Field.Direction.RU)
						board[i][j].addNextField(dirs, board[i-1][j+1]);
				}
			}
				
		}
	}
	
	public Field getField(int row, int col){
		return board[row][col];
	}
	
	public int getSize(){
		return size;
	}
	
	public Rules getRules(){
		return rules;
	}
	
	@Override
	public String toString(){
		String map = "";
		for(int i=1; i<=size; i++){
			for(int j=1; j<=size; j++){
				Disk disk = getField(i,j).getDisk();
				if(disk==null) map+=".";
				else if (disk.isWhite()) map+="W";
				else map+="B";
			}
			map+="\n";
		}
		return map;
	}
}