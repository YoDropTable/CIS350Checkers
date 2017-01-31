package chess;

public class Move {

	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
	}

	public Move(int fromRow, int fromColumn, int toRow, int toColumn) { // move constructor, parameters for "from" location and "to" location
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}
