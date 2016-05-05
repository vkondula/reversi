package ija.project2015.reversi;


import javax.swing.JButton;
/**
 * Class provides custom JButton with indexes included 
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
public class FieldButton extends JButton {
	
	private static final long serialVersionUID = 7218764272181825637L;
	private int i;
	private int j;
	
	/**
	 * Constructor
	 * @param text is label on button
	 * @param i is index in array
	 * @param j is index in array
	 */
	public FieldButton(String text, int i, int j){
		super(text);
		this.i = i;
		this.j = j;
	}
	
	/**
	 * Getter Function
	 * @return index
	 */
	public int getI(){
		return i;
	}
	/**
	 * Getter Function
	 * @return index
	 */
	public int getJ(){
		return j;
	}
	
}
