package samples;

public class Temporary extends Employee {
	
	private int ilsu;
	private int ildang;
	
	
	
	public Temporary(String irum, int nai, int ilsu,int ildang) {
		super(irum,nai);
		this.ildang=ildang;
		this.ilsu=ilsu;
		
	}
	@Override
	public double pay() {
		
		return ilsu*ildang;
	
	}
	@Override
	public void print() {
		display();
		System.out.println(" 월급:"+pay());
	}

}
