import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List list = new List();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < list.taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + list.taskList.get(i) + "\n");
                }
            } else {
                list.addTask(command);
                System.out.println("Got it! I've added " + command + " to the list!\n");
            }
            sc = new Scanner(System.in);
            command = sc.nextLine();
        }
        System.out.println("Hiks. I'm sad, but see you again!!");

    }
}
