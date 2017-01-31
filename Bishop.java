package chess;

public class Bishop extends ChessPiece {
	
	protected Bishop(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board) == true){
			//check additional conditions
			if(Math.abs(move.toColumn - move.fromColumn) == Math.abs(move.toRow - move.fromRow)){
				
				if(move.toColumn > move.fromColumn){ 	// right movement
					if(move.toRow > move.fromRow){		// right and down movement
						for(int i = Math.abs(move.toColumn - move.fromColumn) - 1; i > 0; i--){
							if(board[move.fromRow + i][move.fromColumn + i] != null){ // checks for obstructing pieces 
								return false;
							}
						}
						return true;
					}
					
					if(move.toRow < move.fromRow){ // right and up movement
						for(int j = Math.abs(move.toColumn - move.fromColumn) - 1; j > 0; j--){
							if(board[move.fromRow - j][move.fromColumn + j] != null){ // checks for obstructing pieces
								return false;
							}
						}
						return true;
					}
				}
				
				if(move.toColumn < move.fromColumn){ // left movement
					if(move.toRow > move.fromRow){ // left and down movement
						for(int k = Math.abs(move.toColumn - move.fromColumn) - 1; k > 0; k--){
							if(board[move.fromRow + k][move.fromColumn - k] != null){ // checks for obstructing pieces
								return false;
							}
						}
						return true;
					}
					
					if(move.toRow < move.fromRow){ // left and up movement
						for(int l = Math.abs(move.toColumn - move.fromColumn) - 1; l > 0; l--){
							if(board[move.fromRow - l][move.fromColumn - l] != null){ // checks for obstructing pieces
								return false;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
