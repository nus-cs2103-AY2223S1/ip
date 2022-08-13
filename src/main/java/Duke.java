import java.util.Scanner;

public class Duke {
    public static String reply(String input) {
        return "Duck: " + input;
    }
    public static void main(String[] args) {
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Duck: Quack! Hope to see you again soon!");
                return;
            } else System.out.println(reply(input));
        }
    }
}
