package content;

public class Camera {
	private int x;
	private int y;

	Camera() {
		x = Player.x;
		y = Player.y;
	}

	public float getCameraOffsetX() {

		float offsetX = 0;

		if (Player.x - 32 > this.x) {
			offsetX = 1;
			x += 1;
		}

		else if (Player.x + 32 < this.x) {
			offsetX = -1;
			x -= 1;
		}

		else {
			offsetX = 0;
		}

		System.out.println(this.x + "-------" + Player.x);
		return offsetX;
	}

	public float getCameraOffsetY() {
		float offsetY = 0;

		if (Player.y - 16 > this.y) {
			offsetY = 1;
			y += 1;
		}

		else if (Player.y + 16 < this.y) {
			offsetY = -1;
			y -= 1;
		}

		else {
			offsetY = 0;
		}

		System.out.println(this.x + "-------" + Player.x);
		return offsetY;
	}

}
