package ija.project2015.boardgame.game;



import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class TemplateGUI extends JFrame implements ActionListener {
	
	
	public TemplateGUI(int x) {
		
		int size = 60;
		int offset = 150;
		
		this.setMinimumSize(new Dimension(x*size + offset,x*size+ offset));
		this.setTitle("REVERSI");
		getContentPane().setLayout(new MigLayout("", "[456.00,grow][]", "[420.00,fill]"));
		this.getContentPane().setMinimumSize(new Dimension(x*size + offset,x*size + 50));
		
		JButton[][] buttons = new JButton[8][8];	
		JPanel boardPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		boardPanel.setLayout(new GridLayout(8, 8, 1, 1));
		boardPanel.setMinimumSize(new Dimension(x*size,x*size));
		boardPanel.setSize(new Dimension(x*size, x*size));	
		
		
		for	(int i=0; i<8; i++)
		{
			for	(int j=0; j<8; j++)
			{	
				buttons[i][j] = new JButton(String.valueOf(i)+String.valueOf(j));
				buttons[i][j].setBackground(new Color(0,102,153));
				boardPanel.add(buttons[i][j]);
				
			}
		}
		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.setMinimumSize(new Dimension(offset,x*size));
		controlPanel.setSize(new Dimension(offset, x*size));	
		
		JButton btnNewGame = new JButton("New Game");
		JButton btnSaveGame = new JButton("Save Game");
		JButton btnLoadGame = new JButton("Load Game");
		JButton btnFreezeStones = new JButton("Freeze Stones");
		
		Dimension buttonGap = new Dimension(0,5);
		
		
		controlPanel.add(btnNewGame);
		controlPanel.add(Box.createRigidArea(buttonGap));
		controlPanel.add(btnSaveGame);
		controlPanel.add(Box.createRigidArea(buttonGap));
		controlPanel.add(btnLoadGame);
		controlPanel.add(Box.createRigidArea(buttonGap));
		controlPanel.add(btnFreezeStones);
		
		this.getContentPane().add(boardPanel, "cell 0 0,baseline");
		this.getContentPane().add(controlPanel, "cell 1 0,baseline");
		this.setVisible(true);	
	}
	
	
	/*Comment*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello World");
		new TemplateGUI(8);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
