package gensnake.instance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gensnake.app.App;


public class Instance extends InstanceObject {
	
	private Snake snake;
	private Apple apple;
	private Agent agent;
	private int iScore;
	private int iHiscore;
	private static int poolScore = 0;
	private static int poolHiscore = 0;


	public Instance( Point origin ) {
		super( origin );
		Point snakePos = new Point( origin.x + App.POOL_SIZE / 2, origin.y + App.POOL_SIZE / 2 );
		Boundry boundry = new Boundry(origin.x, origin.y, App.POOL_SIZE, App.POOL_SIZE );
		snake = new Snake( snakePos, origin );
		apple = new Apple( origin );
		agent = new QL_Agent( snake, apple, boundry );
		iScore = 0;
		iHiscore = 0;
	}

	@Override
	public void tick() {
		agent.tick();
		snake.tick();
		agent.reward();
		
		if( snake.isDead() ) {
			snake.reset();
			if( iHiscore > poolHiscore ) {
				poolHiscore = iHiscore;
			}
			if( iScore == poolScore ) {
				poolScore = 0;
			}
			iScore = 0;
		}
		
		if( snake.p.equals( apple.p ) ) {
			snake.grow();
			apple.respawn();
			iScore++;
			if( iScore > iHiscore ) {
				iHiscore = iScore;
			}
			if( iScore > poolScore ) {
				poolScore = iScore;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if( iScore == poolScore ) {
			g.setColor( Color.YELLOW );
		}
		else {
			g.setColor( Color.BLACK );
		}

		g.fillRect(p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE, App.POOL_SIZE * App.TILE_SIZE);

		if( iHiscore == poolHiscore ) {
			g.setColor( Color.CYAN );
		}
		else {
			g.setColor( Color.GREEN );
		}
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
