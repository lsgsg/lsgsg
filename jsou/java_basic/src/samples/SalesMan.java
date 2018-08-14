package samples;

public class SalesMan extends Regular{
	private int sales;
	private double commission;
	
	public SalesMan(String irum,int nai,double salary, int sales, double commission) {
		super(irum,nai,salary);
		this.sales=sales;
		this.commission=commission;
		
	}
	@Override
	public double pay() {
		return super.pay()+sales*commission;
		
	}
	@Override
	public void print() {
		display();
		System.out.println(" 수령액:"+pay());
	}


}
