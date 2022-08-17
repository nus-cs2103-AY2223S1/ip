import java.util.Scanner;

public class Duke {
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Adds a new task to the task list and prints a confirmation message.
     * 
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.addTask(task);
        int size = this.taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msgBegin = "Got it. I've added this task:\n";
        String msgEnd = "\nNow you have " + size + " " + taskString + " in this list.";
        String msg = msgBegin + "  " + task + msgEnd;
        prettyPrint(msg);
    }

    /**
     * Delete a task and prints a confirmation message.
     * 
     * @param index Index of the task as printed by viewAllTask.
     */
    public void deleteTask(int index) {
        String task = this.taskList.getTaskToString(index);
        this.taskList.deleteTask(index);
        int size = this.taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msgBegin = "Noted. I've removed this task:\n";
        String msgEnd = "\nNow you have " + size + " " + taskString + " in this list.";
        String msg = msgBegin + " " + task + msgEnd;
        prettyPrint(msg);
    }

    /**
     * Mark the task with the input index as done and prints a confirmation
     * message.
     * 
     * @param index Index of the task as printed by viewAllTask.
     */
    public void markTask(int index) {
        this.taskList.markTask(index);
        String msgBegin = "Nice! I've marked this task as done: \n ";
        String msg = msgBegin + this.taskList.getTaskToString(index).toString();
        prettyPrint(msg);
    }

    /**
     * Mark the task with the input index as not done and prints a confirmation
     * message.
     * 
     * @param index Index of the task as printed by viewAllTask.
     */
    public void unmarkTask(int index) {
        this.taskList.unmarkTask(index);
        String msgBegin = "OK, I've marked this task as not done yet: \n ";
        String msg = msgBegin + this.taskList.getTaskToString(index).toString();
        prettyPrint(msg);
    }

    /**
     * Prints an overview of all added tasks and their status.
     */
    public void viewAllTask() {
        String msgBegin = "Here are the tasks in your list:\n";
        String msg = msgBegin + this.taskList.toString();
        prettyPrint(msg);
    }

    /**
     * Prints the given message with appropriate indentations and horizontal
     * lines.
     * 
     * @param msg Message to be printed.
     */
    private static void prettyPrint(String msg) {
        // Horizontal lines have 4 spaces as indentation
        System.out.println(
                "    ____________________________________________________________");
        String[] msgTokens = msg.split("\n");
        for (String token : msgTokens) {
            // Message has 5 spaces as indentation
            System.out.println("     " + token);
        }
        System.out.println(
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        String greetingMsg = "Hello! I'm Duke \nWhat can I do for you?";
        String goodByeMsg = "Bye. Hope to see you again soon!";

        Duke dk = new Duke();
        Scanner sc = new Scanner(System.in);
        prettyPrint(greetingMsg);

        while (true) {
            String usrInput = sc.nextLine();
            String[] usrInputTokens = usrInput.split(" ", 2);
            String usrCommand = usrInputTokens[0];

            if (usrCommand.equals("bye")) {
                break;
            }
            try {
                switch (usrCommand) {
                    case "list":
                        dk.viewAllTask();
                        break;
                    case "mark":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! Try doing 'mark <index>'!");
                        }
                        // markIndex - 1 because viewAllTasks is 1-indexed
                        // whereas actual implementation is 0-indexed.
                        int markIndex = Integer.parseInt(
                                usrInputTokens[1], 10) - 1;
                        if (markIndex > dk.taskList.size() - 1 || markIndex < 0) {
                            throw new DukeException(
                                    "Oh no! There doesn't seem to be a task with this index.");
                        }
                        dk.markTask(markIndex);
                        break;
                    case "unmark":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! Try doing 'unmark <index>'!");
                        }
                        int unmarkIndex = Integer.parseInt(
                                usrInputTokens[1], 10) - 1;
                        if (unmarkIndex > dk.taskList.size() - 1 || unmarkIndex < 0) {
                            throw new DukeException(
                                    "Oh no! There doesn't seem to be a task with this index.");
                        }
                        dk.unmarkTask(unmarkIndex);
                        break;
                    case "delete":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! Try doing 'delete <index>'!");
                        }
                        int deleteIndex = Integer.parseInt(
                                usrInputTokens[1], 10) - 1;
                        if (deleteIndex > dk.taskList.size() - 1 || deleteIndex < 0) {
                            throw new DukeException(
                                    "Oh no! There doesn't seem to be a task with this index.");
                        }
                        dk.deleteTask(deleteIndex);
                        break;
                    case "todo":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! The description of a todo cannot be empty.");
                        }
                        String todoTitle = usrInputTokens[1].trim();
                        Todo newTodo = new Todo(todoTitle, false);
                        dk.addTask(newTodo);
                        break;
                    case "deadline":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! The description of a deadline cannot be empty.");
                        }
                        String[] deadlineTokens = usrInputTokens[1].split("/by");
                        if (deadlineTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! Try doing 'deadline <description> /by <date>'!");
                        }
                        String deadlineTitle = deadlineTokens[0].trim();
                        String by = deadlineTokens[1].trim();
                        Deadline newDeadline = new Deadline(deadlineTitle, false, by);
                        dk.addTask(newDeadline);
                        break;
                    case "event":
                        if (usrInputTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! The description of an event cannot be empty.");
                        }
                        String[] eventTokens = usrInputTokens[1].split("/at");
                        if (eventTokens.length < 2) {
                            throw new DukeException(
                                    "Oh no! Try doing 'event <description> /at <date>'!");
                        }
                        String eventTitle = eventTokens[0].trim();
                        String at = eventTokens[1].trim();
                        Event newEvent = new Event(eventTitle, false, at);
                        dk.addTask(newEvent);
                        break;
                    default:
                        throw new DukeException(
                                "Sorry, I don't understand this!");
                }
            } catch (DukeException e) {
                prettyPrint(e.toString());
            }
        }
        sc.close();
        prettyPrint(goodByeMsg);
    }
}
