package ija.project2015.boardgame.board;
/**
 * Represents the gaming board
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
import java.util.ArrayList;

import ija.project2015.boardgame.game.Rules;

public class Board {
	
	protected int size;
	protected Field[][] board;
	protected Rules rules = null;
	protected ArrayList<ArrayList<Field>> history;
	
	/**
	 * creates new gaming board
	 * @param rules provides rules for game
	 */
	public Board(Rules rules){
		this.rules = rules;
		this.history = new ArrayList<ArrayList<Field>>();
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
	/**
	 * Returns field on coordinates defined by parameters
	 * @param row
	 * @param col
	 * @return field
	 */
	public Field getField(int row, int col){
		return board[row][col];
	}
	
	/**
	 * Provides size of board
	 * @return size of board
	 */
	public int getSize(){
		return size;
	}
	/**
	 * Provides rules
	 * @return rules
	 */
	public Rules getRules(){
		return rules;
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * Adds to to history
	 * @param field
	 * @param turned
	 */
	public void addTurn(Field field, ArrayList<Field> turned){
		ArrayList<Field> tmp = new ArrayList<Field>(turned);
		if (field != null)
			tmp.add(field);
		history.add(tmp);
	}
	
	/**
	 * Takes one turn back
	 * @return
	 */
	public ArrayList<Field> undoTurn(){
		if (history.size() == 0) return null;
		ArrayList<Field> retval = history.get(history.size()-1);
		history.remove(history.size()-1);
		return retval;
	}
	
	/**
	 * Clears history of turns
	 */
	public void historyClear(){
		history = new ArrayList<ArrayList<Field>>();
	}
	
	/**
	 * Indicates if game has started
	 * @return
	 */
	public boolean hasStarted(){
		return !history.isEmpty();
	}
	
	/**
	 * Provides count of stones with same color
	 * @param isWhite
	 * @return count
	 */
	public int getStonesCount(boolean isWhite){
		int count = 0;
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				Disk tmp = getField(i, j).getDisk();
				if (tmp==null) continue;
				if (tmp.isWhite() == isWhite) count++;
			}	
		}
		return count;
	}
	/**
	 * Provides data for save
	 * @return
	 */
	public ArrayList<Field> getHistory() {
		ArrayList<Field> retval = new ArrayList<Field>();
		for (ArrayList<Field> f : history) {
			if (f.isEmpty())
				retval.add(null);
			else
				retval.add(f.get(f.size()-1));
		}
		return retval;
	}

}
	