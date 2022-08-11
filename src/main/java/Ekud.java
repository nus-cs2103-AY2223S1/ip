import java.util.Scanner;

public class Ekud {
    private final Task[] taskList;
    private int numberOfTasks;
    private final String logo = "_____________             .___\n" + "\\_   _____/  | ____ __  __| _/\n" + 
    " |    __)_|  |/ /  |  \\/ __ |\n" + " |        \\    <|  |  / /_/ | \n" + "/_______  /__|_ \\____/\\____ | \n" + 
    "        \\/     \\/          \\/ \n";

    public Ekud() {
        this.taskList = new Task[100];
        this.numberOfTasks = 0;
    }

    public void start() {
        this.sendMessage("Hello from\n" + this.logo + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        boolean active = true;

        while (active) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                this.sendMessage("Bye. Hope to see you again soon!");
                active = false;
            } else if (command.equals("list")) {
                this.printTasks();
            } else if (command.startsWith("mark")) {
                this.markAsDone(command);
            } else if (command.startsWith("unmark")) {
                this.markAsUndone(command);
            } else {
                if(!addTask(command)) {
                    this.sendMessage("Sorry, you've reached the maximum limit of 100 tasks.");
                }
                this.sendMessage(String.format("added: %s", command));
            }
        }
        sc.close();
    }

    private void markAsDone(String message) {
        if (message.matches("^mark \\d+$")) {
            int idx = Integer.parseInt(message.substring("mark ".length()));
            if (idx > this.numberOfTasks || idx < 1) {
                this.sendMessage("Invalid index. Type 'list' to see available tasks and their indexes.");
                return;
            } 
            Task task = this.taskList[idx - 1];
            boolean result = task.markAsDone();
            if (result) {
                this.sendMessage(String.format("Nice! I've marked this task as done:\n%s", task.toString()));
            } else {
                this.sendMessage("That task is already marked as done!");
            }
        } else {
            this.sendMessage("Invalid syntax. Use mark <index>");
        }
    }

    private void markAsUndone(String message) {
        if (message.matches("^unmark \\d+$")) {
            int idx = Integer.parseInt(message.substring("unmark ".length()));
            if (idx > this.numberOfTasks || idx < 1) {
                this.sendMessage("Invalid index. Type 'list' to see available tasks and their indexes.");
                return;
            }
            Task task = this.taskList[idx - 1];
            boolean result = task.markAsUndone();
            if (result) {
                this.sendMessage(String.format("OK, I've marked this task as not done yet:\n%s", task.toString()));
            } else {
                this.sendMessage("This task is already marked as undone!");
            }
        } else {
            this.sendMessage("Invalid syntax. Use unmark <index>");
        }
    }

    private void printTasks() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.numberOfTasks; i++) {
            builder.append(String.format("%d.%s\n", i + 1, this.taskList[i].toString()));
        }
        this.sendMessage(builder.toString());
    }

    private boolean addTask(String description) {
        if (this.numberOfTasks == 100) {
            return false;
        }
        Task task = new Task(description);
        this.taskList[this.numberOfTasks++] = task;
        return true;
    }

    private String indentMessage(String message) {
        StringBuilder builder = new StringBuilder("    ");
        for (int i = 0; i < message.length(); i++) {
            builder.append(message.charAt(i));
            if (message.charAt(i) == '\n') {
                builder.append("    ");
            }
        }
        return builder.toString();
    }

    private void sendMessage(String message) {
        String divider = "___________________________________";
        System.out.println(indentMessage(divider + "\n" + message + "\n" + divider + "\n"));
    }
    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        ekud.start();

    }
}
