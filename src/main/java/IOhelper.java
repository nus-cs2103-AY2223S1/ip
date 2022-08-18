import java.util.Scanner;

public class IOhelper {
    public static Scanner scanner = new Scanner(System.in);

    public static void print(String text) {
        System.out.println(text);
    }

    public static String read() {
        return scanner.nextLine();
    }
}
