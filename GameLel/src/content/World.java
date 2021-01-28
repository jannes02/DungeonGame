package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class World {
	BufferedImage world;
	Tile grass;
	Chunk[] chunks = new Chunk[5];
	
	World(){
		grass = new Tile();
		

			
			chunks[0] = new Chunk();	
			chunks[0].setChunk(0, 0, grass);
			
			chunks[1] = new Chunk();	
			chunks[1].setChunk(16, 0, grass);
			
			chunks[2] = new Chunk();	
			chunks[2].setChunk(32, 0, grass);
			
			chunks[3] = new Chunk();	
			chunks[3].setChunk(16 * 3, 0, grass);
			
			chunks[4] = new Chunk();	
			chunks[4].setChunk(16, 16, grass);
		}
		
	
	
	public void draw(Graphics2D g) {
		
		for (int i = 0; i < chunks.length; i++) {
			
		
		g.drawImage(chunks[i].t.drawTile(0), chunks[i].x, chunks[i].y, null);
		}
	}
	
	private class Chunk{
		private Tile t;
		private int x;
		private int y;
		
		public void setChunk(int x, int y, Tile t){
			this.x = x;
			this.y = y;
			this.t = t;
		}
		
		private Tile getTile() {
			return t;
			
		}
		
	}
	
}
