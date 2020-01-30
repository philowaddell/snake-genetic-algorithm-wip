package gensnake.instance;

import java.awt.Graphics;
import java.awt.Point;

public abstract class InstanceObject {
		
	protected Point p;
	
	public InstanceObject( Point p ) {
		this.p = p;
	}

	public abstract void tick();
	public abstract void render( Graphics g );
	
	public void setPos( Point p ) {
		this.p = p;
	}
	
	public Point getPos() {
		return p;	
	}

	public int getX() {
		return p.x;
	}

	public void setX(int x) {
		this.p.x = x;
	}

	public int getY() {
		return p.y;
	}

	public void setY(int y) {
		this.p.y = y;
	}
}
