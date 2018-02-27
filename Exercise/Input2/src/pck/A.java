package pck;

public class A {

	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		if (x>1) {
			B.f("B");
		} else {
			C.f("C");
		}

	}

}
