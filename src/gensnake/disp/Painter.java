package gensnake.disp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gensnake.app.App;
import gensnake.app.GenePool;

public class Painter extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private GenePool pool;
	
	public Painter( GenePool pool ) {
		this.pool = pool;
		
	}

	public void render() {
		this.requestFocus();
		
		BufferStrategy bs = this.getBufferStrategy();
		if( bs == null ) {
			this.createBufferStrategy( 3 );
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		pool.render( g );
		
		bs.show();
	}

}
