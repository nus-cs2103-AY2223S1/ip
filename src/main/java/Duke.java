import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("| Hi this is Thesh. What can I do for you? |");
        System.out.println("-----------------------------------------------");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (!command.equals("bye")) {
                System.out.println(command);
                continue;
            }
            System.out.println("Bye. Hope to see you again soon!");
            break;
        }

    }
}
