import java.util.*;

public class Duke {

    private static void greet() {
        System.out.println("Hi, my name is Duke\nand it's power hour!");
        System.out.println("***********************");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Come again soon!");
                System.exit(0);
            } else if (command.equals("")) {
                continue;
            }
            System.out.println(command);
        }
    }

    public static void main(String[] args) {
        Duke.greet();
    }
}
