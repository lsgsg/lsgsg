package pack4;

public class Dog {
	private String name="개";

	public Dog() {
		
	}

	public Dog(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public String callName() {
		return "종류:"+name;
	}
	public void print() {
		System.out.println(name+"은(는) 지구에 산다");
	}

}
