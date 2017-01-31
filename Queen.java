package chess;

public class Queen extends ChessPiece {
	
	protected Queen(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "Queen";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board) == true){
			//check additional conditions
			
			// copy&paste bishop conditions
			if(Math.abs(move.toColumn - move.fromColumn) == Math.abs(move.toRow - move.fromRow)){
				if(move.toColumn > move.fromColumn){
					if(move.toRow > move.fromRow){
						for(int i = Math.abs(move.toColumn - move.fromColumn) - 1; i > 0; i--){
							if(board[move.fromRow + i][move.fromColumn + i] != null){
								return false;
							}
						}
						return true;
					}
						
					if(move.toRow < move.fromRow){
						for(int j = Math.abs(move.toColumn - move.fromColumn) - 1; j > 0; j--){
							if(board[move.fromRow - j][move.fromColumn + j] != null){
								return false;
							}
						}
						return true;
					}
				}
				
				if(move.toColumn < move.fromColumn){
					if(move.toRow > move.fromRow){
						for(int k = Math.abs(move.toColumn - move.fromColumn) - 1; k > 0; k--){
							if(board[move.fromRow + k][move.fromColumn - k] != null){
								return false;
							}
						}
						return true;
					}
						
					if(move.toRow < move.fromRow){
						for(int l = Math.abs(move.toColumn - move.fromColumn) - 1; l > 0; l--){
							if(board[move.fromRow - l][move.fromColumn - l] != null){
								return false;
							}
						}
						return true;
					}
				}
			}
		
			//copy&paste rook conditions
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
