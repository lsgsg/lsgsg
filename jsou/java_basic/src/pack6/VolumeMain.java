package pack6;

public class VolumeMain {

	public static void main(String[] args) {
		// Volume volume=new Volume(); 안댐
		Volume volume = null;
		VolumeRadio radio = new VolumeRadio();
		radio.voiumUp(10);
		radio.voiumDown(5);
		System.out.println();
		VolumeTv tv = new VolumeTv();
		tv.voiumUp(7);
		tv.voiumDown(3);
		tv.Kbs();
		
		System.out.println();
		volume=radio;
		volume.voiumUp(10);
		volume.voiumDown(8);
		System.out.println();
		volume=tv;
		volume.voiumUp(10);
		volume.voiumDown(8);

	}
}