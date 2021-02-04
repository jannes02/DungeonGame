package content;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {
	int AnzahlMTiles;

	BufferedImage world;
	Tile grass, way;

	HashMap<String, JSONObject> joMap;
	HashMap<Integer, MapTile> mtList;

	World() {
		System.out.println("HIIIII");

	}

	public void loadMap() {

		joMap = new HashMap<String, JSONObject>();
		mtList = new HashMap<Integer, MapTile>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("rsc/worlds/test.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jo = (JSONObject) obj;

			joMap.putAll(jo);

			String maxKey = joMap.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().size()))
					.get().getKey();

			AnzahlMTiles = Integer.parseInt(maxKey.substring(7));

			for (int i = 0; i < AnzahlMTiles; i++) {

				long lx = (long) joMap.get("MapTile" + i).get("x");
				long ly = (long) joMap.get("MapTile" + i).get("y");
				long lw = (long) joMap.get("MapTile" + i).get("w");
				long lh = (long) joMap.get("MapTile" + i).get("h");
				String type = (String) joMap.get("MapTile" + i).get("type");

				int x = (int) lx;
				int y = (int) ly;
				int w = (int) lw;
				int h = (int) lh;

				MapTile mt = new MapTile(x, y, w, h, type);
				mtList.put(i, mt);
			}

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void saveMap() {

		JSONObject jsonMTiles = new JSONObject();

		int k = 0;
		for (int i = 0; i < 18; i++) {

			for (int j = 0; j < 32; j++) {

				JSONObject jsonMtileVars = new JSONObject();
				jsonMtileVars.put("x", j * 16);
				jsonMtileVars.put("y", i * 16);
				jsonMtileVars.put("w", 16);
				jsonMtileVars.put("h", 16);
				if (k % 5 == 0) {
					jsonMtileVars.put("type", "way");
					System.out.println("GRASS");
				} else {
					jsonMtileVars.put("type", "grass");
					System.out.println("WAY");
				}

				jsonMTiles.put("MapTile" + k, jsonMtileVars);

				k += 1;

			}
		}

		try (FileWriter file = new FileWriter("rsc/worlds/test.json")) {
			file.write(jsonMTiles.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {

		for (int i = 0; i < AnzahlMTiles; i++) {

			MapTile mt = mtList.get(i);

			g.drawImage(mt.getTile().drawTile(), mt.getX(), mt.getY(), null);
		}
	}

}
