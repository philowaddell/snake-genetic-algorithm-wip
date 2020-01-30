package gensnake.app;

import java.awt.Dimension;

import javax.swing.JFrame;

import gensnake.disp.Painter;

public class Window {
	
	public static JFrame frame;

	public Window( int width, int height, String title, Painter painter ) {
		
		JFrame frame = new JFrame( title );
		
		frame.setPreferredSize( new Dimension( width, height ) );
		frame.setMaximumSize( new Dimension( width, height ) );
		frame.setMinimumSize( new Dimension( width, height ) );
		
		frame.add( painter );
		frame.setResizable( false );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
	}
}