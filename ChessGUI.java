package chess;

import javax.swing.JFrame;

public class ChessGUI {
	public static void main(String[] args){
		JFrame frame = new JFrame("Chess Game");		// sets up GUI
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel); 
		
		frame.pack();
		frame.setVisible(true);
	}
}
