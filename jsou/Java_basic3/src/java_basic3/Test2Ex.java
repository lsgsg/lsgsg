package java_basic3;



public class Test2Ex {
	private String name;
	private int sabun,money,year;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	private static Test2Ex singleton = new Test2Ex();
	public static Test2Ex getInstance() {
	return singleton;
	}
	
	
	

}
