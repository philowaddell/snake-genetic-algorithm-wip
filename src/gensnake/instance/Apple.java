package gensnake.instance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import gensnake.app.App;

public class Apple extends InstanceObject {
	
	private static Random r = new Random();
	private Point origin;

	public Apple( Point origin ) {
		super( new Point( origin.x + r.nextInt( ( App.POOL_SIZE ) - 2) + 1 , origin.y + r.nextInt( ( App.POOL_SIZE ) - 2) + 1 ) );
		this.origin = origin;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor( Color.RED );
		g.fillRect( p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE );
		
	}

	public void respawn() {
		p.x = origin.x + r.nextInt( ( App.POOL_SIZE ) - 2) + 1;
		p.y = origin.y + r.nextInt( ( App.POOL_SIZE ) - 2) + 1;
	}
}
