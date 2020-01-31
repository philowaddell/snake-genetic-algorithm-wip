package gensnake.instance;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import gensnake.app.App;




public class Snake extends InstanceObject {
	
	private ArrayList<BodyPart> body;
	private int size;
	private final Point origin;
	
	private Direction direction;
	
	private Random r;

	public Snake(Point p, Point origin ) {
		super(p);
		this.origin = origin;
		
		r = new Random();
		
		direction = Direction.valueOf(r.nextInt(4));
		size = 5;
		body = new ArrayList<BodyPart>();
	}

	@Override
	public void tick() {

		if( body.size() >= size ) {
			body.remove(0);
		}	
		
		if( body.size() != size ) {
			body.add( new BodyPart( new Point( direction.step( p ) ) ) );
		}	
	}
	
	@Override
	public void render( Graphics g ) {
		for( BodyPart b : body )
			b.render(g);
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void turnLeft() {
		direction = direction.left();
	}
	
	public void turnRight() {
		direction = direction.right();
	}
	
	public boolean isDead() {
		
		if( p.x < origin.x + 1 || p.x > origin.x + App.POOL_SIZE - 1 ||  p.y < origin.y + 1 || p.y > origin.y + App.POOL_SIZE - 1 ) {
			return true;
		}
		
		for( int i = 0; i < body.size() - 1; i++ ) {
			BodyPart b = body.get(i);
			if( p.x == b.getX() && p.y == b.getY() )
				return true;
		}
		
		return false;
	}
	
	public void reset() {
		body.clear();
		size = 5;
		p.x = origin.x + App.POOL_SIZE / 2;
		p.y = origin.y + App.POOL_SIZE / 2;
	}

	public void grow() {
		size++;
	}

	public ArrayList<BodyPart> getBody() {
		return body;
	}

}
