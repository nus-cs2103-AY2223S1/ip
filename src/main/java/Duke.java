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

    public void markTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        System.out.printf("[%s] %s%n", statusIcon, description);
    }

    public void unmarkTaskAsDone(int taskIndex) {
        Task task = this.taskArr.get(taskIndex);
        task.unmarkAsDone();
        System.out.println("Sure! I've marked this task as not yet done: ");
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        System.out.printf("[%s] %s%n", statusIcon, description);
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        this.greetUser();

        System.out.print(">>> ");
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                this.sayBye();
                break;
            }

            if (input.equals("list")) {
                this.listTasks();
            } else if (input.equals("mark") || input.equals("unmark")) {
                if (scanner.hasNextInt()) {
                    int taskIndex = scanner.nextInt() - 1;
                    if (input.equals("mark")) {
                        this.markTaskAsDone(taskIndex);
                    } else {
                        this.unmarkTaskAsDone(taskIndex);
                    }
                }
                scanner.nextLine();
            } else {
                input += scanner.nextLine();
                Task task = new Task(input);
                this.pushTask(task);
            }
            System.out.print(">>> ");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}