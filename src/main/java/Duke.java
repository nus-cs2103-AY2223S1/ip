import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Duke {

    public static void main(String[] args) {
        intro();
        processCommand();
        outro();
    }

    private static void intro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void processCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        List<Task> userList = new ArrayList<Task>();
        int index = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                userList.forEach(System.out::println);
            } else if (command.length() > 5 && command.substring(0,4).equals("mark")) {
                int tempIndex = command.charAt(5) - '0' - 1;
                if (tempIndex <= index) {
                    userList.set(tempIndex, userList.get(tempIndex).performTask());
                }
            } else if (command.length() > 7 && command.substring(0,6).equals("unmark")) {
                int tempIndex = command.charAt(7) - '0' - 1;
                if (tempIndex <= index) {
                    userList.set(tempIndex, userList.get(tempIndex).undoTask());
                }
            } else {
                index++;
                Task newTask = new Task(command, index);
                userList.add(newTask);
                System.out.printf("\tadded: %s\n", command);
            }
            command = sc.nextLine();
        }
    }

    private static void outro(){
        System.out.println("\tBye. Hope to see you again");
    }
}
