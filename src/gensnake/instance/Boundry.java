package gensnake.instance;

public class Boundry {
	
	private final int NORTH, EAST, SOUTH, WEST;
	
	public Boundry( int n, int e, int s, int w ) {
		this.NORTH = n;
		this.EAST = e;
		this.SOUTH = s;
		this.WEST = w;	
	}
	
	public int north() {
		return NORTH;
	}
	
	public int east() {
		return EAST;
	}
	
	public int south() {
		return SOUTH;
	}
	
	public int west() {
		return WEST;
	}

}
