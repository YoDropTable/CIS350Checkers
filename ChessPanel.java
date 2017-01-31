/**
 * 
 */
package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JMenuBar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author User
 *
 */
public class ChessPanel extends JPanel {
	
	private JButton[][] board;
	private ChessModel model;
	private JButton undo;
	private JLabel currPlayer;
	private JPanel panelMain;
	private JPanel panelBoard;
	private JMenuBar menus;
	private JMenu fileMenu;
	private JMenuItem newGameItem;
	private JMenuItem quitItem;
	private IChessPiece iSquare;
	private ImageIcon blackKing, whiteKing, blackQueen, whiteQueen, blackRook, whiteRook, blackBishop, whiteBishop, blackKnight, whiteKnight, blackPawn, whitePawn;
	private final int BOARD_ROWS = 8;
	private final int BOARD_COLS = 8;
	private final int NULL_VAL = 9;
	//private JButton undo;
	// declare other instance variables
	
	private ButtonListener buttonListener = new ButtonListener(); // instantiate button listener
	
	public ChessPanel() {
		model = new ChessModel();		// new game
		
		/* *************************************************************************************************************** */
		
		blackKing = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\king_black.jpg");		// instantiates all image icons to their appropriate images
		whiteKing = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\king_white.jpg");
		blackQueen = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\queen_black.jpg");
		whiteQueen = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\queen_white.jpg");
		blackRook = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\rook_black.jpg");
		whiteRook = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\rook_white.jpg");
		blackBishop = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\bishop_black.jpg");
		whiteBishop = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\bishop_white.jpg");
		blackKnight = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\knight_black.jpg");
		whiteKnight = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\knight_white.jpg");
		blackPawn = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\pawn_black.jpg");
		whitePawn = new ImageIcon("C:\\Users\\User\\workspace\\ChessPrj\\pawn_white.jpg");
		
		/** *************************************************************************************************************** */
		// instantiate GUI objects
		setLayout(new GridBagLayout());						// sets layout for GUI
		GridBagConstraints loc = new GridBagConstraints();
		
		panelMain = new JPanel();			// upper panel for labels and utility buttons
		loc.gridx = 0;
		loc.gridy = 0;
		loc.gridwidth = 3;
		add(panelMain, loc);
		
		panelBoard = new JPanel();			// lower panel for the board
		loc.gridx = 0;
		loc.gridy = 10;
		add(panelBoard, loc);
		
		undo = new JButton("Undo");		// instantiate undo button, add action listener and add to panelMain
		loc.gridx = 2;
		loc.gridy = 0;
		undo.addActionListener(buttonListener);
		panelMain.add(undo, loc);
		
		currPlayer = new JLabel("Current Player: " + model.currentPlayer());	// label that displays current player
		loc.gridx = 3;
		panelMain.add(currPlayer, loc);
		
		/** *************************************************************************************************************** */
//		fileMenu = new JMenu("File");
//		quitItem = new JMenuItem("Quit");
//		newGameItem = new JMenuItem("New Game");
//		fileMenu.add(quitItem);
//		fileMenu.add(newGameItem);
//		menus = new JMenuBar();
//		setJMenuBar(menus); // cannot get JMenuBar to add to frame
//		menus.add(fileMenu);
//		
//		fileMenu.addActionListener(buttonListener);
//		quitItem.addActionListener(buttonListener);
//		newGameItem.addActionListener(buttonListener);
		
		
		/** *************************************************************************************************************** */
		// board set up
		
		panelBoard.setLayout(new GridLayout(8,8)); // not totally sure what this does but StackOverflow told me it would help and it did
		board = new JButton[BOARD_ROWS][BOARD_COLS];				   // instantiates all buttons, sets their background colors to either white or dark gray, adds their action listeners and changes size
		for (int row = 0; row < BOARD_ROWS; row++){
			for (int col = 0; col < BOARD_COLS; col++) {
				board[row][col] = new JButton(" ");
				if(col%2 == 0 && row%2 == 0 || col%2 != 0 && row%2 != 0)
					board[row][col].setBackground(Color.WHITE);
				if(col%2 == 0 && row%2 != 0 || col%2 != 0 && row%2 == 0)
					board[row][col].setBackground(Color.GRAY);
				board[row][col].addActionListener(buttonListener);
				board[row][col].setPreferredSize(new Dimension(100,100));
				
				panelBoard.add(board[row][col], loc); 		// add all buttons to board
			}
		}
		displayBoard();
		
		
	}
	
