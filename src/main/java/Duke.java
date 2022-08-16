import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        System.out.println("-----------------------------------");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(line);
            }
            System.out.println("-----------------------------------");
        }
    }


}
