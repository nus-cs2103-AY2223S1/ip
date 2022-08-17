import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            // Exit
            if (command.equals("bye")) {
                carryOn = false;
                System.out.print("Bye. Hope to see you again soon!");
            } else {
                System.out.print(command + "\n");
                command = sc.nextLine();
            }
        }
    }
}