	/** *************************************************************************************************************** */
	private void displayBoard(){			// method for displaying and updating all visuals
		for(int row = 0; row < BOARD_ROWS; row++){
			for (int col = 0; col < BOARD_COLS; col++){
				iSquare = model.pieceAt(row, col);	// temp object
				
				if(iSquare instanceof Pawn && iSquare.player() == Player.BLACK){	// setting icons to appropriate pieces
					board[row][col].setIcon(blackPawn);
				}
				
				else if(iSquare instanceof Pawn && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whitePawn);
				}
				
				
				else if(iSquare instanceof King && iSquare.player() == Player.BLACK){
					board[row][col].setIcon(blackKing);
				}
				
				else if(iSquare instanceof King && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whiteKing);
				}
				
				
				else if(iSquare instanceof Queen && iSquare.player() == Player.BLACK){
					board[row][col].setIcon(blackQueen);
				}
				
				else if(iSquare instanceof Queen && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whiteQueen);
				}
				
				
				else if(iSquare instanceof Rook && iSquare.player() == Player.BLACK){
					board[row][col].setIcon(blackRook);
				}
				
				else if(iSquare instanceof Rook && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whiteRook);
				}
				
				
				else if(iSquare instanceof Bishop && iSquare.player() == Player.BLACK){
					board[row][col].setIcon(blackBishop);
				}
				
				else if(iSquare instanceof Bishop && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whiteBishop);
				}
				
				
				else if(iSquare instanceof Knight && iSquare.player() == Player.BLACK){
					board[row][col].setIcon(blackKnight);
				}
				
				else if(iSquare instanceof Knight && iSquare.player() == Player.WHITE){
					board[row][col].setIcon(whiteKnight);
				}
				
				else{
					board[row][col].setIcon(null);
				}
				
				if(model.numRows() != NULL_VAL && model.numCols() != NULL_VAL){						// if first click has been done, highlights piece clicked with blue
					board[model.numRows()][model.numCols()].setBackground(Color.CYAN);
//					for(int rowz = 0; rowz < BOARD_ROWS; rowz++){
//						for(int colz = 0; colz < BOARD_COLS; colz++){
//							Move m3 = new Move(model.numRows(), model.numCols(), rowz, colz);			// attempt to highlight valid moves for piece selected, causes major errors
//							if(model.isValidMove(m3) == true){
//								board[rowz][colz].setBackground(Color.GREEN);
//							}
//						}
//					}
				}
				else{
					if(col%2 == 0 && row%2 == 0 || col%2 != 0 && row%2 != 0)			// otherwise displays all as white and gray
						board[row][col].setBackground(Color.WHITE);
					if(col%2 == 0 && row%2 != 0 || col%2 != 0 && row%2 == 0)
						board[row][col].setBackground(Color.GRAY);
				}
			}
		}
	}
	
	/** *************************************************************************************************************** */
	
	//inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {		// button listener
		public void actionPerformed(ActionEvent event) {
			if(undo == event.getSource()){						// undo listener
				model.undo();
				currPlayer.setText("Current Player: " + model.currentPlayer());
				displayBoard();
			}
			
			else if(quitItem == event.getSource()){
				System.exit(0);
			}
			
			else if(newGameItem == event.getSource()){
				model = new ChessModel();
				for(int row = 0; row < BOARD_ROWS; row ++){
					for(int col = 0; col < BOARD_COLS; col++){
						board[row][col].setEnabled(true);
						displayBoard();
					}
				}
			}
			
			else{ 
				for (int row = 0; row < BOARD_ROWS; row++){ 					// listener for rest of buttons
				for (int col = 0; col < BOARD_COLS; col++){
					if (board[row][col] == event.getSource()){				// instance where first click has not been done, determined by temp variable value
						if(model.numRows() == NULL_VAL && model.numCols() == NULL_VAL){
							if(model.pieceAt(row, col) != null){			// checks that an actual piece is being clicked on, and that piece belongs to current player
								if(model.pieceAt(row, col).player() == model.currentPlayer()){
									model.setRows(row);
									model.setCols(col);
									//validMoveColorChange();
									displayBoard();
								}
							}
							
						}
						else{												// otherwise if first click has already been done (temp vars != 9), makes the move, updates display
							Move m1 = new Move(model.numRows(), model.numCols(), row, col);
							if(model.isValidMove(m1) == true){
								model.move(m1);
								// try is complete here
								if(model.isComplete() == true){
									JOptionPane.showMessageDialog(null, model.currentPlayer() + " has been checkmated! \nGAME OVER!");
									JOptionPane.showMessageDialog(null, model.nextPlayer()+ " has won!");
									deactivateButtons();
								}
								displayBoard();
								currPlayer.setText("Current Player: " + model.currentPlayer());
							}
							
							else{
								
								model.setRows(NULL_VAL);					// if the move trying to be made is not valid, de-selects the first-clicked piece      
								model.setCols(NULL_VAL);
								displayBoard();
							}
							
							
						}
					}
				}
			}
				displayBoard();
			}
		}
	}
	
	/** *************************************************************************************************************** */
	
	public void deactivateButtons(){
		for(int row = 0; row < BOARD_ROWS; row++){
			for(int col = 0; col < BOARD_COLS; col++){
				board[row][col].setEnabled(false);
			}
		}
	}
	
}



		
	

