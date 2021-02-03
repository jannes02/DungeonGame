package content;

public class MapTile {

	// Variablen die aus der JSON gesetzt
	private int x, y, width, height;

	// Weitere Variablen
	private Tile tile;

	// Constructor
	MapTile(int x, int y, int width, int height, String type) {
		this.x = x;
		this.y = y;
		this.tile = new Tile(type); // lol
		this.width = tile.getInt("width");
		this.height = tile.getInt("height");
	}

	public Tile getTile() {
		return tile;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
