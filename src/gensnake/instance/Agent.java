package gensnake.instance;

public class Agent {
	
	private Snake snake;
	private Apple apple;
	
	public Agent( Snake snake, Apple apple ) {
		this.snake = snake;
		this.apple = apple;
	}
	
	public void tick() {
		if( snake.getDirection() == Direction.NORTH ) {
			if( apple.getX() < snake.getX() ) {
				snake.turnLeft();
			}
			else if( apple.getX() > snake.getX() ) {
				snake.turnRight();
			}
		}
		else if( snake.getDirection() == Direction.EAST ) {
			if( apple.getY() < snake.getY() ) {
				snake.turnLeft();
			}
			else if( apple.getY() > snake.getY() ) {
				snake.turnRight();
			}
		}
		else if( snake.getDirection() == Direction.SOUTH ) {
			if( apple.getX() > snake.getX() ) {
				snake.turnLeft();
			}
			else if( apple.getX() < snake.getX() ) {
				snake.turnRight();
			}
			
		}
		else if( snake.getDirection() == Direction.WEST ){
			if( apple.getY() > snake.getY() ) {
				snake.turnLeft();
			}
			else if( apple.getY() < snake.getY() ) {
				snake.turnRight();
			}
		}
	}

}
