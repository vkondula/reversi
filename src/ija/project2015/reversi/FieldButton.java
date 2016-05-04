package ija.project2015.reversi;
import javax.swing.JButton;

public class FieldButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7218764272181825637L;
	private int i;
	private int j;
	
	public FieldButton(String text, int i, int j){
		super(text);
		this.i = i;
		this.j = j;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ(){
		return j;
	}
	
}
