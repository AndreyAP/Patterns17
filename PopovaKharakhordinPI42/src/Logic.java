package p2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

abstract public class Logic {
	// �� ������� ���������� ���� ����-���� ������ ��������
	final static double gettingHungry = 0.0000005;	// ��������
	final static double gettingThursty = 0.0000006;	// ����� ����
	
	// �� ������� ���������� ���� ��������� ������ ������� ����� �������
	// TODO: ������ ������������ ��� ���� �������
	final static double wannaLeave = 0.05;		// ���������� ����� ����, ��� ������� ������� ���� ����� ����� �������
	
	// ����������� ��������� ����������
	// TODO: ������ ������������ ��� ���� �����
	final static double goingToTheWork = 0.7;
	
	// ����������� �������, ��������� � ������������� ���������
	final static double gonnaLeave = 0.001;				// ������� �����

	final static double hasCloth = 0.5;					// � �������� ������
	final static double hasToPrint = 0.5;				// �����������, ��� ����� ����� �����������
	final static double gonnaToCoatRoom = 0.3;			// �� ������ �� �������

	final static double wannaEat = 0.3;					// ������� ����� ����
	final static double wannaDrink = 0.2;				// ������� ����� ����
	
	final static int minPaperLimit = 10;				// ���������� ������� ������ � ����������

	// ����������� ��� �������� �����
	final static int minCash = 0, maxCash = 50;			// ���. � ����. �������
	final static int minCredit = 0, maxCredit = 200;	// ���. � ����. ����� �� �����
	final static int minPages = 1, maxPages = 10;		// ���. � ����. ������ ��� ����������
	
	// �������� �������
	final static String goingToVending = "���� � ��������� ��������";
	final static String goingToPrint = "���� ��������";
	final static String goingToCoat = "���� � ��������";
	final static String goingToCoatN = " (������� ������)";
	final static String goingToCoatY = " (�������� ������)";
	final static String goingToPost = "���� � �����";
	final static String goingToStore = "���� � �������";
	final static String goingToATM = "���� � ���������";
	final static String goingToExit = "���� �� �����";
	final static String goingToDrink = " (����)";
	final static String goingToEat = " (����)";
	final static String goingToWalk = "������";
	final static String nowWannaEat = "������� ����";
	final static String nowWannaDrink = "������� ����";
	final static String drankSome = "�����";
	final static String ateSome = "����";
	final static String printed = "����������";
	final static String gaveCloth = "���� ������";
	final static String tookCloth = "���� ������";
	final static String noCash = "�� ������� �������, ���� � ���������";
	final static String noCredit = "����� ���, ���� �� �����";
	final static String goingBack = "������������ � �������� �����";
	final static String noDrinks = "��� ������ ������, ���� �� �����";
	final static String noMeals = "� �������� ������ ���, ���� �� �����";
	final static String noPaper = "��� ������ ��� ������, ���� �� �����";
	final static String refillVending = "������ ��������� �������";
	final static String refillStore = "������ ��������� ����� � ��������";
	final static String refillCopy = "������ ��������� ������";
	
	// ��������� ���������� � ��������
	static void GeneratePersonInfo(Person p) {
		Random r = new Random();
		// �������� � ��������� ������
		p.cash = r.nextInt(maxCash - minCash) + minCash;
		p.credit = r.nextInt(maxCredit - minCredit) + minCredit;
		// ���� �� ������
		if (r.nextDouble() < hasCloth)
			p.hasCloth = true;
		// ����� �� ����
		if (r.nextDouble() < wannaDrink)
			p.wantsToDrink = true;
		// ����� �� ����
		if (r.nextDouble() < wannaEat)
			p.wantsToEat = true;
		// ����� �� �����������
		if (r.nextDouble() < hasToPrint)
			p.toPrint = r.nextInt(maxPages - minPages) + minPages;
		p.wannaDrinkProb = gettingThursty;
		p.wannaEatProb = gettingHungry;
		p.wannaLeave = gonnaLeave;
	}
	
