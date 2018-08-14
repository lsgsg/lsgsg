package samples;

public class Regular extends Employee{
	private double salary;
	
	
	public Regular(String irum, int nai,double salary) {
		super(irum,nai);
		this.salary=salary;
	}

	
	@Override
	public double pay() {
	return salary;	
	
	}

	@Override
	public void print() {
		display();
		System.out.println(" 급여:"+pay());
		
	}
	
	

}
