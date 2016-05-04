package ija.project2015.reversi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.TextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import ija.project2015.boardgame.game.Game;
import ija.project2015.boardgame.board.Board;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.reversi.ReversiRules;
import jdk.nashorn.internal.ir.WhileNode;
import ija.project2015.reversi.AI;
import ija.project2015.boardgame.game.Player;
import ija.project2015.reversi.FieldButton;

public class ReversiGUI extends JFrame implements ActionListener {
	
	private int boardSize;
	
	private JButton btnUndo;
	private JButton[][] btnFields;	
	private JPanel boardPanel;
	private JMenuBar menuBar;
	
	private JPanel controlPanel;

	private JLabel gameSettings;
	private JLabel oponentSettings;
	private ButtonGroup selectOponent;
	private JRadioButtonMenuItem oponentAlgoritm1;
	private JRadioButtonMenuItem oponentAlgoritm2;
	private JRadioButtonMenuItem oponentPlayer;

	private JLabel sizeSettings;
	private ButtonGroup selectFieldSize;
	private JRadioButtonMenuItem sizeSix;
	private JRadioButtonMenuItem sizeEight;
	private JRadioButtonMenuItem sizeTen;
	private JRadioButtonMenuItem sizeTwelve;
	
	private ButtonGroup selectStoneColor;
	private JRadioButtonMenuItem blackStones;
	private JRadioButtonMenuItem whiteStones;
	private JLabel stoneSettings;

	private JButton menu;
	private JButton createGame;
	private JButton loadGame;
	private JButton saveGame;
	private JLabel info;
	private JCheckBox freezer;
	private JLabel lblI;
	private JLabel lblB;
	private JLabel lblC;
	private JSpinner spinB;
	private JSpinner spinC;
	private JSpinner spinI;
	

	private Color customBlueBackground;
	private ImageIcon blackStone = new ImageIcon("images/black.png");
	private ImageIcon whiteStone = new ImageIcon("images/white.png");
	
	private Rules rules;
	private Board board;
	private Game game;
	private Player white;
	private Player black;
	private boolean playing = true;
	
