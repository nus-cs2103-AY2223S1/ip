import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Ekud {
    private final List<Task> taskList;
    private final String logo = "_____________             .___\n" + "\\_   _____/  | ____ __  __| _/\n" + 
    " |    __)_|  |/ /  |  \\/ __ |\n" + " |        \\    <|  |  / /_/ | \n" + "/_______  /__|_ \\____/\\____ | \n" + 
    "        \\/     \\/          \\/ \n";

    /**
     * Constructor that instantiates new Ekud instance.
     */
    public Ekud() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Starts the chat bot.
     */
    public void start() {
        this.sendMessage("Hello from\n" + this.logo + "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        boolean active = true;

        while (active) {
            String command = sc.nextLine();
            try {
                if (command.equals("bye")) {
                    this.sendMessage("Bye. Hope to see you again soon!");
                    active = false;
                } else if (command.equals("list")) {
                    this.printTasks();
                } else if (command.startsWith("mark")) {
                    this.markAsDone(command);
                } else if (command.startsWith("unmark")) {
                    this.markAsUndone(command);
                } else if (command.startsWith("todo")) {
                    addTask(command, TaskType.TODO);
                } else if (command.startsWith("deadline")) {
                    addTask(command, TaskType.DEADLINE);
                } else if (command.startsWith("event")) {
                    addTask(command, TaskType.EVENT);
                } else if (command.startsWith("delete")) {
                    this.deleteTask(command);
                } else {
                    throw new EkudException("Invalid command.");
                }
            } catch (EkudException exception) {
                this.sendMessage(exception.getMessage());
            }
        }
        sc.close();
    }

    private void deleteTask(String message) throws EkudException {
        if (message.matches("^delete \\d+$")) {
            int idx = Integer.parseInt(message.substring("delete ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            }
            Task task = this.taskList.remove(idx - 1);
            this.sendMessage(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", task.toString(), taskList.size()));
        } else {
            throw new EkudException(("Invalid syntax. Use delete <index>"));
        }
    }

    private void markAsDone(String message) throws EkudException {
        if (message.matches("^mark \\d+$")) {
            int idx = Integer.parseInt(message.substring("mark ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            } 
            Task task = this.taskList.get(idx - 1);
            boolean result = task.markAsDone();
            if (result) {
                this.sendMessage(String.format("Nice! I've marked this task as done:\n%s", task.toString()));
            } else {
                throw new EkudException("That task is already marked as done!");
            }
        } else {
            throw new EkudException("Invalid syntax. Use mark <index>");
        }
    }

    private void markAsUndone(String message) throws EkudException {
        if (message.matches("^unmark \\d+$")) {
            int idx = Integer.parseInt(message.substring("unmark ".length()));
            if (idx > this.taskList.size() || idx < 1) {
                throw new EkudException("Invalid index. Type 'list' to see available tasks and their indexes.");
            }
            Task task = this.taskList.get(idx - 1);
            boolean result = task.markAsUndone();
            if (result) {
                this.sendMessage(String.format("OK, I've marked this task as not done yet:\n%s", task.toString()));
            } else {
                throw new EkudException("This task is already marked as undone!");
            }
        } else {
            throw new EkudException("Invalid syntax. Use unmark <index>");
        }
    }

    private void printTasks() {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            builder.append(String.format("%d.%s\n", i + 1, this.taskList.get(i).toString()));
        }
        this.sendMessage(builder.toString());
    }

    private void addTask(String description, TaskType type) throws EkudException {
        if (this.taskList.size() == 100) {
            throw new EkudException("Sorry, you've reached the maximum limit of 100 tasks.");
        }
        Task task = parseSpecialTask(description, type);
        this.taskList.add(task);
        this.sendMessage(String.format("Got it. I've added this task: %s", task.toString())); 
    }

    private Task parseSpecialTask(String description, TaskType type) throws EkudException {
        Optional<Task> task = Optional.empty();
        String[] parts = description.split(" ");
        switch (type) {
            case TODO:
                if (parts.length < 2) {
                    throw new EkudException("Description of a TODO cannot be empty.");
                } else {
                    task = Optional.of(new ToDo(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))));
                }
                break;
            default: 
                int idxOfSeparator = 0;
                for (int i = 0; i < parts.length; i++) {
                    if (type == TaskType.EVENT && parts[i].equals("/at")) {
                        idxOfSeparator = i;
                    } else if (type == TaskType.DEADLINE && parts[i].equals("/by")) {
                        idxOfSeparator = i;
                    }
                }
                if (idxOfSeparator == 0) {
                    String separator = type == TaskType.EVENT ? "/at" : "/by";
                    throw new EkudException(String.format("Invalid syntax. Use %s <task> %s <date/time>", type.toString(), separator));
                } else if (idxOfSeparator == 1) {
                    throw new EkudException(String.format("Description of an %s cannot be empty.", type.toString()));
                } else if (idxOfSeparator == parts.length - 1) {
                    throw new EkudException("Date/Time cannot be empty.");
                } else {
                    String taskDesc = String.join(" ", Arrays.copyOfRange(parts, 1, idxOfSeparator));
                    String taskDueDate = String.join(" ", Arrays.copyOfRange(parts, idxOfSeparator + 1, parts.length));
                    if (type == TaskType.EVENT) {
                        task = Optional.of(new Event(taskDesc, taskDueDate));
                    } else {
                        task = Optional.of(new Deadline(taskDesc, taskDueDate));
                    }
                }
                break;
        }
        if (task.isEmpty()) {
            throw new EkudException("Unexpected error.");
        } else {
            return task.get();
        }
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
