import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskArr = new ArrayList<>();

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }

    public void sayBye() {
        String message = "Bye! Hope to see you soon!";
        System.out.println(message);
    }

    public void pushTask(Task task) {
        this.taskArr.add(task);
        System.out.println("Added task: " + task.getDescription());
    }

    public void listTasks() {
        System.out.println("Here are your tasks: ");
        int len = this.taskArr.size();
        for (int i = 0; i < len; i++) {
            Task task = this.taskArr.get(i);
            String statusIcon = task.getStatusIcon();
            String description = task.getDescription();
            System.out.printf("%d.[%s] %s%n", i + 1, statusIcon, description);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        duke.greetUser();

        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                duke.sayBye();
                break;
            }

            if (input.equals("list")) {
                duke.listTasks();
            } else {
                Task task = new Task(input);
                duke.pushTask(task);
            }

        }
    }
}