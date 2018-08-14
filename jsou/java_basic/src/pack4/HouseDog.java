package pack4;

public class HouseDog extends Dog{
	private String where="집";
	
	public HouseDog(String name) {
		super(name);
	
	}
	
	public void show() {
		System.out.println("거주:"+where);
	}
	
	@Override
	public void print() {
		System.out.println(getName()+":"+where+"에 산다");
	}


}
