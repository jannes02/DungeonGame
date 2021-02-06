package content;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class World {

	private int AnzahlMTiles;

	BufferedImage world;
	Tile grass, way;

	HashMap<String, JSONObject> joMap;
	HashMap<Integer, MapTile> mtList;

	private Thread loadWorldThread;
	private Runnable loadWorld;

	private int mapHeight;
	private int mapWidth;
	private int countOfTiles;

	private String loadingStatus;

	private boolean worldLoaded;

	World() {

		loadWorldThread = new Thread(loadWorld);

		loadWorld = new Runnable() {
			public void run() {

				System.out.println("FIINISHED");
				loadMap();

			}
		};

		// saveMap();
	}

	@SuppressWarnings("unchecked")
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

			mapHeight = (int) (long) joMap.get("mapData").get("MapHeight");
			mapWidth = (int) (long) joMap.get("mapData").get("MapWidth");
			countOfTiles = mapHeight * mapWidth;

			for (int i = 0; i < countOfTiles; i++) {

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

				String s = "=";
				int n = i / 400;
				loadingStatus = IntStream.range(0, n).mapToObj(j -> s).collect(Collectors.joining(""));
			}

		} catch (IOException |

				ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ANZAHL MAPTILES: " + countOfTiles);
		System.out.println("FIINISHED");
		worldLoaded = true;
	}

	@SuppressWarnings("unchecked")
	public void saveMap() {

		JSONObject jsonMTiles = new JSONObject();

		int k = 0;
		for (int i = 0; i < 100; i++) {

			for (int j = 0; j < 200; j++) {

				JSONObject jsonMtileVars = new JSONObject();
				jsonMtileVars.put("x", j * 16);
				jsonMtileVars.put("y", i * 16);
				jsonMtileVars.put("w", 16);
				jsonMtileVars.put("h", 16);
				if (k % 7 == 0) {
					jsonMtileVars.put("type", "way");
					System.out.println("GRASS");
				} else {
					jsonMtileVars.put("type", "grass");
					System.out.println("WAY");
				}

				jsonMTiles.put("MapTile" + k, jsonMtileVars);

				System.out.print(k);

				k += 1;

			}
			JSONObject mapData = new JSONObject();
			mapData.put("MapHeight", 100);
			mapData.put("MapWidth", 200);
			jsonMTiles.put("mapData", mapData);
		}

		try (FileWriter file = new FileWriter("rsc/worlds/test.json")) {
			file.write(jsonMTiles.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		if (worldLoaded) {
			for (int i = 0; i < countOfTiles; i++) {

				MapTile mt = mtList.get(i);

				g.drawImage(mt.getTile().drawTile(), mt.getX(), mt.getY(), null);
			}
		} else {

			g.setColor(Color.RED);
			g.drawString("WORLD IS LOADING..." + loadingStatus, 40, 40);

		}

	}

	public MapTile getHoveredMaptile() {

		int tileNO = ((GamePanel.MOUSE_Y / 16) * 32) - (32 - (GamePanel.MOUSE_X / 16)) + 32;
		System.out.println(tileNO);
		return mtList.get(tileNO);

	}

	public void startLoadWorld() {
		loadWorldThread = new Thread(loadWorld);
		loadWorldThread.start();

	}

	public boolean isWorldLoaded() {
		return worldLoaded;
	}

}
