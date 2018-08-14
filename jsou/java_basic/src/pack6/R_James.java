package pack6;

public class R_James implements Resume{
	private String irum,phone,skill;
	
	@Override
	public void setIrum(String irum) {
		if(irum.equals(null)) {
			this.irum="무명";
		}else {
			this.irum="irum";
		}
		
	}
	@Override
	public void setPhone(String phone) {
		this.phone=phone;
		
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	@Override
	public void print() {
		System.out.println("이력서 규격은 "+Resume.SIZE);
		System.out.println("성명:"+irum+", 전화:"+phone+", 보유기술:"+skill);
		playJava(true);
		
	}
	@Override
	public void playJava(boolean b) {
		// TODO Auto-generated method stub
		Resume.super.playJava(b);
	}

}
