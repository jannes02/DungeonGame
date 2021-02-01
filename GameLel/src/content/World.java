package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {
	BufferedImage world;
	Tile grass, way;
	MapTile mapTile;
	HashMap<String, String> tMap;

	World() {

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
	}

}
