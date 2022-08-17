import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private static String botName = "DIGITAL DADDY";
    private static String emoji = "\uD83E\uDD16";
    private List<Task> taskList = new ArrayList<>();

    Duke(Scanner sc) {
        this.sc = sc;
    }

    private static void botReply(String input) {
        String lineSeparator = "____________________________________________________________";
        String reply = String.format("%s\n%s %s %s \n%s \n%s", lineSeparator, emoji, botName, emoji, input, lineSeparator);
        System.out.println(reply);
    }

    private String taskListToString(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "You haven't added anything to your list!";
        }

        String taskListString = "";

        for (int index = 1; index <= taskList.size(); index++) {
            Task listItem = taskList.get(index - 1);
            String listItemString = index + ". " + listItem.toString();
            if (index != taskList.size()) {
                listItemString += "\n";
            }
            taskListString += listItemString;
        }

        return taskListString;
    }

    public void start() {
        botReply("Hello! I'm " + botName + "\nWhat can I do for you?");

        while (true) {
            try {
                String input = sc.nextLine().trim();

                if (input.equals("bye")) {
                    botReply("Bye. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    botReply(this.taskListToString(this.taskList));
                    continue;
                }

                // Marking tasks
                if (input.startsWith("mark") || input.startsWith("unmark")) {
                    String[] parts = input.split(" ");

                    // Input validation
                    if (parts.length != 2) {
                        throw new DukeException("Wrong input format! mark/unmark <item number>\ne.g. 'mark 3'");
                    }

                    int taskIndex;
                    Task pickedTask;
                    try {
                        taskIndex = Integer.parseInt(parts[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid task number! mark/unmark <item number>\ne.g. 'mark 3'");
                    }

                    try {
                        pickedTask = this.taskList.get(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Task number doesn't exist!");
                    }

                    String markOperation = parts[0];
                    String reply = "";

                    if (markOperation.equals("mark")) {
                        pickedTask.markTask(true);
                        reply += "Nice! I've marked this task as done:\n";
                    } else if (markOperation.equals("unmark")) {
                        pickedTask.markTask(false);
                        reply += "OK, I've marked this task as not done yet:\n";
                    }

                    reply += " " + pickedTask.toString();
                    botReply(reply);

                    continue;
                }

                // Deleting tasks
                if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");

                    // Input validation
                    if (parts.length != 2) {
                        throw new DukeException("Wrong format! delete <item number>\ne.g. 'delete 3'");
                    }

                    int taskIndex;
                    Task pickedTask;
                    try {
                        taskIndex = Integer.parseInt(parts[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid task number! delete <item number>\ne.g. 'delete 3'");
                    }

                    try {
                        pickedTask = this.taskList.get(taskIndex);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Task number doesn't exist!");
                    }

                    this.taskList.remove(taskIndex);
                    botReply("Noted. I've removed this task:\n " + pickedTask + "\nNow you have " + this.taskList.size() + " tasks in the list");

                    continue;
                }

                // Adding tasks
                if (input.startsWith("todo")) {
                    String[] parts = input.split("todo", 2);
                    String description = parts[1].trim();

                    if (parts[1].equals("")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    Task newTask = new Todo(description);
                    this.addToList(newTask);
                    continue;
                }

                if (input.startsWith("deadline")) {
                    String[] parts = input.split("deadline", 2);

                    if (parts[1].equals("")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                    String[] details = parts[1].split("/by", 2);

                    String description;
                    String date;

                    try {
                        description = details[0].trim();
                        date = details[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("A deadline needs a by date! e.g. deadline buy dinner /by 6pm");
                    }

                    if (date.equals("")) {
                        throw new DukeException("A deadline needs a by date! e.g. deadline buy dinner /by 6pm");
                    }

                    Task newTask = new Deadline(description, date);
                    this.addToList(newTask);
                    continue;
                }

                if (input.startsWith("event")) {
                    String[] parts = input.split("event", 2);

                    if (parts[1].equals("")) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }

                    String[] details = parts[1].split("/at", 2);

                    String description;
                    String date;

                    try {
                        description = details[0].trim();
                        date = details[1].trim();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("An event needs a date! e.g. event meeting /at 2pm-4pm");
                    }

                    if (date.equals("")) {
                        throw new DukeException("An event needs a date! e.g. event meeting /at 2pm-4pm");
                    }

                    Task newTask = new Event(description, date);
                    this.addToList(newTask);
                    continue;
                }

                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                botReply("\uD83E\uDD22" + " OOPS!!! " + e.getMessage());
            }
        }
    }

    private void addToList(Task newTask) {
        this.taskList.add(newTask);
        botReply("Got it. I've added this task:\n " + newTask + "\nNow you have " + this.taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        // Create a scanner to read from standard input.
        Scanner sc = new Scanner(System.in);

        Duke duke = new Duke(sc);
        duke.start();

        sc.close();
    }


}
