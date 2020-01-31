package gensnake.instance;

import java.awt.Point;
import java.util.HashMap;

public enum Direction {
	
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);

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
    		return values()[3];
    	}
    	else {
    		return values()[ordinal() - 1];
    	}
    }
    
    public Direction right() {
    	if( this.ordinal() == 3 ) {
    		return values()[0];
    	}
    	else {
    		return values()[ordinal() + 1];
    	}
    }
    
    public Point step( Point p ) {
    	switch ( this.ordinal() ) {
    		case 0:
    			p.y--;
    			break;
    		case 1:
    			p.x++;
    			break;
    		case 2:
    			p.y++;
    			break;
    		case 3:
    			p.x--;
    			break;
			default: 
				break;
    	}
				
		return p;
    			
	}
}