	private int B;	//freezer values
	private int C;
	private int I;
	
	
	public ReversiGUI(int x, int alg, int b, int c, int y) {
		
		this.boardSize = x;
		this.B = b;
		this.C = c;
		this.I = y;
		
		JSeparator optionSep = new JSeparator();
		JSeparator optionSep2 = new JSeparator();
		
		customBlueBackground = new Color(0,102,153);
		blackStone = new ImageIcon("images/black.png");
		whiteStone = new ImageIcon("images/white.png");
		
		this.setResizable(false);
		
		// Game related objects
		rules = new ReversiRules(x);
		board = new Board(rules);
		game = new Game(board);
		black = new Player(false);
		if (alg != 0){  
			white = new AI(true, alg);
		} else {
			white = new Player(true);
		}
		game.addPlayer(black);
		game.addPlayer(white);
			
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(this);
		
		boardPanel = new JPanel();
		info = new JLabel("Welcome");
		info.setHorizontalAlignment(SwingConstants.RIGHT);
		info.setPreferredSize(new Dimension(200,20));
		
		final int size = 70;
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
				btnFields[i][j] = new FieldButton("", i+1, j+1);
				btnFields[i][j].setBackground(new Color(0,102,153));
				btnFields[i][j].addActionListener(this);
				btnFields[i][j].setMargin(null);
				boardPanel.add(btnFields[i][j], "cell " + String.valueOf(i)+ " " + String.valueOf(j) +" ,grow");
			}
		}
		
		this.getContentPane().add(boardPanel, "cell 0 0,baseline");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);	
		menuBar.setLayout(new MigLayout("","[grow]"));
		menuBar.add(this.btnUndo,"cell 0 0,baseline");

		menu = new JButton("Menu");
		menu.addActionListener(this);
		menuBar.add(menu, "cell 1 0,right");

		controlPanel = new JPanel(new MigLayout("alignx center, fillx "));
		controlPanel.setBackground(Color.red);
		controlPanel.setPreferredSize(this.getSize());
		controlPanel.setBackground(customBlueBackground);
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

		createGame = new JButton("Create Game");
		createGame.addActionListener(this);
		loadGame = new JButton("Load Game");
		loadGame.addActionListener(this);
		saveGame = new JButton("Save Game");
		saveGame.addActionListener(this);

		gameSettings = new JLabel("Game");
		gameSettings.setForeground(Color.WHITE);

		controlPanel.add(gameSettings, "cell 0 0, left");
		controlPanel.add(createGame, "cell 0 1,center");
		controlPanel.add(loadGame, "cell 1 1,center");
		controlPanel.add(saveGame, "cell 2 1,center, span");

		selectOponent = new ButtonGroup();
		oponentAlgoritm1 = new JRadioButtonMenuItem("Algoritm 1");
		oponentAlgoritm1.addActionListener(this);
		oponentAlgoritm1.setBackground(customBlueBackground);
		oponentAlgoritm1.setForeground(Color.WHITE);

		oponentAlgoritm2 = new JRadioButtonMenuItem("Algoritm 1");
		oponentAlgoritm2.addActionListener(this);
		oponentAlgoritm2.setBackground(customBlueBackground);
		oponentAlgoritm2.setForeground(Color.WHITE);

		oponentPlayer = new JRadioButtonMenuItem("Another Player");
		oponentPlayer.addActionListener(this);
		oponentPlayer.setBackground(customBlueBackground);
		oponentPlayer.setForeground(Color.WHITE);

		selectOponent.add(oponentAlgoritm1);
		selectOponent.add(oponentAlgoritm2);
		selectOponent.add(oponentPlayer);
		
		controlPanel.add(optionSep, "growx, wrap, span 3");
		
		oponentSettings = new JLabel("Select oponent");
		oponentSettings.setForeground(Color.white);
		
		controlPanel.add(oponentSettings, "cell 0 4, left, gapy 30px");
		controlPanel.add(oponentPlayer, "cell 0 5, left");
		controlPanel.add(oponentAlgoritm1, "cell 0 6, left");
		controlPanel.add(oponentAlgoritm2, "cell 0 7, left");

		sizeSettings = new JLabel("Select board size");
		sizeSettings.setForeground(Color.WHITE);

		selectFieldSize = new ButtonGroup();
		sizeSix = new JRadioButtonMenuItem("6x6");
		sizeSix.setBackground(customBlueBackground);
		sizeSix.setForeground(Color.WHITE);
		sizeSix.addActionListener(this);

		sizeEight = new JRadioButtonMenuItem("8x8");
		sizeEight.setBackground(customBlueBackground);
		sizeEight.setForeground(Color.WHITE);
		sizeEight.addActionListener(this);

		sizeTen = new JRadioButtonMenuItem("10x10");
		sizeTen.setBackground(customBlueBackground);
		sizeTen.setForeground(Color.WHITE);
		sizeTen.addActionListener(this);

		sizeTwelve = new JRadioButtonMenuItem("12x12");
		sizeTwelve.setBackground(customBlueBackground);
		sizeTwelve.setForeground(Color.WHITE);
		sizeTwelve.addActionListener(this);

		selectFieldSize.add(sizeEight);
		selectFieldSize.add(sizeSix);
		selectFieldSize.add(sizeTen);
		selectFieldSize.add(sizeTwelve);

		controlPanel.add(sizeSettings, "cell 1 4, Left , gapy 30px");
		controlPanel.add(sizeSix, "cell 1 5, center");
		controlPanel.add(sizeEight, "cell 1 6, center");
		controlPanel.add(sizeTen, "cell 1 7, center");
		controlPanel.add(sizeTwelve, "cell 1 8, center");

		freezer = new JCheckBox("Enable Freeze");
		freezer.setForeground(Color.WHITE);
		freezer.setBackground(customBlueBackground);
		freezer.addActionListener(this);
		controlPanel.add(freezer,"cell 2 4, right, gapy 30px");

		SpinnerModel spinnerModel = new SpinnerNumberModel(10,0,20,1);

		lblI = new JLabel("I:");
		lblI.setForeground(Color.white);
		spinI = new JSpinner(spinnerModel);
		controlPanel.add(lblI,"cell 2 5, split 2, right ");
		controlPanel.add(spinI);

		lblB = new JLabel("B:");
		lblB.setForeground(Color.WHITE);
		spinB = new JSpinner(spinnerModel);
		controlPanel.add(lblB,"cell 2 6, split 2, right");
		controlPanel.add(spinB);

		lblC = new JLabel("C:");
		lblC.setForeground(Color.white);
		spinC = new JSpinner(spinnerModel);
		controlPanel.add(lblC,"cell 2 7, split 2, right");
		controlPanel.add(spinC);

		controlPanel.add(optionSep2, "cell 0 9, growx, wrap, span 3");
		
		stoneSettings = new JLabel("Choose side");
		stoneSettings.setForeground(Color.white);
		
		controlPanel.add(stoneSettings,"cell 1 10, left, gapy 30px");
		
		selectStoneColor = new ButtonGroup();
		whiteStones = new JRadioButtonMenuItem(whiteStone);
		whiteStones.setBackground(customBlueBackground);
		whiteStones.addActionListener(this);
		selectStoneColor.add(whiteStones);
		
		blackStones = new JRadioButtonMenuItem(blackStone);
		blackStones.setBackground(customBlueBackground);
		blackStones.addActionListener(this);
		selectStoneColor.add(blackStones);
		blackStones.setSelected(true);
		
		controlPanel.add(blackStones, "cell 1 11,  right ");
		controlPanel.add(whiteStones, "cell 1 12,  right ");
		
		menuBar.add(info,"cell 2 0,right");
		
		switch (x) {
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
		
		switch (alg) {
		case 0:
			oponentAlgoritm1.setSelected(true);
			break;
		case 1:
			oponentAlgoritm2.setSelected(true);
			break;
		case 2:
			oponentPlayer.setSelected(true);
			break;
		default:
			break;
		}
		
		if(this.B != 0 || this.C != 0 || this.I != 0)
		{
			freezer.setSelected(true);
			spinB.setValue(this.B);
			spinC.setValue(this.C);
			spinI.setValue(this.I);
		}
		else
		{
			freezer.setSelected(false);
			spinB.setEnabled(false);
			spinC.setEnabled(false);
			spinI.setEnabled(false);
		}
		this.setVisible(true);	
	
		// GAME RELATED CODE
		// set starting stones
		// white
		int [][] fields = rules.getLayout(true);
		setColor(fields[0][0], fields[0][1], true);
		setColor(fields[1][0], fields[1][1], true);
		// black
		fields = rules.getLayout(false);
		setColor(fields[0][0], fields[0][1], false);
		setColor(fields[1][0], fields[1][1], false);
		
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
		
		if (e.getSource() == this.btnUndo){
			undoTurn();
			undoTurn();
		}
		
		if (e.getSource() == this.createGame)
		{
			int alg = 0;
			if (oponentAlgoritm1.isSelected()){
				alg = 1;
			}
			else if (oponentAlgoritm2.isSelected()){
				alg = 2;
			}
			new ReversiGUI(this.getBoardSize(), alg, 
					(int)this.spinB.getValue(), (int)this.spinC.getValue(), (int)this.spinI.getValue());
		}
		
		
		if (e.getSource() instanceof FieldButton){
			FieldButton button = (FieldButton) e.getSource();
			Field selected = board.getField(button.getI(), button.getJ());
			if (game.currentPlayer().canPutDisk(selected)){
				resolveTurn(selected);
			} else {
				info.setText(game.currentPlayer() + ": Can't put disk there");
			}		
			
		}
		
		if(e.getSource() == this.menu)	//menu
		{
			if(!this.controlPanel.isShowing())
			{
				this.getContentPane().remove(this.boardPanel);
				this.getContentPane().add(controlPanel, "cell 0 0,baseline");
				this.controlPanel.setVisible(true);
			}
			else
			{
				this.getContentPane().remove(this.controlPanel);
				this.getContentPane().add(boardPanel, "cell 0 0,baseline");
				this.controlPanel.setVisible(false);
			}
			this.revalidate();
			this.repaint();
		}
		if(e.getSource() == this.freezer)
		{
			if(freezer.isSelected())
			{
				this.spinB.setEnabled(true);
				this.spinC.setEnabled(true);
				this.spinI.setEnabled(true);
				this.spinB.setValue(this.B);
				this.spinC.setValue(this.C);
				this.spinI.setValue(this.I);
			}
			else
			{
				this.spinB.setEnabled(false);
				this.spinC.setEnabled(false);
				this.spinI.setEnabled(false);
			}
		}
	}

	public int getBoardSize() {
		return boardSize;
	}

	private void setColor(int i, int j, Boolean white){
		if (white){
			btnFields[i-1][j-1].setIcon(this.whiteStone);
		} else {
			btnFields[i-1][j-1].setIcon(this.blackStone);
		}
	}
	
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	
	protected void messageTurn(Boolean white){
		if (white)
			info.setText("White player's turn");
		else
			info.setText("Black player's turn");
	}
	
	protected void resolveTurn(Field selected){
		game.currentPlayer().putDisk(selected);
		ArrayList<Field> turned = rules.getTurned();
		for (Field toTurn : turned){
			setColor(toTurn.getRow(), toTurn.getCol(), game.currentPlayer().isWhite()); 
		}
		setColor(selected.getRow(),selected.getCol(), game.currentPlayer().isWhite());
		Player onTurn = game.nextPlayer(); 
		if (!onTurn.canPlay()){
			onTurn = game.nextPlayer();
			if (!onTurn.canPlay()){
				gameOver();
				return;
			}
		}
		if (onTurn instanceof AI){
			Field toPlay = ((AI)onTurn).getField();
			resolveTurn(toPlay);
		}
		if (playing)
			messageTurn(game.currentPlayer().isWhite());
	}
	
	protected void gameOver(){
		playing = false;
		info.setText("GAMEOVER");
		JOptionPane.showMessageDialog(new JFrame(), "You won ?"); //TODO: prehral som 
		for	(int i=0; i<boardSize; i++){
			for	(int j=0; j<boardSize; j++){	
				btnFields[i][j].setEnabled(false);
			}
		}
		System.out.println("GAME OVER");
	}
	
	protected void undoTurn(){
		ArrayList<Field> fields = board.undoTurn();
		if (fields == null) return;
		Field remove = fields.get(fields.size()-1);
		fields.remove(fields.size()-1);
		remove.removeDisk();
		for (Field toTurn : fields){
			toTurn.getDisk().turn();
			boolean white = toTurn.getDisk().isWhite();
			setColor(toTurn.getRow(), toTurn.getCol(), white);
		}
		btnFields[remove.getRow()-1][remove.getCol()-1].setIcon(null);;
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello World");
		new ReversiGUI(8, 1, 0, 0, 0);
	}
}
