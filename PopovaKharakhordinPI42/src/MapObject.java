package p2016;

import java.awt.Point;
import java.io.Serializable;

// ������ �� �����
@SuppressWarnings("serial")
abstract public class MapObject implements Serializable {
	// ����������
	private Point p; 
	
	MapObject(Point p0) {
		p = p0;
	}

	public Point getP() {
		return p;
	}

	public void setXy(Point p0) {
		this.p = p0;
	}
	
}
