package chess;

public class Pawn extends ChessPiece{
	Player player;
	
	protected Pawn(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public String type(){
		return "Pawn";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board){
		// general chess piece conditions
		if(super.isValidMove(move, board) == true){
			if(super.player() == player.WHITE){ // WHITE piece conditions
				if(move.fromRow == 6 && (move.fromRow - move.toRow) == 2 && move.fromColumn == move.toColumn && board[move.toRow + 1][move.toColumn] == null && board[move.toRow][move.toColumn] == null){
					return true;
				}
				
				if((move.fromRow - move.toRow) == 1 && move.fromColumn == move.toColumn){
					return true;
				}
				
				if(Math.abs(move.fromRow - move.toRow) == 1 && Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == player.BLACK){
					return true;
				}
				
				return false;
			}
		
		
			if(super.player() == player.BLACK){ // BLACK piece conditions
				if(move.fromRow == 1 && (move.toRow - move.fromRow) == 2 && move.fromColumn == move.toColumn && board[move.toRow - 1][move.toColumn] == null && board[move.toRow][move.toColumn] == null){
					return true;
				}
				
				if((move.toRow - move.fromRow) == 1 && move.fromColumn == move.toColumn){
					if(board[move.toRow][move.toColumn] == null){
						return true;
					}
				}
				
				if(Math.abs(move.fromRow - move.toRow) == 1 && Math.abs(move.fromColumn - move.toColumn) == 1 && board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() == player.WHITE){
					return true;
				}
				
				return false;
			}
		}
		return false;
	}
}
		
	

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//										// if first move for black pawns
//			if(move.fromRow == 1 && board[move.fromRow][move.fromColumn].player() == Player.BLACK){ 
//				if(move.toRow == 2 && move.fromColumn == move.toColumn){//(move.toColumn - move.fromColumn) <= Math.abs(1)){
//					return true;
//				}
//				
//				if(move.toRow == 3 && move.fromColumn == move.toColumn){		// if moving 2 spaces forward
//					return true;
//				}
//			}
//										// if first move for white pawns
//			else if(move.fromRow == 6 && board[move.fromRow][move.fromColumn].player() == Player.WHITE){ 
//					if(move.toRow == 5 && move.fromColumn == move.toColumn){
//						return true;
//					}
//					
//					if(move.toRow == 4 && move.fromColumn == move.toColumn){	// if moving 2 spaces forward
//						return true;
//					}
//				}
//			
//			// diagonal capture for black pawns
//			else if(move.toRow == move.fromRow + 1 && Math.abs(move.toColumn - move.fromColumn) == 1 && board[move.fromRow][move.fromColumn].player() == Player.BLACK){
//				if(board[move.toRow][move.toColumn].player() == Player.WHITE){	
//						return true;	
//				}
//			}
//			
//			// diagonal capture for white pawns
//			else if(move.toRow == move.fromRow - 1 && Math.abs(move.toColumn - move.fromColumn) == 1 && board[move.fromRow][move.fromColumn].player() == Player.WHITE){
//				if(board[move.toRow][move.toColumn] != null){
//					if(board[move.fromRow][move.fromColumn].player() != board[move.toRow][move.toColumn].player()){
//						return true;
//					}
//				}
//			}
//			
//			// any other 1 space move for black pawns
//			else if(board[move.fromRow][move.fromColumn].player() == Player.BLACK){
//				if(move.fromRow + 1 == move.toRow && move.fromColumn == move.toColumn && board[move.toRow][move.toColumn] == null){
//					return true;
//				}
//			}
//			// any other 1 space move for white pawns
//			else if(board[move.fromRow][move.fromColumn].player() == Player.WHITE){
//				if(move.fromRow - 1 == move.toRow && move.fromColumn == move.toColumn && board[move.toRow][move.toColumn] == null){
//					return true;
//				}
//			}
//			else return false;
//			
//		}
//		return false;
//	}
	

