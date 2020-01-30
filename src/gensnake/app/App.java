package gensnake.app;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gensnake.disp.Painter;


public class App implements Runnable {

	public static final int WINDOW_WIDTH = 1300, WINDOW_HEIGHT = 700, FRAME_SIZE = 180, TILE_SIZE = 4;
	public static final int POOL_SIZE = FRAME_SIZE / TILE_SIZE, POOLS_ACROSS = 7, POOLS_DOWN = 3;
	
	//public static final int WINDOW_WIDTH = 502, WINDOW_HEIGHT = 525, FRAME_SIZE = 240, TILE_SIZE = 6;
	//public static final int POOL_SIZE = FRAME_SIZE / TILE_SIZE, POOLS_ACROSS = 2, POOLS_DOWN = 2;
	
	private boolean isRunning = false;
	private Thread thread;
	
	private Painter painter;
	
	private GenePool pool;
	
	private int ticks = 0;
	
	public App() {
		pool = new GenePool();
		painter = new Painter( pool );
		new Window( WINDOW_WIDTH, WINDOW_HEIGHT, "Snake AI", painter );
		
		start();
	}
	
	// Implemented as part of Runnable
	private void start() {
		isRunning = true;
		thread = new Thread( this );
		thread.start();
	}

	// Implemented as part of Runnable
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	// Implemented as part of Runnable
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		while( isRunning ) {
			long now = System.nanoTime();
			delta += ( now - lastTime ) / ns;
			lastTime = now;
			while( delta >= 1 ) {
				tick();
				//updates++;
				delta--;
			}
			painter.render();
			//frames++;
			
			if( System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				//frames = 0;
				//updates = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		if( ticks > 2 ) {
			pool.tick();
			ticks = 0;
		}
		ticks++;

	}

	public static void main( String args[] ) {
		new App();
	}
}
