package chess;

public abstract class ChessPiece implements IChessPiece{
	private Player owner;
	
	protected ChessPiece(Player player) {
		this.owner = player;
	}
 
	public abstract String type();
	
	/** *************************************************************************************************************** */
	
	public Player player(){
		return owner;
	}
	
	/** *************************************************************************************************************** */
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		// basic move conditions (is there an obstruction, is the "to" square empty
		if(board[move.fromRow][move.fromColumn] != null){		// making sure moving a valid piece
			if(board[move.toRow][move.toColumn] == null){		// checking that the "to" square is either null, or occupied by opposing player
				return true;
			}
			else if(board[move.fromRow][move.fromColumn].player() != board[move.toRow][move.toColumn].player()){
				return true;
			}
			return false;
		}
		return false;
	}
}
