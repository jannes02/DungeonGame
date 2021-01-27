package content;

import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class UI_object {
	int x, y, b, h;
	PointerInfo m;
	
	public void setObject(int x, int y, int b, int h) {
		this.b = b;
		this.h = h;
		this.x = x;
		this.y = y;
	}
	
	public boolean getStatus() {
		
		m = MouseInfo.getPointerInfo();
		
		if ((m.getLocation().x >= x && m.getLocation().x <= x + b) && (m.getLocation().y >= y && m.getLocation().y <= y + h)) {
			return true;
		}
		else return false;
	}
}
