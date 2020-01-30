package gensnake.app;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import gensnake.instance.Agent;
import gensnake.instance.Apple;
import gensnake.instance.Instance;
import gensnake.instance.Snake;

public class GenePool {
	
	private ArrayList<Instance> pool;
	
	public GenePool() {
		
		Point origin;
		
		pool = new ArrayList<Instance>();

		for( int i = 0; i < App.POOLS_DOWN; i++ ) {
			for( int j = 0; j < App.POOLS_ACROSS; j++ ) {
				origin = new Point( j * App.POOL_SIZE, i * App.POOL_SIZE);
				pool.add( new Instance( origin ) );
			}
		}
	}
	
	public void tick() {
		for( Instance i : pool ) {
			i.tick();
		}
	}
	
	public void render( Graphics g ) {
		for( Instance i : pool ) {
			i.render(g);
		}
		
	}

}