	// ���� ������ �������
	static WayPoint WhereToGo(Person p, boolean justSpawned) {
		Random r = new Random();
		ArrayList<WayPoint> wps = Panel.getCurrentSimScreen()._waypoints;
		// ������������� �����
		Collections.shuffle(wps);
		int action;	// ���� ������ �������
		// 0..5 - �� ����� ��������
		// -1 - ������
		// -2 - �� �����

		while (true) {
			// ���� ������� ������ ��� ��������
			if (justSpawned) {
				if (p.hasCloth)
					// ������ �� ������� ������
					if (r.nextDouble() < gonnaToCoatRoom) {
							p.log.add(Logic.goingToCoat + Logic.goingToCoatN);
							action = 2;
							break;
						}
			}
			// ������� �����
			if (r.nextDouble() < p.wannaLeave) {
				// ���� ������ � ���������
				if (p.coatRoomCheck) {
						p.log.add(Logic.goingToCoat + Logic.goingToCoatY);
						action = 2;
						break;
					}
				// ������ ���
				if (!p.coatRoomCheck) {
					p.log.add(Logic.goingToExit);
					action = -2;
					break;
				}
			}
			// ����� ����
			if (p.wantsToEat) {
					p.log.add(Logic.goingToStore + Logic.goingToEat);
					action = 4;
					break;
				}
			// ����� ����
			if (p.wantsToDrink) {
				// ��� ������
				boolean goingToStore = r.nextBoolean();
				if (goingToStore) {
					p.log.add(Logic.goingToStore + Logic.goingToDrink);
					action = 4;
					break;
				} else {
					p.log.add(Logic.goingToVending + Logic.goingToDrink);
					action = 0;
					break;
				}
			}
			// ����� �����������
			if (p.toPrint > 0) {
					p.log.add(Logic.goingToPrint);
					action = 1;
					break;
				}
			
			p.log.add(Logic.goingToWalk);
			action = -1;
			break;
		}
		if (!wps.isEmpty())
			for (WayPoint wp : wps) {
				if (action >= 0 && wp.getClass().getSimpleName().equals("UsableWayPoint")) {
					UsableWayPoint uwp = (UsableWayPoint) wp;
					if (uwp.object.getType() == action)
						return wp;
				}
				if (action < 0 && !wp.getClass().getSimpleName().equals("UsableWayPoint")) {
					switch (action) {
					case -1:
						if (!wp.isSpawn)
							return wp;
						break;
					case -2:
						if (wp.isSpawn)
							return wp;
						break;
					}
				}
			}
		// ������ ���� ������
		for (WayPoint wp : wps)
			if (!wp.getClass().getSimpleName().equals("UsableWayPoint")) {
				p.log.add(Logic.goingToWalk);
				return wp;
			}
		System.out.println("null");
		return null;
	}
	// ������� � ����� ���������� ����
	static boolean FindRandomPathToAnywhere(Person person, WayPoint start, boolean justSpawned) {
		WayPoint end_point = Logic.WhereToGo(person, justSpawned);

		// �������� ��������� ���� ����� ����� � �������
		// TODO: ����� �������� ����?
		App.dfs(start, end_point, new boolean[Panel.getCurrentSimScreen()._waypoints.size()], new ArrayList<WayPoint>(), person);
		if (person.path == null) {
			Panel.getCurrentSimScreen()._persons.remove(person);
			return false;
		}
		person.goingTo = person.path.get(0);
		person.path.remove(0);
		return true;
	}
	
	// ����� ���� �� ������������� ������������� �������
	static boolean FindRandomPathToUsable(Person person, WayPoint start, int type) {
		WayPoint end_point = null;
		for ( WayPoint wp : Panel.getCurrentSimScreen()._waypoints )
			if (wp.getClass().getSimpleName().equals("UsableWayPoint")) {
				UsableWayPoint uwp = (UsableWayPoint) wp;
				if (uwp.getObject().getType() == type) {
					end_point = wp;
					break;
				}
			}

		// �������� ��������� ���� ����� ����� � �������
		// TODO: ����� �������� ����?
		App.dfs(start, end_point, new boolean[Panel.getCurrentSimScreen()._waypoints.size()], new ArrayList<WayPoint>(), person);
		if (person.path == null) {
			Panel.getCurrentSimScreen()._persons.remove(person);
			return false;
		}
		person.goingTo = person.path.get(0);
		person.path.remove(0);
		return true;
	}

	// ����� ���� �� ������
	static boolean FindRandomPathToExit(Person person, WayPoint start) {
		WayPoint end_point = null;
		for ( WayPoint wp : Panel.getCurrentSimScreen()._waypoints )
			if (!wp.getClass().getSimpleName().equals("UsableWayPoint"))
				if (wp.isSpawn) {
					end_point = wp;
					break;
				}

		// �������� ��������� ���� ����� ����� � �������
		// TODO: ����� �������� ����?
		App.dfs(start, end_point, new boolean[Panel.getCurrentSimScreen()._waypoints.size()], new ArrayList<WayPoint>(), person);
		if (person.path == null) {
			Panel.getCurrentSimScreen()._persons.remove(person);
			return false;
		}
		person.goingTo = person.path.get(0);
		person.path.remove(0);
		return true;
	}
	// ����� ���� �� �������� �����
	static boolean FindRandomPathToPoint(Person person, WayPoint start, WayPoint end) {
		// �������� ��������� ���� ����� ����� � �������
		// TODO: ����� �������� ����?
		App.dfs(start, end, new boolean[Panel.getCurrentSimScreen()._waypoints.size()], new ArrayList<WayPoint>(), person);
		if (person.path == null) {
			Panel.getCurrentSimScreen()._persons.remove(person);
			return false;
		}
		person.goingTo = person.path.get(0);
		person.path.remove(0);
		return true;
	}
}
