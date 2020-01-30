package gensnake.instance;

import java.awt.Point;
import java.util.HashMap;

public enum Direction {
	
	NORTH(0),
	NORTH_EAST(1),
	EAST(2),
	SOUTH_EAST(3),
	SOUTH(4),
	SOUTH_WEST(5),
	WEST(6),
	NORTH_WEST(7);

    private int value;
    private static HashMap<Object, Object> map = new HashMap<>();

    private Direction(int value) {
        this.value = value;
    }

    static {
        for (Direction direction : Direction.values()) {
            map.put(direction.value, direction);
        }
    }

    public static Direction valueOf(int direction) {
        return (Direction) map.get(direction);
    }

    public int getValue() {
        return value;
    }
    
    public Direction left() {
    	if( this.ordinal() == 0 ) {
    		return values()[6];
    	}
    	else {
    		return values()[ordinal() - 2];
    	}
    }
    
    public Direction right() {
    	if( this.ordinal() == 6 ) {
    		return values()[0];
    	}
    	else {
    		return values()[ordinal() + 2];
    	}
    }
    
    public Point step( Point p ) {
    	switch ( this.ordinal() ) {
    		case 0:
    			p.y--;
    			break;
    		case 2:
    			p.x++;
    			break;
    		case 4:
    			p.y++;
    			break;
    		case 6:
    			p.x--;
    			break;
			default: 
				break;
    	}
				
		return p;
    			
	}
}

