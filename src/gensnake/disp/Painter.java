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
		
		g.setColor( Color.BLACK );
		g.fillRect( 0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);

		/*g.setColor( Color.GREEN );
		g.fillRect(0, 0, App.FRAME_SIZE, App.TILE_SIZE);
		g.fillRect(0, 0, App.TILE_SIZE, App.FRAME_SIZE);
		g.fillRect(App.FRAME_SIZE - App.TILE_SIZE, 0, App.TILE_SIZE, App.FRAME_SIZE);
		g.fillRect(0, App.FRAME_SIZE - App.TILE_SIZE, App.FRAME_SIZE, App.TILE_SIZE);*/
		
		pool.render( g );
		
		bs.show();
		
	}

}