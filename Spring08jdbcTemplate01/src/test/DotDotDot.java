package test;

public class DotDotDot {
	
	// 파라미터의 타입은 입력값에 따라 변한다
	public void print(Object... args) {
		for(int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		DotDotDot dot = new DotDotDot();
		
		dot.print("A");
		dot.print("1", "2");
		dot.print(1, 2, 3, 4, 5);
	}

}
