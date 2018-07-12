package bookclub;

public class Example {

	public static void Change(String a) {
		a = new String("c");
	}
	
	public static void Change(Hocsinh hocsinh) {
		hocsinh.name = "Nguyen Van A";
		hocsinh.age = 12;
	}
	
	public static void main(String[] args) {
//		String a = "a";
//		Change(a);
//		System.out.println(a);
//		Hocsinh hocsinh = new Hocsinh("Nguyen Huu Uy", 18);
//		System.out.println(hocsinh);
//		Change(hocsinh);
//		System.out.println(hocsinh);
		String fileName = "nguyen huu uy";
		String cleanFileName = fileName.replaceAll("\\s", "");
		System.out.println(cleanFileName);
	}

}


class Hocsinh {
	public String name;
	public int age;
	public Hocsinh(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "Name: " + name + "/n Age: " + age;
	}
}

