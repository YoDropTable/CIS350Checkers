package chess;

public class King extends ChessPiece {
	
	protected King(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "King";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		// general chess piece conditions
		if(super.isValidMove(move, board) == true){
			
			// up and down movement
			if(Math.abs(move.toRow - move.fromRow) == 1 && move.toColumn == move.fromColumn){
				return true;
			}
			
			// left and right movement
			if(Math.abs(move.toColumn - move.fromColumn) == 1 && move.toRow == move.fromRow){
				return true;
			}
			// diagonal movement
			if(Math.abs(move.toColumn - move.fromColumn) == 1 && Math.abs(move.toRow - move.fromRow) == 1){
				return true;
			}
			
			return false;
		}
		else return false;
	}

}
