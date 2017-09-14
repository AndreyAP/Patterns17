package p2016;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// �������
@SuppressWarnings("serial")
public class Person implements Serializable {
	private Point2D.Double position;	// �������
	private int size;		// ������ (������?)
	private int speed;		// �������� �����������

	boolean hasCloth;		// ���� ������ � �����
	boolean coatRoomCheck;	// ������ ������ � ��������
	boolean wantsToEat;		// ����� �� ����
	boolean wantsToDrink;	// ����� �� ����
	int toPrint;			// ������� ������ ����� �����������

	double wannaEatProb;	// �����������, ��� ������� ����
	double wannaDrinkProb;	// �����������, ��� ������� ����
	double wannaLeave;		// �����������, ��� ������ �� �����

	public int cash;		// ������� ����� ����� ��������
	public int credit;		// ������� ����� ����� �� �����
	public WayPoint goingTo;// � ����� ����� ������ ����
	public ArrayList<WayPoint> path;	// ����, �� �������� ����
	public ArrayList<String> log;	// �������, ������������ � ���������
	
	public WayPoint backToStore;	// ����� �� ���������
	
	public boolean isSelected;				// ������� ��
	public boolean isWorker;				// �������� ��
	public UsableMapObject object;			// ������ ���������
	
	Person(Point2D.Double p) {
		Random r = new Random();
		position = p;
		speed = r.nextInt(3) + 1;	// ����. �������� 1...4
		size = r.nextInt(10) + 10;	// ������ 10...20
		log = new ArrayList<String>();
		
		Logic.GeneratePersonInfo(this);
	}
	
	public Point2D.Double getPosition() {
		return position;
	}

	public void setPosition(Point2D.Double position) {
		this.position = position;
	}

	public int getSize() {
		return size;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
