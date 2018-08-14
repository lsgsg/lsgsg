package pack3;

public class PohamCarMain {

	public static void main(String[] args) {
		PohamCar tom=new PohamCar("톰");
		tom.playHandle(20);
		System.out.println(tom.ownerName+"의 회전량은 "
		+tom.turnShow+" "+tom.handle.quantity );
		
		tom.playHandle(0);
		System.out.println(tom.ownerName+"의 회전량은 "
		+tom.turnShow+" "+tom.handle.quantity );
		
		System.out.println();
		PohamCar kildong=new PohamCar("길동");
		kildong.playHandle(-10);
		System.out.println(kildong.ownerName+"의 회전량은 "
		+kildong.turnShow+" "+kildong.handle.quantity );
	}

}
