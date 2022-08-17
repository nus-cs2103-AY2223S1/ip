import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        System.out.println("Hello! I'm " + name + "\nHow can I help you?");

        Task[] tasks = new Task[100];
        int currIndex = 0;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! See you again :)");
                return;
            }
            if (command.equals("list")) {
                for (int i = 0; i < currIndex; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                continue;
            }
            if (command.contains("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].unmark();
                System.out.println("Okay, this task is now unchecked:\n" + tasks[index]);
            }
            else if (command.contains("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks[index].mark();
                System.out.println("Great! This task is completed:\n" + tasks[index]);
            }
            else {
                tasks[currIndex++] = new Task(command);
                System.out.println("added: " + command);
            }
        }
    }
}
