package user.domain;

public enum Level {
	//enum 선언에 DB에 저장할 값과 함께 다음 단계의 레벨 정보도 추가한다.
	GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);
	
	private final int value;
	//다음 단계의 레벨 정보를 스스로 갖고 있도록 Level 타입의 next 변수를 추가한다. 
	private final Level next; 
	
	Level(int value, Level next) {
		this.value = value;
		this.next = next;
	}
	
	public int intValue() {
		return value;	
	}
	
	public Level nextLevel() {
		return this.next;
	}
	
	public static Level valueOf(int value) {
		switch(value) {
		case 1 : return BASIC;
		case 2 : return SILVER;
		case 3 : return GOLD;
		default : throw new AssertionError("Unknown value : " + value);
		}
	}
}
