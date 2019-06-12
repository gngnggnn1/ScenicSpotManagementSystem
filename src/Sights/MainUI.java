package Sights;

import java.io.IOException;
import java.util.Scanner;

import Car.Parking;

public class MainUI {
	static String notice = "";
	public static Scanner sc = new Scanner(System.in);
	static Graph graph = new Graph();
    public static void mainmenu() {
    	System.out.println("********************");
    	System.out.println("        景区管理系统");
    	System.out.println("********************");
    	System.out.println("管理员公告: "+notice);
    	System.out.println(
    			"1.输出景区景点分布图\r\n" + 
    			"2.景点的查找\r\n" + 
    			"3.输出导游线路图\r\n" + 
    			"4.两个景点间的最短路径和最短距离\r\n" + 
    			"5.停车场车辆进出记录信息\r\n" +
    			"6.管理员菜单\r\n" +
    			"0.退出");
    }
    
    public static void displaySights() {
    	boolean flag = true;
    	while(flag) {
    		System.out.println("请选择显示方式:");
        	System.out.println(
        			"1.输出邻接表\r\n" + 
        			"2.输出邻接矩阵\r\n" +
        			"3.按受欢迎程度排序景点\r\n" +
        			"0.退出");
        	int choice = sc.nextInt();
        	switch(choice) {
        	
        	case 1:
        		graph.printSightList(graph);
        		break;
        	
        	case 2:
        		graph.createSightMatrix(graph);
        		graph.printSightMatrix(graph);
        		break;
        	case 3:
        		graph.welcomeSort();
        	case 0:
        		flag = false;
        		break;
        	}
        	
    	}
    }
    
    public static void addVer() {
    	System.out.println("请输入你要添加的景点，以及它可以和哪个结点连通(以新景点名 受欢迎程度 路程 原来的景点名 景点简介输入)");
		String newFolname = sc.next();//将新景点设置为边的尾节点
		int newWelcomed = sc.nextInt();
		int newWeight = sc.nextInt();
		String newPrename = sc.next();//原节点为边的头节点 北门
		String description = sc.next();
		graph.addVertex(newFolname, newWelcomed, newWeight, newPrename,description);
    }
    
    public static void search() {
    	System.out.println("请输入你要查找的关键字");
    	String keyWord = sc.next();
    	graph.searchSight(keyWord);
    }
    
    
    public static void shortPath() {
    	System.out.println("请输入起点");
    	String startVer = sc.next();
    	graph.createSightMatrix(graph);
    	graph.shortestPath(startVer);
    }
    
    public static void admin() {
    	boolean flag = true;
    	System.out.println("请输入密码,默认123");
    	int passwordIn = sc.nextInt();
    	while(flag) {
	    	switch(passwordIn) {
	    	case 123:
	    		System.out.println("1.添加景点\r\n" + 
	    						   "2.删除一条路\r\n" +
	    						   "3.删除一个景点\r\n" +
	    						   "4.发布管理员公告\r\n" +
	    						   "0.退出");
	    		int choice = sc.nextInt();
	    		switch(choice) {
	    		case 1:
	    			addVer();
	    			break;
	    		case 2:
	    			delePath();
	    			break;
	    		case 3:
	    			deleVer();
	    			break;
	    		case 4:
	    			adminnotice();
	    			break;
	    		case 0:
	    			flag = false;
	    			return;
	    		}
	    	
	    	default:
	    		break;
	    	}
	    }
    }
    
    public static void adminnotice() {
    	System.out.println("请输入你要发布的通知");
    	notice = sc.next();
    }
    
    public static void delePath() {
    	System.out.println("请输入要删除的路径（按照景点 景点格式输入）");
    	String sight1 = sc.next();
    	String sight2 = sc.next();
    	graph.deleEdge(sight1, sight2);
    }
    
    public static void deleVer() {
    	System.out.println("请输入要删除的景点");
    	String ver = sc.next();
    	graph.deleVer(ver);
    }
    
    public static void guideWay() {
    	System.out.println("输入起点 终点");
    	String begin = sc.next();
		String end = sc.next();
		graph.createSightMatrix(graph);
		graph.getHamiltonCircuit(begin, end);
    }
    
    private static void parking() {		
		boolean flag = true;
		// TODO 自动生成的方法存根
		Parking parking = new Parking(2,5);// 两个车位
		System.out.println("            **停车场管理程序**                   ");
		System.out.println("============================================");
		System.out.println("**========================================**");
		System.out.println("**A---汽车进车场===B---自动模拟======D---汽车出车场**");
		System.out.println("**============E--退出程序====================**");
		System.out.println("**========================================**");
		System.out.println("============================================");
		while(flag){
			System.out.print("请选择:");
			String choice = sc.next();
			switch (choice.toUpperCase() ) {
			case "A":
				parking.carEnter();
				break;
			case "D":
				parking.carExit();
				break;
			case "E":
				flag = false;
				break;

			default:
				System.out.println("请输入正确的选项！");
				break;
			}
		}
	}
    
    public static void main(String args[]) throws IOException {
    	graph.getInfo();
    	graph.createSightMatrix(graph);
    	while(true) {
    		mainmenu();
    		System.out.println("请输入选项:");
    		int choice = sc.nextInt();
    		switch(choice) {
    		
    		case 1:
    			displaySights();
    			break;
    		
    		case 2 :
    			search();
    			break;
    		
    		case 3:
    			guideWay();
    			break;
    		
    		case 4:
    			shortPath();
    			break;
    		
    		case 5:
    			parking();
    			break;
    		
    		case 6:
    			admin();
    			break;
    		
    		case 0:
    			System.out.println("感谢使用，再会!");
    			return;
    		
    		default:
				System.out.println("请输入0-6以内的选项！");
				break;
    		}
    	}    
    	
    }
}
