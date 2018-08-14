package pack2;



public class Production {
	private int price=0;// 가격
	private int size = 0;// 갯수

	public String name; // 이름
	

	
	public Production(String name,int price) {
		this.name=name;
		this.price=price;
	}
	
	public void display() {
		System.out.println("1 "+name+" 2 "+price+" 3 "+size);
	}
	
	
//	private int price=0;// 가격
//	private int size = 0;// 갯수
//
//	public String name; // 이름
//	
//	String name2="멋진 차 ";
//	
//	private String rename(String name2){
//	return "상품명: "+name2;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//	public int getSize() {
//		return size;
//	}
//	
//	public void display() {
//		System.out.println("상품명:" + name + " 가격은 " + price + " 갯수는 " + size);
//
//	}
//
//	public void setPrice(int price) {
//		this.price=price;
//	}
//	public void setSize(int size) {
//		this.size=size;
	}

