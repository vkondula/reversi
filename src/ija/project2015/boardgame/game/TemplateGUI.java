package ija.project2015.boardgame.game;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Graphics;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;

public class TemplateGUI extends JFrame implements ActionListener {
	
	private JButton btnUndo = new JButton("Undo");
	
	public TemplateGUI(int x) {
		
		final int size = 60;
		final int paneSize = x*size;
		final int windowSize = x*size+30;
		
		this.getContentPane().setMinimumSize(new Dimension(paneSize,paneSize));
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setMinimumSize(new Dimension(windowSize,windowSize));
		this.setTitle("REVERSI");
		
		this.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//JPanel controlPanel = new JPanel();
		
		//controlPanel.add(btnUndo);
		
		JButton[][] btnFields = new JButton[x][x];	
		JPanel boardPanel = new JPanel();
	
		boardPanel.setLayout(new MigLayout("gap 1px", "[" + String.valueOf(paneSize)+ ", grow]", "[" + String.valueOf(paneSize)+ ", grow]"));
				
			
		for	(int i=0; i<x; i++)
		{
			for	(int j=0; j<x; j++)
			{	
				btnFields[i][j] = new JButton(String.valueOf(i)+String.valueOf(j));
				btnFields[i][j].setBackground(new Color(0,102,153));
				boardPanel.add(btnFields[i][j], "cell " + String.valueOf(i)+ " " + String.valueOf(j) +" ,grow");
				
				
			}
		}
		
		//this.getContentPane().add(controlPanel,"cell 0 0,growx");
		this.getContentPane().add(boardPanel, "cell 0 0,baseline");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.setLayout(new MigLayout("","[grow]"));
		
		btnUndo.setBackground(Color.GRAY);
		menuBar.add(this.btnUndo,"cell 0 0,baseline");
		
		JMenu menu = new JMenu("MENU");
		menuBar.add(menu, "cell 1 0,center");
		
		JMenuItem SaveGame = new JMenuItem("Save Game");
		menu.add(SaveGame);
		JMenuItem LoadGame = new JMenuItem("Load Game");
		menu.add(LoadGame);
		
		menu.addSeparator();
		JLabel lblOponent = new JLabel("Select Oponent");
		menu.add(lblOponent);
		
		ButtonGroup selectOponent = new ButtonGroup();
		JRadioButtonMenuItem oponentComputer = new JRadioButtonMenuItem("Computer");
		
		oponentComputer.setSelected(true);
		selectOponent.add(oponentComputer);
		menu.add(oponentComputer);

		JRadioButtonMenuItem oponentPlayer = new JRadioButtonMenuItem("Another Player");
		
		selectOponent.add(oponentPlayer);
		menu.add(oponentPlayer);
		
		menu.addSeparator();
		
		JLabel lblSize = new JLabel("Select Size");
		menu.add(lblSize);

		ButtonGroup selectFieldSize = new ButtonGroup();
		JRadioButtonMenuItem sizeSix = new JRadioButtonMenuItem("6x6");
		
		selectFieldSize.add(sizeSix);
		menu.add(sizeSix);

		JRadioButtonMenuItem sizeEight = new JRadioButtonMenuItem("8x8");
		
		sizeEight.setSelected(true);
		selectFieldSize.add(sizeEight);
		menu.add(sizeEight);
		
		JRadioButtonMenuItem sizeTen = new JRadioButtonMenuItem("10x10");
		
		selectFieldSize.add(sizeTen);
		menu.add(sizeTen);
		
		JRadioButtonMenuItem sizeTwelve = new JRadioButtonMenuItem("12x12");
		
		selectFieldSize.add(sizeTwelve);
		menu.add(sizeTwelve);

		menu.addSeparator();

		JLabel lblFreeze = new JLabel("Stone Freezing");
		menu.add(lblFreeze);

		ButtonGroup selectFreeze = new ButtonGroup();
		JRadioButtonMenuItem freezeI = new JRadioButtonMenuItem("Freeze I");
		
		selectFreeze.add(freezeI);
		menu.add(freezeI);

		JRadioButtonMenuItem freezeB = new JRadioButtonMenuItem("Freeze B");
		
		selectFreeze.add(freezeB);
		menu.add(freezeB);
		
		JRadioButtonMenuItem freezeC = new JRadioButtonMenuItem("Freeze C");
		
		selectFreeze.add(freezeC);
		menu.add(freezeC);
		
		JRadioButtonMenuItem freezeOff = new JRadioButtonMenuItem("Freeze Off");
		
		freezeOff.setSelected(true);
		selectFreeze.add(freezeOff);
		menu.add(freezeOff);


		menu.addSeparator();
		
		JMenuItem QuitGame = new JMenuItem("Quit Game");
		menu.add(QuitGame);
		
		
		JLabel info = new JLabel("White's turn");
		menuBar.add(info,"cell 2 0,right");
		
		menuBar.setUI ( new BasicMenuBarUI (){
		    public void paint ( Graphics g, JComponent c ){
		       g.setColor ( Color.GRAY);
		       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
		    }
		} );
		
		this.setVisible(true);	
		
	}
	
	
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
