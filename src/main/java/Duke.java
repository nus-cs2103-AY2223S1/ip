import java.util.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> itemList = new ArrayList<>();
        Scanner sc= new Scanner(System.in);

        System.out.print("Hello! I'm Duke \nWhat can I do for you? \n");

        String command = sc.nextLine();
        boolean carryOn = true;

        while (carryOn){
            String[] words = command.split(" ");

            // Exit
            if (command.equals("bye")) {
                carryOn = false;
                System.out.print("Bye. Hope to see you again soon!");
            }
            // List out items
            else if (command.equals("list")) {
                System.out.print("Here are the tasks in your list:\n");
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.print(i + 1 + ":" + itemList.get(i) + "\n");
                }
                command = sc.nextLine();
            }
            // unrecognised commands
            else {
                System.out.print(command + "\n");
                Task toAdd = new Task(command);
                itemList.add(toAdd);
                command = sc.nextLine();
            }
        }


    }
}
