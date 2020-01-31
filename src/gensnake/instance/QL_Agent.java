package gensnake.instance;

import java.awt.Point;
import java.util.HashMap;

import gensnake.app.App;

public class QL_Agent extends Agent {
	
	private Boundry boundry;
	
	private boolean isAppleLeft;
	private boolean isAppleRight;
	private boolean isAppleAhead;
	
	private boolean isLeftClear;
	private boolean isRightClear;
	private boolean isAheadClear;
	
	private HashMap<String, float[]> qMap;
	private boolean[] state = new boolean[7];
	private float[] qValues;
	
	private float alpha = 0.7f;
	private float gamma = 0.8f; // 0.025
	//private float gamma = 0.0f; // 0.025
	
	private Action action;
	private float reward;
	private int oldDist;
	private int dist;

	public QL_Agent( Snake snake, Apple apple, Boundry boundry ) {
		super( snake, apple );
		this.boundry = boundry;
		qValues = new float[3];
		qMap = new HashMap<String, float[]>();
		dist = distanceToApple();
	}
	
	public void tick() {
		
		state[0] = isLeftClear();
		state[1] = isRightClear();
		state[2] = isAheadClear();
		state[3] = isAppleLeft();
		state[4] = isAppleRight();
		state[5] = isAppleAhead();
		state[6] = isAppleBehind();
		
		String key = "";
		
		for( boolean s : state )
			key += Integer.toString((s ? 1 : 0));
		
		if( qMap.containsKey( key ) == false ) {
			qMap.put( key, new float[3] );
		}
		
		action = Action.valueOf( getMaxQ( qMap.get( key ) ) );
		
		if( action == Action.LEFT ) {
			snake.turnLeft();
		}
		else if( action == Action.RIGHT ) {
			snake.turnRight();
		}
	}
	
	public void reward() {
		
		String key = "";
		
		for( boolean s : state )
			key += Integer.toString((s ? 1 : 0));
		
		oldDist = dist;
		
		dist = distanceToApple();
		
		if( snake.p.equals( apple.p ) ) {
			reward = 50.0f;
		}
		else if( snake.isDead() ) {
			reward = -200.0f;
		}
		else if( oldDist > dist ) {
			reward = 0.5f;
		}
		else if( oldDist < dist ) {
			reward = -0.5f;
		}
		else {
			reward = -0.5f;
		}
		
		qValues = qMap.get(key);
		qValues[action.ordinal()] = qValues[action.ordinal()] + alpha * ( reward  + ( gamma * 10 ) - qValues[action.ordinal()] );
		qMap.put(key, qValues);
		
	}
	
	private boolean isLeftClear() {
		Point nextPosition = snake.getDirection().left().step( new Point ( snake.p ) );
		
		if( intersectSnakeBody( nextPosition ) ) {
			return false;
		}
		
		if( nextPosition.x < boundry.west() || nextPosition.x > boundry.east() ||  nextPosition.y < boundry.north() || nextPosition.y > boundry.south() ) {
			return false;
		}
		
		return true;
	}
	

	private boolean isRightClear() {
		Point nextPosition =  snake.getDirection().right().step( new Point ( snake.p ) );
		
		if( intersectSnakeBody( nextPosition ) ) {
			return false;
		}
		
		if( nextPosition.x < boundry.west() || nextPosition.x > boundry.east() ||  nextPosition.y < boundry.north() || nextPosition.y > boundry.south() ) {
			return false;
		}
		
		return true;
	}
	
	private boolean isAheadClear() {
		Point nextPosition =  snake.getDirection().step( new Point ( snake.p ) );

		if( intersectSnakeBody( nextPosition ) ) {
			return false;
		}
		
		if( nextPosition.x < boundry.west() || nextPosition.x > boundry.east() ||  nextPosition.y < boundry.north() || nextPosition.y > boundry.south() ) {
			return false;
		}
		
		return true;
	}
	
	private boolean isAppleLeft() {
		if( snake.getDirection() == Direction.NORTH && apple.getX() < snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.EAST && apple.getY() < snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.SOUTH && apple.getX() > snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.WEST && apple.getY() > snake.getY() ) {
			return true;
		}
		return false;
	}
	
	private boolean isAppleRight() {
		if( snake.getDirection() == Direction.NORTH && apple.getX() > snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.EAST && apple.getY() > snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.SOUTH && apple.getX() < snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.WEST && apple.getY() < snake.getY() ) {
			return true;
		}
		return false;
	}
	
	private boolean isAppleAhead() {
		if( snake.getDirection() == Direction.NORTH && apple.getX() == snake.getX() && apple.getY() < snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.EAST && apple.getY() == snake.getY() && apple.getX() > snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.SOUTH && apple.getX() == snake.getX() && apple.getY() > snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.WEST && apple.getY() == snake.getY() && apple.getX() < snake.getX() ) {
			return true;
		}
		return false;
	}
	
	private boolean isAppleBehind() {
		if( snake.getDirection() == Direction.NORTH && apple.getX() == snake.getX() && apple.getY() > snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.EAST && apple.getY() == snake.getY() && apple.getX() < snake.getX() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.SOUTH && apple.getX() == snake.getX() && apple.getY() < snake.getY() ) {
			return true;
		}
		else if( snake.getDirection() == Direction.WEST && apple.getY() == snake.getY() && apple.getX() > snake.getX() ) {
			return true;
		}
		return false;
	}

	public boolean intersectSnakeBody( Point p ) {
		for( int i = 0; i < snake.getBody().size() - 1; i++ ) {
			BodyPart b = snake.getBody().get(i);
			if( p.x == b.getX() && p.y == b.getY() )
				return true;
		}
		return false;
	}
	
	private int distanceToApple() {
		return Math.abs( apple.getX() - snake.getX() ) + Math.abs( apple.getY() - snake.getY() );
	}
	
	private int getMaxQ( float[] values ) {
		float max = values[0];
		int index = 0;
		for( int i = 1; i < values.length; i++ ) {
			if( values[i] > max ) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}
}

