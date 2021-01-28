package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class World {
	BufferedImage world;
	Tile grass;
	Chunk[] chunks;
	
	World(){
		grass = new Tile();
		
			chunks = new Chunk[576]; //ANZAHL DER CHUNKS IN EINEM SCREEN

			int k = 0;
			for (int i = 0; i < 18; i++) {
				
				for (int j = 0; j < 32; j++) {
					chunks[k] = new Chunk();	
					chunks[k].setChunk(j * 16, i * 16, grass);
					k += 1;
				}
			}
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
