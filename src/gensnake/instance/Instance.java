package gensnake.instance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import gensnake.app.App;


public class Instance extends InstanceObject {
	
	private Snake snake;
	private Apple apple;
	private Agent agent;

	public Instance( Point origin ) {
		super( origin );
		Point snakePos = new Point( origin.x + App.POOL_SIZE / 2, origin.y + App.POOL_SIZE / 2 );
		snake = new Snake( snakePos, origin, App.POOL_SIZE );
		apple = new Apple( origin );
		agent = new Agent( snake, apple );
	}

	@Override
	public void tick() {
		snake.tick();
		if( snake.p.equals( apple.p ) ) {
			snake.grow();
			apple.respawn();
		}	
		agent.tick();
	}

	@Override
	public void render(Graphics g) {
		
		
		
		g.setColor( Color.GREEN );
		g.fillRect(p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, (App.POOL_SIZE  + 1) * App.TILE_SIZE + App.TILE_SIZE, App.TILE_SIZE);
		g.fillRect(p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE);
		g.fillRect(p.x * App.TILE_SIZE, (p.y + App.POOL_SIZE) * App.TILE_SIZE, (App.POOL_SIZE + 1) * App.TILE_SIZE , App.TILE_SIZE);
		g.fillRect((p.x + App.POOL_SIZE) * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE);
		
		
		snake.render(g);
		apple.render(g);
		
		
		//g.fillRect(p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE, App.TILE_SIZE);
		//g.fillRect(p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE);
		//g.fillRect(p.x + App.FRAME_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE);
		//g.fillRect(p.x * App.POOL_SIZE, p.y + App.FRAME_SIZE, (App.POOL_SIZE + 1) * App.TILE_SIZE, App.TILE_SIZE);
		
		
	}
}
