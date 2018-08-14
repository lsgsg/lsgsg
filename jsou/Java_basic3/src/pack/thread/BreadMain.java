package pack.thread;

public class BreadMain {

	public static void main(String[] args) {
		BreadPlate breadPlate=new BreadPlate();
		
		BreadMaker maker=new BreadMaker(breadPlate);
		BreadEater eater=new BreadEater(breadPlate);
		
		maker.setPriority(8);
		maker.start();
		eater.start();

	}

}
