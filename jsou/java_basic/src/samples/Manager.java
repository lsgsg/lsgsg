package samples;

public class Manager extends Regular {

	private double incentive;

	public Manager(String irum, int nai, double salary) {
		super(irum, nai, salary);

	}

	@Override
	public double pay() {
		if (super.pay() > 25000000) {
			incentive = super.pay() * 0.6;
		} else if (super.pay() >= 2000000) {
			incentive = super.pay() * 0.5;
		} else {
			incentive = super.pay() * 0.4;
		}
		return incentive + super.pay();
	}

	public void print() {
		display();
		System.out.println(" 수령액:" + pay());
	}

}
