package chess;

import javax.swing.JOptionPane;

public class ChessModel implements IChessModel{
	private IChessPiece[][] board;
	private Player p;
	private int moveCounter = 0; // counter to change player
	private final int NULL_VAL = 9;
	private int numRows = NULL_VAL; 	 // temp variables for moving pieces, 9 will serve as a "null" value
	private int numCols = NULL_VAL;
	private final int BOARD_ROWS = 8;
	private final int BOARD_COLS = 8;
	private Move undoMove;
	IChessPiece A;
	IChessPiece B;
	
	/** *************************************************************************************************************** */
	
	public ChessModel(){
		// instantiate board, player, and an undo move
		board = new ChessPiece[BOARD_ROWS][BOARD_COLS];
		p = Player.WHITE;
		//undoMove = new Move(0,0,0,0);
		
		// black top pieces
		board[0][0]= new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);
		
		// white bottom pieces
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);
		
		
	}
	
	/** *************************************************************************************************************** */
	
	public boolean isComplete(){
		Move m;
		for(int row = 0; row < BOARD_ROWS; row++){		// iterates through all pieces on board
			for(int col = 0; col < BOARD_COLS; col++){
				if(board[row][col] != null && board[row][col].player() == currentPlayer()){ // only pays attention to non-null and currentPlayer pieces
					for(int r = 0; r < BOARD_ROWS; r++){						// iterates through the board again
						for(int c = 0; c < BOARD_COLS; c++){
							m = new Move(row, col, r, c);						// checks to see if piece can move to any of these piece
							if(isValidMove(m) == true){							// checks if this move will take current player out of check
								return false;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	/** *************************************************************************************************************** */
	
	public boolean isValidMove(Move move){
		IChessPiece A;								// store move
		A = board[move.fromRow][move.fromColumn];
		IChessPiece B;
		B = board[move.toRow][move.toColumn];
		
		
		if(A.isValidMove(move, board) == true){ // check if move is fundamentally valid (super/class validity)
			//if(inCheck(board[move.fromRow][move.fromColumn].player()) == true){
				move(move);							// "make" the move
				p = p.next();						//  change player, this UNDOES the player change done in the previous line when we make a move
				System.out.println(currentPlayer() + " after 'move' player");
				if(inCheck(A.player()) == false){				// move, has been made, check if the move has taken player out of check
					board[move.fromRow][move.fromColumn] = A;	// if the player is NO LONGER IN check (false) then reset the pieces, and return true
					board[move.toRow][move.toColumn] = B;
				     
				    System.out.println(currentPlayer() + " after player move back");
					return true;
				}
				else{											// if the player IS STILL IN check (true) then reset the pieces, tell the player it didn't work, and return false
					//JOptionPane.showMessageDialog(null, currentPlayer() + " must move out of check!");
					board[move.fromRow][move.fromColumn] = A;			
					board[move.toRow][move.toColumn] = B;
					
					
					return false;
				}
	
			
			
			
		}
		return false;
	}
	
	/** *************************************************************************************************************** */
			
	
	public void move (Move move){
		setUndo(move);
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];	// moves pieces
		board[move.fromRow][move.fromColumn] = null;
		
		p = p.next(); 
		//setUndo(move);																// sets the undo variable to allow for an undo
		if(inCheck(currentPlayer()) == true){										// warns player if in check	
			JOptionPane.showMessageDialog(null, currentPlayer() + " is in check");
		}
		
		setRows(NULL_VAL);																	// resets temp variables to a "null" value
		setCols(NULL_VAL);
		/*board[move.fromRow][move.fromColumn].remove();
		pieceAt(move.fromRow, move.fromColumn).remove();
		board[move.toRow][move.toColumn] = new Pawn(Player.WHITE);*/
		// maybe have a move counter, depending on odd/even we can tell who currentPlayer is 
	}
	
	/** *************************************************************************************************************** */
	
	public boolean inCheck(Player p){
		int kRow = 0;						//variables to save location of king
		int kCol = 0;
		for (int row = 0; row < BOARD_ROWS; row++){
			for (int col = 0; col < BOARD_COLS; col++) {
				if(board[row][col] instanceof  King){ 		// search for king of player type p
					if(board[row][col].player() == p){
						kRow = row;							// save king location
						kCol = col;
					}
				}
			}
		}
		Move m2;
		for(int row = 0; row < BOARD_ROWS ; row++){
			for(int col = 0; col < BOARD_COLS; col++){
				if(pieceAt(row, col) != null && pieceAt(row, col).player() != p){ 	// iterate through pieces that are opposite color of p's king
					m2 = new Move(row, col, kRow, kCol);		// make a move from that piece to the other players king
					if(isValidMove(m2) == true){		// check if it's valid, if it is, p is in check
						return true;					// return true because p is in check
					}
				}
			}
		}
		return false;
	}
	
	/** *************************************************************************************************************** */
	
	public Player currentPlayer() {	// returns current player
		return p;
		
	}
	
	public Player nextPlayer(){		// returns next player, used only once
		return p.next();
	}
	
	/** *************************************************************************************************************** */
	
	public int numRows() {		// getters for temp variables (used in moving pieces
		return numRows;
	}
	
	public int numCols() {
		return numCols;
	}
	
	/** *************************************************************************************************************** */
	
	public void setRows(int r){	// setters for temp variables
		numRows = r;
	}
	
	public void setCols(int c){
		numCols = c;
	}
	
	/** *************************************************************************************************************** */
	
	public IChessPiece pieceAt(int row, int col){	// returns piece at specified row/col location on board
		return board[row][col];
	}
	
	/** *************************************************************************************************************** */
	
	public void setUndo(Move pMove){ // FIXME error somewhere	// sets the undo to the last move made
		//A = board[pMove.fromRow][pMove.fromColumn];
		//B = board[pMove.toRow][pMove.toColumn];
		undoMove = pMove;
	}
	
	public void undo(){			// undoes last move
		p.next();
		board[undoMove.fromRow][undoMove.fromColumn] = B;
		board[undoMove.toRow][undoMove.toColumn] = A;
		//return undoMove;
	}
	
	/** *************************************************************************************************************** */
	
	

}
