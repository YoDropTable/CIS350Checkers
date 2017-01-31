package chess;

public enum Player {
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 *
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() { // change to this from move count 
		return this == BLACK ? WHITE : BLACK;
	}
}
