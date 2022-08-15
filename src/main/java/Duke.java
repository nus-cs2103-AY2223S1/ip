import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        boolean bye = false;

        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
        System.out.println("HELLO");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println("bai bai");
            } else {
                System.out.println(input);
            }
        }
    }
}
