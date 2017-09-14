package p2016;

import java.awt.Point;
import java.io.Serializable;

// �����
@SuppressWarnings("serial")
public class Wall extends MapObject implements Serializable {
	private Point wallEnd;	// ������ ������ ���� �����
	
	Wall(Point p0, Point p) {
		super(p0);
		wallEnd = p;
	}

	public Point getWallEnd() {
		return wallEnd;
	}

	public void setWallEnd(Point wallEnd) {
		this.wallEnd = wallEnd;
	}

}
