package ija.project2015.reversi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicMenuBarUI;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.TextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;

import ija.project2015.boardgame.game.Game;
import ija.project2015.boardgame.board.Board;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.reversi.ReversiRules;
import ija.project2015.boardgame.game.Player;
import ija.project2015.reversi.FieldButton;

public class ReversiGUI extends JFrame implements ActionListener {
	
	private int boardSize;
	
	private JButton btnUndo;
	private JButton[][] btnFields;	
	private JPanel boardPanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem createGame;
	private JMenuItem saveGame;
	private JMenuItem loadGame;
	private JMenuItem exitGame;
	private JLabel lblOponent;
	private JLabel lblSize;
	private ButtonGroup selectOponent;
	private JRadioButtonMenuItem oponentAlgoritm1;
	private JRadioButtonMenuItem oponentAlgoritm2;
	private JRadioButtonMenuItem oponentPlayer;
	private ButtonGroup selectFieldSize;
	private JRadioButtonMenuItem sizeSix;
	private JRadioButtonMenuItem sizeEight;
	private JRadioButtonMenuItem sizeTen;
	private JRadioButtonMenuItem sizeTwelve;
	private JLabel info;
	private JCheckBox freezer;
	private JLabel I;
	private JLabel B;
	private JLabel C;
	private TextField setI;
	private TextField setB;
	private TextField setC;
	
