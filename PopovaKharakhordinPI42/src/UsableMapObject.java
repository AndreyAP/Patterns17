package p2016;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
import javax.swing.Timer;

// ������������ ������
@SuppressWarnings("serial")
public class UsableMapObject extends MapObject {
	private int capacity;					// �����������
	private int has;						// ������� ��������
	private int type;						// ��� �������
	private int price;						// ���� ������
	// 0 - �������, 1 - ������, 2 - ��������, 3 - ����, 4 - �������, 5 - ��������

	public int minTime = 1000, maxTime = 5000;	// ������ ��� ��������� ���. � ����. ����� (� �������������)
	public Queue<Person> queue;		// �������
	public Person admin;			// ��� ��������� � �������
	public Timer timer;				// ������ ��� ��������� �������
	public WayPoint stayPoint;		// �����, ��� ����� ��������
	
	public UsableMapObject(Point pos, int t) {
		super(pos);
		setType(t);
		queue = new LinkedList<Person>();
	}
	
	public boolean ChangeProperties() {
        String test1 = "0", test2 = "0", test3 = "0";
        switch (getType()) {
        case 0:
            test1 = JOptionPane.showInputDialog("����������� �������� (����� ����)");
            test2 = JOptionPane.showInputDialog("������� ������� ����� ���� � ��������");
            test3 = JOptionPane.showInputDialog("���� ����");
            break;
        case 1:
            test2 = JOptionPane.showInputDialog("������� ������� ������");
            test3 = JOptionPane.showInputDialog("���� ���������� (�� ����)");
            break;
        case 2:
        	break;
        case 3:
        	break;
        case 4:
            test1 = JOptionPane.showInputDialog("����������� �������� (���-�� ���)");
            test2 = JOptionPane.showInputDialog("������� ������� ��� � ��������");
            test3 = JOptionPane.showInputDialog("���� ���");
        	break;
        case 5:
            test1 = JOptionPane.showInputDialog("����������� ��������� (�����)");
            test2 = JOptionPane.showInputDialog("������� ������� ����� � ���������");
        	break;
        }
        int cap, h, p; 
        try {
        	cap = Integer.parseInt(test1);
        	h = Integer.parseInt(test2);
        	p = Integer.parseInt(test3);
        } catch (java.lang.NumberFormatException e) {
        	return false;
        }
        if ((h > cap && getType() != 1) || cap < 0 || h < 0 || p < 0)
        	return false;
        setCapacity(cap);
        setHas(h);
        setPrice(p);
        return true;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getHas() {
		return has;
	}

	public void setHas(int has) {
		this.has = has;
	}
}
