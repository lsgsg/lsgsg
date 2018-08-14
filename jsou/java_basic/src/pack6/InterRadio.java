package pack6;
//public class InterRadio implements InterVol, InterAdvanceVol
public class InterRadio implements InterAdvanceVol{
	private int v=0;
	
	@Override
	public void volUp(int v) {
		this.v+=v;
		
	}
	@Override
	public void volDown(int v) {
		this.v-=v;
		
	}
	
	@Override
	public void volResume() {
		
		
	}
	public void show() {
		System.out.println("현재 볼륨은"+v);
	}
	@Override
	public void volOff() {
		// TODO Auto-generated method stub
		
	}

}
