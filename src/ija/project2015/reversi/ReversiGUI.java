package ija.project2015.reversi;
/**
 * Class generates UI and handles on events
 * @author Václav Kondula, xkondu00
 * @author Martin Kraňák, xkrajn02
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JFileChooser;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import ija.project2015.boardgame.game.Game;
import ija.project2015.boardgame.board.Board;
import ija.project2015.boardgame.board.Field;
import ija.project2015.boardgame.game.Rules;
import ija.project2015.reversi.ReversiRules;
import ija.project2015.reversi.AI;
import ija.project2015.boardgame.game.Player;
import ija.project2015.reversi.FieldButton;

public class ReversiGUI extends JFrame implements ActionListener {
	
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
	private JButton exitGame;
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
	// Default setting for buttons, can be changed
	// Setting for new games
	private int B;	
	private int C;
	private int I;
	private int boardSize;
	private int alg;
	private boolean isWhite;
	// Back-up of setting of current game
	// Setting for save game
	private int b_B;	
	private int b_C;
	private int b_I;
	private int b_boardSize;
	private int b_alg;
	private boolean b_isWhite;
	
	/**  
	 * Method initiates new interface and also new game
	 * @param x is size of board
	 * @param alg is AI algorithm,
	 * @param b is time in seconds for which stones stay frozen
	 * @param c number of stones frozen
	 * @param y is time between freezes
	 */
	public ReversiGUI(int x, int alg, boolean isWhite, int b, int c, int y, FileReader fr) {
		// Default setting for buttons, can be changed
		// Setting for new games
		this.boardSize = x;
		this.B = b;
		this.C = c;
		this.I = y;
		this.alg = alg;
		this.isWhite = isWhite;
		// Back-up of setting of current game
		// Setting for save game
		this.b_boardSize = x;
		this.b_B = b;
		this.b_C = c;
		this.b_I = y;
		this.b_alg = alg;
		this.b_isWhite = isWhite;
		
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
		black = new Player(isWhite);
		if (alg != 0){  
			white = new AI(!isWhite, alg);
		} else {
			white = new Player(!isWhite);
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
		int windowSize; 
		if(x == 6)
			windowSize = 8*size + 30; 
		else
			windowSize = x*size+30;
			
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

		controlPanel = new JPanel(new MigLayout("alignx center, fill "));
		controlPanel.setBackground(Color.red);
		controlPanel.setPreferredSize(this.getSize());
		controlPanel.setBackground(customBlueBackground);
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

		createGame = new JButton("Create Game");
		createGame.addActionListener(this);
		createGame.setPreferredSize(new Dimension(100, 20));
		loadGame = new JButton("Load Game");
		loadGame.addActionListener(this);
		loadGame.setPreferredSize(new Dimension(100, 20));
		saveGame = new JButton("Save Game");
		saveGame.addActionListener(this);
		saveGame.setPreferredSize(new Dimension(100, 20));
		exitGame = new JButton("Exit Game");
		exitGame.addActionListener(this);
		exitGame.setPreferredSize(new Dimension(100, 20));
		
		gameSettings = new JLabel("Game");
		gameSettings.setForeground(Color.WHITE);

		controlPanel.add(gameSettings, "cell 0 0, left");
		controlPanel.add(createGame, "cell 0 1,right");
		controlPanel.add(loadGame, "cell 1 1,split 2,growx");
		controlPanel.add(saveGame);
		controlPanel.add(exitGame, "cell 2 1,growx, span");

		selectOponent = new ButtonGroup();
		oponentAlgoritm1 = new JRadioButtonMenuItem("Easy");
		oponentAlgoritm1.addActionListener(this);
		oponentAlgoritm1.setBackground(customBlueBackground);
		oponentAlgoritm1.setForeground(Color.WHITE);

		oponentAlgoritm2 = new JRadioButtonMenuItem("Hard");
		oponentAlgoritm2.addActionListener(this);
		oponentAlgoritm2.setBackground(customBlueBackground);
		oponentAlgoritm2.setForeground(Color.WHITE);

		oponentPlayer = new JRadioButtonMenuItem("Another Player");
		oponentPlayer.addActionListener(this);
		oponentPlayer.setBackground(customBlueBackground);
		oponentPlayer.setForeground(Color.WHITE);


		selectOponent.add(oponentPlayer);
		selectOponent.add(oponentAlgoritm1);
		selectOponent.add(oponentAlgoritm2);
		
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
			oponentPlayer.setSelected(true);
			break;
		case 1:
			oponentAlgoritm1.setSelected(true);
			break;
		case 2:
			oponentAlgoritm2.setSelected(true);
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
		
		if(isWhite){
			whiteStones.setSelected(true);
		} else {
			blackStones.setSelected(true);
		}
		
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
		// if AI starts, make move
		Player onTurn = game.currentPlayer();
		if (onTurn instanceof AI && fr == null){
			Field toPlay = ((AI)onTurn).getField();
			resolveTurn(toPlay);
		}
		if (fr!=null){
			// TODO: actual load game
		}
		
	}
	
	
	/** 
	 * Method handles interface events
	 * @param e is event on which this method is invoked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.whiteStones){
			this.isWhite = true;
		}
		
		if (e.getSource() == this.blackStones){
			this.isWhite= false;
		}
		
		if (e.getSource() == this.sizeSix){
			this.setBoardSize(6);
		}
		
		if (e.getSource() == this.sizeEight){
			this.setBoardSize(8);
		}
		
		if (e.getSource() == this.sizeTen){
			this.setBoardSize(10);
		}
		
		if (e.getSource() == this.sizeTwelve){
			this.setBoardSize(12);
		}
		
		if (e.getSource() == this.btnUndo){
			if (undoTurn()) game.nextPlayer();
			if (undoTurn()) game.nextPlayer();
			Player onTurn = game.currentPlayer();
			if (onTurn instanceof AI){
				if (undoTurn()) game.nextPlayer();
			}
			if (!onTurn.canPlay()){
				board.addTurn(null, new ArrayList<Field>());
				onTurn = game.nextPlayer();
				if (!onTurn.canPlay()){
					gameOver();
					return;
				}
			}
			messageTurn(onTurn.isWhite());
			if (onTurn instanceof AI){
				Field toPlay = ((AI)onTurn).getField();
				resolveTurn(toPlay);
			}
			
			
		}
		
		if (e.getSource() == this.createGame){
			if (!board.hasStarted()){
				this.dispose();
			}
			if(this.freezer.isSelected()){
				new ReversiGUI(this.getBoardSize(), this.alg, this.isWhite,
					(int)this.spinB.getValue(), (int)this.spinC.getValue(), (int)this.spinI.getValue(), null);
			}
			else{
				new ReversiGUI(this.getBoardSize(), this.alg, this.isWhite, 0,0,0, null);
			}
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
		
		if(e.getSource() == this.menu){ //menu
			if(!this.controlPanel.isShowing()){
				this.getContentPane().remove(this.boardPanel);
				this.getContentPane().add(controlPanel, "cell 0 0,baseline");
				this.controlPanel.setVisible(true);
			}
			else{
				this.getContentPane().remove(this.controlPanel);
				this.getContentPane().add(boardPanel, "cell 0 0,baseline");
				this.controlPanel.setVisible(false);
			}
			this.revalidate();
			this.repaint();
		}
		if(e.getSource() == this.freezer){
			if(freezer.isSelected()){
				this.spinB.setEnabled(true);
				this.spinC.setEnabled(true);
				this.spinI.setEnabled(true);
				this.spinB.setValue(this.B);
				this.spinC.setValue(this.C);
				this.spinI.setValue(this.I);
			}
			else{
				this.spinB.setEnabled(false);
				this.spinC.setEnabled(false);
				this.spinI.setEnabled(false);
			}
		}
		if (e.getSource() == this.exitGame){
			this.dispose();
		}
		if(e.getSource() == this.oponentAlgoritm1)
			this.alg = 1;
		if(e.getSource() == this.oponentAlgoritm2)
			this.alg = 2;
		if(e.getSource() == this.oponentPlayer)
			this.alg = 0;
		
		if(e.getSource() == this.saveGame)
			saveGame();
		if(e.getSource() == this.loadGame)
			loadGame();
	}
	/**
	 * Provides size of board
	 * @return size of board
	 */
	public int getBoardSize() {
		return boardSize;
	}
	
	/**
	 * Place right ImageIcon on JButton object
	 * @param i index of button inside array
	 * @param j index of button inside array
	 * @param white color of stone which will be placed on button
	 */
	private void setColor(int i, int j, Boolean white){
		if (white){
			btnFields[i-1][j-1].setIcon(this.whiteStone);
		} else {
			btnFields[i-1][j-1].setIcon(this.blackStone);
		}
	}
	/**
	 * Will set size of board
	 * @param boardSize actual size
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	
	/**
	 * Provides message for player
	 * @param white indicates for which player is message viewed
	 */
	protected void messageTurn(Boolean white){
		if (white)
			info.setText("White player's turn");
		else
			info.setText("Black player's turn");
	}
	
	/**
	 * TODO:
	 * @param selected
	 */
	protected void resolveTurn(Field selected){
		game.currentPlayer().putDisk(selected);
		ArrayList<Field> turned = rules.getTurned();
		for (Field toTurn : turned){
			setColor(toTurn.getRow(), toTurn.getCol(), game.currentPlayer().isWhite()); 
		}
		setColor(selected.getRow(),selected.getCol(), game.currentPlayer().isWhite());
		Player onTurn = game.nextPlayer(); 
		if (!onTurn.canPlay()){
			board.addTurn(null, new ArrayList<Field>());
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
	
	/**
	 * Handles end game state
	 */
	protected void gameOver(){
		playing = false;
		info.setText("GAMEOVER");
		String text;
		Player victorius = game.getWinningPlayer();
		if (victorius == null) {
			text = "It's a tie!";
		} else if (victorius instanceof AI){
			text = "You lost!";
		} else if ((!(white instanceof AI)&&(!(black instanceof AI)))){
			if (victorius.isWhite()){
				text = "White player won!";
			} else {
				text = "Black player won!";
			}
		} else {
			text = "Congratulations you won!";
		}
		int reply = JOptionPane.showConfirmDialog(null, "Do you wish to play again", text, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	this.dispose();
        	new ReversiGUI(this.boardSize, this.alg, this.isWhite, this.B, this.C, this.I, null);
        }
        else {
        	this.dispose();
        }
		System.out.println("GAME OVER");
	}
	
	/**
	 * Will take one turn back
	 */
	protected boolean undoTurn(){
		ArrayList<Field> fields = board.undoTurn();
		if (fields == null) return false;
		if (fields.isEmpty()) return true;
		Field remove = fields.get(fields.size()-1);
		fields.remove(fields.size()-1);
		remove.removeDisk();
		for (Field toTurn : fields){
			toTurn.getDisk().turn();
			boolean white = toTurn.getDisk().isWhite();
			setColor(toTurn.getRow(), toTurn.getCol(), white);
		}
		btnFields[remove.getRow()-1][remove.getCol()-1].setIcon(null);
		return true;
	}
	
	/**
	 * Loads game from a file
	 */
	protected void loadGame(){
		
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(this);
    
		if (returnValue == JFileChooser.APPROVE_OPTION) {
    	
			File selectedFile = fileChooser.getSelectedFile();
			
			if(!selectedFile.exists() || !selectedFile.canRead())
					System.out.println("File not exists");
			
			FileReader fr;
			try {
				fr = new FileReader(selectedFile);
				BufferedReader br = new BufferedReader(fr);
				String s = br.readLine();
				System.out.println(s);
				if (!s.contains("ReversiSave") ){
					System.out.println("file is not valid savegame file");
					return;
				}
				int[] properties = new int [4];
				for(int i=0; i<4; i++)
				{
					s = br.readLine();
					properties[i] = Integer.parseInt(s);
					System.out.print(properties[i]+"\n");
					
				}
				s = br.readLine();
				Boolean.parseBoolean(s);
				do
				{		
					s = br.readLine();
					System.out.println(s);
				}
				while(s != null);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
	}
	/**
	 * Saves game to a file
	 * @throws IOException 
	 */
    protected void saveGame(){
    	
    	JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          
        	File selectedFile = fileChooser.getSelectedFile();
        	System.out.println(selectedFile.getName());
        	
        	if(!selectedFile.exists()){
        		try {
					if(!selectedFile.createNewFile())
						System.out.println("Could not create file");
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	try {
        	    FileWriter fw = new FileWriter(selectedFile);
        	    BufferedWriter bw = new BufferedWriter(fw);
        	    bw.write("ReversiSave");								//flag
        	    bw.newLine();
        	    bw.append(String.valueOf(this.b_boardSize)+"\n");	//board size
        	    bw.append(String.valueOf(this.b_B)+"\n"+String.valueOf(this.b_C)+"\n"+String.valueOf(this.b_I)+"\n"); //timers
        	    bw.append(String.valueOf(this.b_alg)+"\n"); //opponent
        	    bw.append(String.valueOf(this.b_isWhite)+"\n"); //color
        	    
        	    for (Field field : this.board.getHistory()) {
					if (field != null) {
						bw.append(String.valueOf(field.getRow())+",");	//row
						bw.append(String.valueOf(field.getCol()));	//col
					}
					bw.newLine();
				}
        	    bw.write("END");	//end stamp
        	    bw.newLine();
        	    bw.close();
        	    fw.close();
        	}
        	catch (Exception e) {
        	   e.printStackTrace();
        	}
        }
    }
	/**
	 * Main Funcion
	 * @param args is empty
	 */
	public static void main(String[] args) {
		
		new ReversiGUI(8, 1, false ,0, 0, 0, null);
	}
}
