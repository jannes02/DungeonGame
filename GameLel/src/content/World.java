package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
<<<<<<< HEAD
=======
import java.io.FileWriter;
>>>>>>> refs/remotes/origin/master
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {
	BufferedImage world;
	Tile grass, way;
<<<<<<< HEAD
	MapTile mapTile;
=======
	Chunk[] chunks;
>>>>>>> refs/remotes/origin/master
	HashMap<String, String> tMap;

	World() {
<<<<<<< HEAD

		// genWorld();

		HashMap<Integer, MapTile> hm = new HashMap<Integer, MapTile>();

		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader("rsc/worlds/test.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;
		System.out.println(jo);
=======
>>>>>>> refs/remotes/origin/master

<<<<<<< HEAD
		/*
		 * getting int k = 0; for (int i = 0; i < 18; i++) {
		 * 
		 * for (int j = 0; j < 32; j++) {
		 * 
		 * String str = (String) jo.;
		 * 
		 * mapTile = new MapTile(k, j, i, i, str)
		 * 
		 * hm.put(k, mapTile);
		 * 
		 * 
		 * System.out.println((String) jo.get("X" + i + "Y" + j)); k += 1;
		 * 
		 * } } }
		 * 
		 * public void draw(Graphics2D g) {
		 * 
		 * for (int i = 0; i < chunks.length; i++) {
		 * 
		 * g.drawImage(chunks[i].t.drawTile(), chunks[i].x, chunks[i].y, null); }
		 */
=======
		grass = new Tile("grass");
		way = new Tile("way");

		// genWorld();

		chunks = new Chunk[576]; // ANZAHL DER CHUNKS IN EINEM SCREEN
		// parsing file "JSONExample.json"
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader("rsc/worlds/test.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;

		// getting
		int k = 0;
		for (int i = 0; i < 18; i++) {

			for (int j = 0; j < 32; j++) {
				chunks[k] = new Chunk();
				String str = (String) jo.get("X" + i + "Y" + j);
				String str2 = "grass";
				if (str.equals(str2)) {
					chunks[k].setChunk(j * 16, i * 16, grass);
				} else {
					chunks[k].setChunk(j * 16, i * 16, way);
				}
				System.out.println((String) jo.get("X" + i + "Y" + j));
				k += 1;

			}
		}
	}

	@SuppressWarnings("unchecked")
	public void genWorld() { // CONSTRUCTOR

		JSONObject obj = new JSONObject();

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
				obj.put("X" + i + "Y" + j, chunks[k].getTile().getTileName());
				k += 1;

			}

		}

		try (FileWriter file = new FileWriter("rsc/worlds/test.json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(obj.toString());

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

>>>>>>> refs/remotes/origin/master
	}

}