	private Rules rules;
	private Board board;
	private Game game;
	private Player white;
	private Player black;
	
	
	public ReversiGUI(int x) {
		
		final int itemWidth = 200;
		final int itemHeight = 30; 
		this.boardSize = x;
		// Game related objects
		rules = new ReversiRules(x);
		board = new Board(rules);
		game = new Game(board);
		black = new Player(false);
		if (false){  // TODO: Doplnit AI algoritmy
			// white = AI(alg);
		} else {
			white = new Player(true);
		}
			
		
		btnUndo = new JButton("Undo");
		boardPanel = new JPanel();
		menuBar = new JMenuBar();
		menu = new JMenu("MENU");
		
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		
		createGame = new JMenuItem("Create Game");
		createGame.setPreferredSize(new Dimension(itemWidth, itemHeight));
		createGame.addActionListener(this);
		
		saveGame = new JMenuItem("Save Game");
		saveGame.setPreferredSize(new Dimension(itemWidth, itemHeight));
		saveGame.addActionListener(this);
		
		loadGame = new JMenuItem("Load Game");
		loadGame.setPreferredSize(new Dimension(itemWidth, itemHeight));
		loadGame.addActionListener(this);
		
		exitGame = new JMenuItem("Exit Game");
		exitGame.setPreferredSize(new Dimension(itemWidth, itemHeight));
		exitGame.addActionListener(this);
		
		lblOponent = new JLabel("Select Oponent");
		lblOponent.setPreferredSize(new Dimension(itemWidth,itemHeight));
		
		selectOponent = new ButtonGroup();
		oponentAlgoritm1 = new JRadioButtonMenuItem("Algoritm 1");
		oponentAlgoritm1.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
		oponentAlgoritm2 = new JRadioButtonMenuItem("Algoritm 1");
		oponentAlgoritm2.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
		oponentPlayer = new JRadioButtonMenuItem("Another Player");
		oponentPlayer.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
		lblSize = new JLabel("Select Size");
		selectFieldSize = new ButtonGroup();
		
		sizeSix = new JRadioButtonMenuItem("6x6");
		sizeSix.addActionListener(this);
		sizeSix.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
		sizeEight = new JRadioButtonMenuItem("8x8");
		sizeEight.setPreferredSize(new Dimension(itemWidth, itemHeight));
		sizeEight.addActionListener(this);
		
		sizeTen = new JRadioButtonMenuItem("10x10");
		sizeTen.setPreferredSize(new Dimension(itemWidth, itemHeight));
		sizeTen.addActionListener(this);
		
		sizeTwelve = new JRadioButtonMenuItem("12x12");
		sizeTwelve.setPreferredSize(new Dimension(itemWidth, itemHeight));
		sizeTwelve.addActionListener(this);
		
		info = new JLabel("White's turn");
		freezer = new JCheckBox("Freeze stones", false);
		freezer.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
		I = new JLabel("I");
		I.setPreferredSize(new Dimension(itemWidth, itemHeight));
		B = new JLabel("B");
		B.setPreferredSize(new Dimension(itemWidth, itemHeight));
		C = new JLabel("C");
		C.setPreferredSize(new Dimension(itemWidth, itemHeight));
		
//		setI = new TextField("Time between freezes");
//		setI.setPreferredSize(new Dimension(itemWidth,itemHeight));
//		setI.addActionListener(this);
//		setI.setEnabled(false);
//		
//		setC = new TextField("Number Of Stones");
//		setC.setPreferredSize(new Dimension(itemWidth,itemHeight));
//		setC.addActionListener(this);
//		
//		setB = new TextField("Freeze Time");
//		setB.setPreferredSize(new Dimension(itemWidth,itemHeight));
//		setB.addActionListener(this);
		
		final int size = 60;
		final int paneSize = x*size;
		final int windowSize = x*size+30;
		
		this.getContentPane().setMinimumSize(new Dimension(paneSize,paneSize));
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setMinimumSize(new Dimension(windowSize,windowSize));
		this.setTitle("REVERSI");
		
		this.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
	
		boardPanel.setLayout(new MigLayout("gap 1px", "[" + String.valueOf(paneSize)+ ", grow]", "[" + String.valueOf(paneSize)+ ", grow]"));
				
		btnFields = new JButton[x][x];	
		for	(int i=0; i<x; i++)
		{
			for	(int j=0; j<x; j++)
			{	
				btnFields[i][j] = new FieldButton(String.valueOf(i)+String.valueOf(j), i+1, j+1);
				btnFields[i][j].setBackground(new Color(0,102,153));
				btnFields[i][j].addActionListener(this);
				boardPanel.add(btnFields[i][j], "cell " + String.valueOf(i)+ " " + String.valueOf(j) +" ,grow");
			}
		}
		
		this.getContentPane().add(boardPanel, "cell 0 0,baseline");
		
		setJMenuBar(menuBar);
		
		menuBar.setLayout(new MigLayout("","[grow]"));
		
		btnUndo.setBackground(Color.GRAY);
		menuBar.add(this.btnUndo,"cell 0 0,baseline");
		
		menuBar.add(menu, "cell 1 0,center");
		
		menu.add(createGame);
		menu.add(saveGame);
		menu.add(loadGame);
		
		menu.addSeparator();
		menu.add(lblOponent);
		
		oponentAlgoritm1.setSelected(true);
		selectOponent.add(oponentAlgoritm1);
		menu.add(oponentAlgoritm1);
		
		selectOponent.add(oponentAlgoritm2);
		menu.add(oponentAlgoritm2);

		selectOponent.add(oponentPlayer);
		menu.add(oponentPlayer);
		
		menu.addSeparator();
		menu.add(lblSize);

		
		selectFieldSize.add(sizeSix);
		menu.add(sizeSix);

		selectFieldSize.add(sizeEight);
		menu.add(sizeEight);
		
	
		selectFieldSize.add(sizeTen);
		menu.add(sizeTen);
		
		selectFieldSize.add(sizeTwelve);
		menu.add(sizeTwelve);

		menu.addSeparator();
		
		menu.add(freezer);
		menu.add(I);
		JSlider Islider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
		Islider.setMajorTickSpacing(5);
		Islider.setMinorTickSpacing(1);
		Islider.setPaintLabels(true);
		Islider.setPaintTicks(true);
		
		menu.add(Islider);
		JSlider Cslider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
		Cslider.setMajorTickSpacing(5);
		Cslider.setMinorTickSpacing(1);
		Cslider.setPaintLabels(true);
		Cslider.setPaintTicks(true);
		
		menu.add(C);
		menu.add(Cslider);
		JSlider Bslider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
		Bslider.setMajorTickSpacing(5);
		Bslider.setMinorTickSpacing(1);
		Bslider.setPaintLabels(true);
		Bslider.setPaintTicks(true);
		
		menu.add(B);
		menu.add(Bslider);
		
		menu.addSeparator();
		menu.add(exitGame);
		
		menuBar.add(info,"cell 2 0,right");
		
		menuBar.setUI ( new BasicMenuBarUI (){
		    public void paint ( Graphics g, JComponent c ){
		       g.setColor ( Color.GRAY);
		       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
		    }
		} );
		
		switch (this.boardSize) {
		case 6:
			sizeSix.setSelected(true);
			break;
		case 8:
			sizeEight.setSelected(true);
			break;
		case 10:
			sizeTen.setSelected(true);
			break;
		case 12:
			sizeTwelve.setSelected(true);
			break;
		default:
			break;
		}
		
		this.setVisible(true);	
	
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello World");
		new ReversiGUI(8);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.sizeSix)
		{
			this.setBoardSize(6);
		}
		
		if (e.getSource() == this.sizeEight)
		{
			this.setBoardSize(8);
		}
		
		if (e.getSource() == this.sizeTen)
		{
			this.setBoardSize(10);
		}
		
		if (e.getSource() == this.sizeTwelve)
		{
			this.setBoardSize(12);
		}
		
		if (e.getSource() == this.createGame)
		{
			new ReversiGUI(this.boardSize);
		}
		
		if (e.getSource() == this.exitGame)
		{
			this.dispose();
		}
		
		if (e.getSource() instanceof FieldButton){
			FieldButton button = (FieldButton) e.getSource();
			int i = button.getI();
			int j = button.getJ();
			System.out.println(String.valueOf(i)+"/"+String.valueOf(j));
		}
		
	}

	public int getBoardSize() {
		return boardSize;
	}


	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	
}