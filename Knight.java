package chess;

public class Knight extends ChessPiece {
	
	protected Knight(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "Knight";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		if(super.isValidMove(move, board) == true){
			// checks each Knight-specific condition // does not check for pieces in the way because knights are awesome and don't care
			if(move.toRow == move.fromRow + 2){		// if moving down 
				if(move.toColumn == move.fromColumn + 1){ // moving down and right
					return true;
				}
				if(move.toColumn == move.fromColumn - 1){ // moving down and left
					return true;
				}
			}
			
			if(move.toRow == move.fromRow - 2){		// if moving up
				if(move.toColumn == move.fromColumn + 1){ // moving up and right
					return true;
				}
				if(move.toColumn == move.fromColumn - 1){ // moving up and left
					return true;
				}
			}
			
			if(move.toColumn == move.fromColumn + 2){	//if moving right
				if(move.toRow == move.fromRow + 1){	// if moving right and down
					return true;
				}
				if(move.toRow == move.fromRow - 1){	// if moving right and up
					return true;
				}
			}
			
			if(move.toColumn == move.fromColumn - 2){	// if moving left
				if(move.toRow == move.fromRow + 1){	// if moving left and down
					return true;
				}
				if(move.toRow == move.fromRow - 1){ // if moving left and up
					return true;
				}
			}
			
			return false;

		}
		else return false;
	}

}
