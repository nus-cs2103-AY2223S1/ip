import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    private static final String WELCOME_MESSAGE = "\t-------------------------------\n"
            + "\tHello, I'm Duke!\n" + "\tWhat can I do for you?\n" + "\t-------------------------------";

    private static final String BYE_MESSAGE = "\tBye! Hope to see you again";
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);

        String input = sc.nextLine().trim();

        while (!input.equals(Commands.BYE)) {
            duke.run(input);
            input = sc.nextLine().trim();
        }
        // cleaning up and shutting down Duke
        System.out.println(BYE_MESSAGE);
        sc.close();
    }
    public void run(String input) {
        try {
                String[] inputString = input.split(" ", 2);
                String command = inputString[0];
                System.out.println("\t-------------------------------");
                switch (command) {
                case Commands.LIST:
                    Task.listTasks();
                    break;
                case Commands.MARK:
                    if (inputString.length == 1) {
                        throw new DukeException("Oops! you forgot to indicate the task number");
                    }
                    int taskNumber = parseInt(inputString[1].trim());
                    Task.markAsDone(taskNumber - 1); // since display is 1-indexed
                    break;
                case Commands.UNMARK:
                    if (inputString.length == 1) {
                        throw new DukeException("Oops! You forgot to indicate the task number");
                    }
                    taskNumber = parseInt(inputString[1].trim());
                    Task.markAsNotDone(taskNumber - 1); // since display is 1-indexed
                    break;
                case Commands.TODO:
                    if (inputString.length == 1) {
                        throw new DukeException("Oops! You forgot to indicate the description for your todo");
                    }
                    Task.add(new Todo(inputString[1]));
                    break;
                case Commands.DEADLINE:
                    if (inputString.length == 1) {
                        throw new DukeException("Oops! You forgot to indicate the description and deadline " +
                                "for your deadline");
                    }
                    if (inputString[1].contains("/by")) {
                        String[] deadlineDetails = inputString[1].split("/by");
                        if (deadlineDetails.length == 1) {
                            throw new DukeException("Please be sure to specify both description and deadline");
                        }
                        String description = deadlineDetails[0].trim();
                        String deadline = deadlineDetails[1].trim();
                        if (description.length() == 0) {
                            throw new DukeException("Oops! You forgot to indicate the description " +
                                    "for your deadline");
                        }
                        if (deadline.length() == 0) {
                            throw new DukeException("Oops! You forgot to indicate the deadline");

                        }
                        Task.add(new Deadline(description, deadline));
                    } else {
                        throw new DukeException("Oops! You forgot to use /by to separate " +
                                "the description and deadline");
                    }
                    break;
                case Commands.EVENT:
                    if (inputString.length == 1) {
                        throw new DukeException("Oops! You forgot to indicate the description and timing " +
                                "for your event");
                    }
                    if (inputString[1].contains("/at")) {
                        String[] eventDetails = inputString[1].split("/at");
                        if (eventDetails.length == 1) {
                            throw new DukeException("Please be sure to specify both description and timing" +
                                    " for your event");
                        }
                        String description = eventDetails[0].trim();
                        String timing = eventDetails[1].trim();
                        if (description.length() == 0) {
                            throw new DukeException("Oops! You forgot to indicate the description " +
                                    "for your event");
                        }
                        if (timing.length() == 0) {
                            throw new DukeException("Oops! You forgot to indicate the timing " +
                                    "for your event");
                        }
                        Task.add(new Event(description, timing));
                    } else {
                        throw new DukeException("Oops! You forgot to use /at to separate " +
                                "the description and timing");
                    }
                    break;
                case Commands.DELETE:
                    if (inputString.length == 1 || inputString[1].trim().length() == 0) {
                        throw new DukeException("Oops! You forgot to specify which task number to delete");
                    }
                    taskNumber = parseInt(inputString[1]);
                    Task.delete(taskNumber - 1); // since we store tasks 0-indexed in ArrayList
                    break;
                default:
                    System.out.println("\tOops! I've no idea what you're talking about!");
                }
                System.out.println("\t-------------------------------");
        } catch (DukeException e) {
            System.out.printf("\t%s\n", e.getMessage());
            System.out.println("\t-------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("\tPlease make sure you enter a task number correctly!");
            System.out.println("\t-------------------------------");
        }
    }
}

