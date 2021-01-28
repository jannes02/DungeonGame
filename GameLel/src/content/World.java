package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class World {
	BufferedImage world;
	Tile grass, way;
	Chunk[] chunks;

	World() { // CONSTRUCTOR

		JSONObject obj = new JSONObject();
		obj.put("name", "mkyong.com");
		obj.put("age", 100);

		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);

		try (FileWriter file = new FileWriter("c:\\projects\\test.json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(obj);

		grass = new Tile("grass");
		way = new Tile("way");

		chunks = new Chunk[576]; // ANZAHL DER CHUNKS IN EINEM SCREEN

		int k = 0;
		for (int i = 0; i < 18; i++) {

			for (int j = 0; j < 32; j++) {
				chunks[k] = new Chunk();
				if (k % 5 == 0) {
					chunks[k].setChunk(j * 16, i * 16, grass);
				} else {
					chunks[k].setChunk(j * 16, i * 16, way);
				}
				k += 1;
			}

		}

	}

	public void draw(Graphics2D g) {

		for (int i = 0; i < chunks.length; i++) {

			g.drawImage(chunks[i].t.drawTile(0), chunks[i].x, chunks[i].y, null);
		}
	}

	private class Chunk {
		private Tile t;
		private int x;
		private int y;

		public void setChunk(int x, int y, Tile t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}

		private Tile getTile() {
			return t;

		}

	}

}
