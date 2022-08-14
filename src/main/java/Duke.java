import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------");
        System.out.println("Hello from Phil");
        System.out.println("How may I assist you on this fine day?");
        System.out.println("-------------------------------------------");

        String in = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            in = sc.next();
            if (in.equals("bye")) {
                break;
            }
            System.out.println(in);
            System.out.println("-------------------------------------------");
        }
        System.out.println("See you later alligator!");
        System.out.println("-------------------------------------------");
    }
}
