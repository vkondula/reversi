package ija.project2015.reversi;
/**
 * TODO
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
import ija.project2015.boardgame.game.Player;

import java.util.ArrayList;
import java.util.Random;

import ija.project2015.boardgame.board.Disk;
import ija.project2015.boardgame.board.Field;

public class AI extends Player{
	protected int alg;
	public AI(boolean isWhite, int difficulty){
		super(isWhite);
		this.alg = difficulty;
	}
	
	public Field getField(){
		if (alg == 1) return randomField();
		if (alg == 2) return bestField();
		return null;
	} 
	
	protected Field randomField(){
		ArrayList<Field> fields = new ArrayList<Field>();
		int size = board.getSize();
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				if (canPutDisk(board.getField(i, j))){
					fields.add(board.getField(i, j));
				}
			}
		}
		if (fields.size() > 0){
			int index = new Random().nextInt(fields.size());
			return fields.get(index);
		}
		return null;
	}
	
	protected Field bestField(){
		Field maxField = null;
		Field tmp;
		int max = 0;
		int size = board.getSize();
		for (int i=1; i<size+1; i++){
			for (int j=1; j<size+1; j++){
				tmp = board.getField(i, j);
				if (canPutDisk(tmp)){
					int turning = toTurn(tmp);
					if(turning>max){
						max = turning;
						maxField = tmp;
					}
				}
			}
		}
		return maxField;
	}
	
	protected int toTurn(Field field){
		int turning = 0;
		for (Field.Direction dirs : Field.Direction.values()){
			int buffer = 0;
			boolean ready = false;
			Field searched = field.nextField(dirs);
			while(searched != null && !searched.isEmpty()){
				Disk next = searched.getDisk();
				if(next != null){
					if(ready && next.isWhite()==white){
						turning += buffer;
						break;
					} else if(next.isWhite()!=white){ 
						buffer++;
						ready = true;
						searched = searched.nextField(dirs);
					} else 
						break;
				} else {
					break;
				}
			}
		}
		return turning;
	}
	
}
