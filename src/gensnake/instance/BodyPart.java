package gensnake.instance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import gensnake.app.App;

public class BodyPart extends InstanceObject {
	
	public BodyPart( Point p ) {
		super(p);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor( Color.WHITE );
		g.fillRect( p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE );
		g.setColor( Color.BLACK );
		g.drawRect( p.x * App.TILE_SIZE, p.y * App.TILE_SIZE, App.TILE_SIZE, App.TILE_SIZE );	
	}

}
