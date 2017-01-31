package chess;

public class Rook extends ChessPiece{
	
	
	protected Rook(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "Rook";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		// general chess piece conditions
		if(super.isValidMove(move, board) == true){
			if(move.fromRow == move.toRow){ // if moving HORIZONTALLY
				if(move.fromColumn > move.toColumn){ // if moving "left"
					for(int i = move.toColumn + 1; i < move.fromColumn; i++){
						if(board[move.fromRow][i] != null){ // check for obstructing pieces
							return false;
						}
					}
					return true;
				}
				
				if(move.fromColumn < move.toColumn){ // if moving "right"
					for(int i = move.fromColumn + 1; i < move.toColumn; i++){
						if(board[move.fromRow][i] != null){ // check for obstructing pieces
							return false;
						}
					}
					return true;
				}
			}
			if(move.fromColumn == move.toColumn){ // if moving VERTICALLY
				if(move.fromRow > move.toRow){ // if moving "up"
					for(int i = move.toRow + 1; i < move.fromRow; i++){
						if(board[i][move.fromColumn] != null){ // check for obstructing pieces
							return false;
						}
					}
					return true;
				}
				
				if(move.fromRow < move.toRow){ // if moving "down"
					for(int i = move.fromRow + 1; i < move.toRow; i++){
						if(board[i][move.toColumn] != null){ // check for obstructing pieces
							return false;
						}
					}
					return true;
				}
				
			}
		}
		return false;
	}

}
