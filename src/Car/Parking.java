package Car;

import java.util.Scanner;

/*
 * 停车场系统管理类
 * 包括：汽车的进入以及出去
 */
public class Parking {
	
	private Stack<Car> enterStack;//存放进入的汽车
	private Stack<Car> exitStack;//存放回滚退出的汽车
	private Queue tempParking;//便道
	private int costPerHour;//每小时的价格（超过一小时按一小时算）
	private String timeRegix;//时间的正则表达式
	Scanner sc = new Scanner(System.in);

	public Parking(int size,int costPerHour){
		this.enterStack = new Stack<Car>(size);
		this.exitStack = new Stack<Car>(size);
		this.tempParking = new Queue();
		this.costPerHour = costPerHour;
		this.timeRegix = "[0-2][0-9]+:[0-6][0-9]";
	}
	
	
	public void carEnter(){
		System.out.print("车牌为：");
		int num = sc.nextInt();
		System.out.print("进场时刻(xx:xx)：");
		String arvTime = sc.next();
		while(!arvTime.matches(timeRegix)){//正则表达式判断格式正误
			System.out.print("请按照格式输入(xx:xx)：");
			arvTime = sc.next();
		}
		
		
		if(!tempParking.isEmpty()||enterStack.isFull()){
			//如果便道不是空的或者停车场满了，那新来的车就放在便道上
			System.out.println("停车场已满!请在临时车道上等候!");
			tempParking.enQueue(new Car(num,arvTime));			
		}else{
			//如果停车场没有满并且便道也为空，则新来的车直接开入停车场
			enterStack.push(new Car(num,arvTime));
			System.out.println("该车已进入停车场："+enterStack.getSize()+"号车道！");
		}
	}
	
	public void carExit(){
		System.out.println("车牌为：");
		int num = sc.nextInt();
		System.out.print("出场时刻：");
		String levTime = sc.next();
		while(!levTime.matches(timeRegix)){//对输入的时间进行正则监测 
			System.out.print("请按照格式输入(xx:xx)：");
			levTime = sc.next();
		}
		boolean flag = true;
		int size = enterStack.getSize();
		
		while(flag && !enterStack.isEmpty()){
			/*
			 * 有车要出场，那么停在他前面的车依次进入
			 * exitStack，直到该车出去
			 */
			Car car = enterStack.pop();
			if(car.getNumber()==num){
				System.out.println(car.getNumber()+"已退场！");
				System.out.println("收取费用："+cost(car.getArriveTime(), levTime));
				flag = false;
			}else{
				exitStack.push(car);
			}
		}
		while(!exitStack.isEmpty()){
			Car car = exitStack.pop();
			enterStack.push(car);
		}
		//通过车场内车的前后数量变化
		//来判断是否找到车子
		if(size==enterStack.getSize()){
			System.out.println("您输入的车牌不存在!");
			return;
		}
		//如果便道上有车则让其进入车场
		if(!tempParking.isEmpty()){
			Car car = tempParking.deQueue();
			car.setArriveTime(levTime);
			enterStack.push(car);
			System.out.println(car.getNumber()+"已进场！");
		}
		
	}
	
	public int cost(String arvTime,String levTime){
		int cost = 0;
		int arvH = Integer.parseInt(arvTime.split(":")[0]);
		int levH = Integer.parseInt(levTime.split(":")[0]);
		int arvM = Integer.parseInt(arvTime.split(":")[1]);
		int levM = Integer.parseInt(arvTime.split(":")[1]);
		if(arvH<levH){
			if(arvM<levM)
				//加1表示不到一个小时的按照1小时算
				cost += (levH-arvH+1)*costPerHour;
			else
				cost +=	(levH-arvH)*costPerHour;
		}else if(arvH==levH){
			/*
			 * 出现相同时间默认已经停车一天
			 * 因为车不可能进去一分钟都不停
			 */
			return 24*costPerHour;
		}else{
			if(arvM<levM)
				cost += (24-arvH+levH+1)*costPerHour;
			else
				cost +=	(24-arvH+levH)*costPerHour;
		}
		return cost;
	}
	
	
}
