package p2016;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

// waypoint
@SuppressWarnings("serial")
public class WayPoint extends MapObject implements Serializable {

	boolean isSpawn;			 		// ����� ��������?
	ArrayList<WayPoint> _connected;		// ������� �����
	
	WayPoint(Point p0, boolean spawn) {
		super(p0);
		isSpawn = spawn;
		_connected = new ArrayList<WayPoint>();
	}
}
