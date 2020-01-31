package gensnake.instance;

public abstract class Agent {
	
	protected Snake snake;
	protected Apple apple;
	
	public Agent( Snake snake, Apple apple ) {
		this.snake = snake;
		this.apple = apple;
	}
	
	public abstract void tick();

	public abstract void reward();
	
}
