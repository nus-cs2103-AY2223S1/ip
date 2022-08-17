import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                duke.execute(input);
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            }
        }

    }

    public void execute(String input) throws DukeException{
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].trim();
        String detail = "";
        Boolean description_needed = command.equals("todo") || command.equals("deadline") || command.equals("event") ||
                command.equals("mark") || command.equals("unmark") || command.equals("delete");
        if (splitInput.length <= 1 && description_needed) {
            throw new DukeException("Description of command is required.\n");
        } else if (description_needed){
            detail = splitInput[1].trim();
        }

        switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                String message = "";
                int length = this.tasks.size();
                if (length == 0) {
                    message += "List of tasks is currently empty.";
                } else {
                    message += "Here are the tasks in your list:\n";
                    for (int i = 0; i < length; i++) {
                        message += String.format("%d. " + this.tasks.get(i).toString() + "\n", i + 1);
                    }
                }
                System.out.println(message);
                break;
            case "mark":
                if (detail.matches("\\d+")) {
                    int markIndex = Integer.parseInt(detail) - 1;
                    if (markIndex < 0 || markIndex >= this.tasks.size()) {
                        throw new DukeException(String.format("There is no task with index %d\n", markIndex + 1));
                    } else {
                        Task markTask = this.tasks.get(markIndex);
                        if (markTask.isDone) {
                            throw new DukeException("This task is already marked as done:\n" + markTask.toString() + "\n");
                        } else {
                            markTask.markDone();
                            System.out.println("Nice! I've marked this task as done:\n" + markTask.toString() + "\n");
                        }
                    }
                } else {
                    throw new DukeException(detail + " is not an integer.\n");
                }
                break;
            case "unmark":
                if (detail.matches("\\d+")) {
                    int unMarkIndex = Integer.parseInt(detail) - 1;
                    if (unMarkIndex < 0 || unMarkIndex >= this.tasks.size()) {
                        throw new DukeException(String.format("There is no task with index %d\n", unMarkIndex + 1));
                    } else {
                        Task unMarkTask = this.tasks.get(unMarkIndex);
                        if (!unMarkTask.isDone) {
                            throw new DukeException("This task is already marked as undone:\n" + unMarkTask.toString() + "\n");
                        } else {
                            unMarkTask.markUndone();
                            System.out.println("OK, I've marked this task as not done yet:\n" + unMarkTask.toString() + "\n");
                        }
                    }
                } else {
                    throw new DukeException(detail + " is not an integer.\n");
                }
                break;
            case "todo":
                Todo todo = new Todo(detail);
                this.tasks.add(todo);
                String todoMessage = "added: " + todo.toString() + "\n";
                todoMessage += String.format("Now, you have %d task(s) in the list.", this.tasks.size());
                System.out.println(todoMessage + "\n");
                break;
            case "deadline":
                String[] splitDetailDeadline = detail.split("/by", 2);
                if (splitDetailDeadline.length == 1) {
                    throw new DukeException("Description of deadline must be followed by /by then followed by time of deadline.\n");
                } else {
                    String deadlineAction = splitDetailDeadline[0].trim();
                    String deadlineTime = splitDetailDeadline[1].trim();
                    Deadline deadline = new Deadline(deadlineAction, deadlineTime);
                    this.tasks.add(deadline);
                    String deadlineMessage = "added: " + deadline.toString() + "\n";
                    deadlineMessage += String.format("Now, you have %d task(s) in the list.", this.tasks.size());
                    System.out.println(deadlineMessage + "\n");
                }
                break;
            case "event":
                String[] splitDetailEvent = detail.split("/at", 2);
                if (splitDetailEvent.length == 1) {
                    throw new DukeException("Description of event must be followed by /at then followed by time of event.\n");
                } else {
                    String eventAction = splitDetailEvent[0].trim();
                    String eventTime = splitDetailEvent[1].trim();
                    Event event = new Event(eventAction, eventTime);
                    this.tasks.add(event);
                    String eventMessage = "added: " + event.toString() + "\n";
                    eventMessage += String.format("Now, you have %d task(s) in the list.", this.tasks.size());
                    System.out.println(eventMessage + "\n");
                }
                break;
            case "delete":
                if (detail.matches("\\d+")) {
                    int deleteIndex = Integer.parseInt(detail) - 1;
                    if (deleteIndex < 0 || deleteIndex >= this.tasks.size()) {
                        throw new DukeException(String.format("There is no task with index %d\n", deleteIndex + 1));
                    } else {
                        Task deletedTask = this.tasks.get(deleteIndex);
                        this.tasks.remove(deleteIndex);
                        String deleteMessage = "Noted. I've removed this task:\n" + deletedTask.toString() +
                                "\nNow you have %d task(s) in the list\n";
                        System.out.println(String.format(deleteMessage, this.tasks.size()));
                    }
                } else {
                    throw new DukeException(detail + " is not an integer.\n");
                }
                break;
            default:
                throw new DukeException(command + " is an invalid command.\n");
        }
    }
}
