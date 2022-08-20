import java.util.ArrayList;
import java.util.Scanner;

public class Ted {

    private static final String GREETING =
            "##################################################\n" +
                    "||                                              ||\n" +
                    "||                Hello! I'm Ted                ||\n" +
                    "||            What can I do for you?            ||\n" +
                    "||                                              ||\n" +
                    "##################################################";

    private static final String INPUT_PREFIX = "> ";

    ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Helper to add task to tasks
     * @param task task to be added
     */
    private void addTask(Task task) {
        this.tasks.add(task);
        System.out.printf("Got it. I've added this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.\n",
                task.toString(),
                tasks.size()
        );
    }

    /**
     * List of handlers for different command
     */
    private void handleListCommand(String arg) {
        if (this.tasks.size() == 0) {
            System.out.println("There is no tasks here. Feel free to add some tasks.");
            return;
        }

        for (int inputIndex = 0; inputIndex < tasks.size(); inputIndex++) {
            System.out.printf("%d.%s\n", inputIndex + 1, tasks.get(inputIndex));
        }
    }

    private void handleMarkCommand(String arg) throws InvalidInputException {
        try {
            int index = Integer.parseInt(arg);

            if (this.tasks.size() == 0) {
                throw new InvalidInputException("There is no tasks here. Feel free to add a task.");
            }

            if (index <= 0) {
                throw new InvalidInputException("The number of task to be marked must be greater than 0.");
            }

            if (index > this.tasks.size()) {
                throw new InvalidInputException(
                        String.format(
                                "Error: The number of task to be marked must be less than or equal to %d.",
                                this.tasks.size()
                        )
                );
            }

            this.tasks.get(index - 1).markAsDone();
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The number of task to be marked passed must be a number.");
        }
    }

    private void handleUnmarkCommand(String arg) throws InvalidInputException {
        try {
            int index = Integer.parseInt(arg);

            if (this.tasks.size() == 0) {
                throw new InvalidInputException("There is no tasks here. Feel free to add a task.");
            }

            if (index <= 0) {
                throw new InvalidInputException("The number of task to be unmarked must be greater than 0.");
            }

            if (index > this.tasks.size()) {
                throw new InvalidInputException(
                        String.format(
                                "The number of task to be unmarked must be less than or equal to %d.",
                                this.tasks.size()
                        )
                );
            }

            this.tasks.get(index - 1).unmark();
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The number of task to be unmarked passed must be a number.");
        }
    }

    private void handleToDoCommand(String arg) throws InvalidInputException {
        if (arg.isEmpty()) {
            throw new InvalidInputException("The description of todo must not be empty.");
        }

        this.addTask(new ToDo(arg));
    }

    private void handleDeadlineCommand(String arg) throws InvalidInputException {
        if (arg.isEmpty()) {
            throw new InvalidInputException("The description of deadline must not be empty.");
        }

        String[] inputs = arg.split(" /by ");
        String description = inputs[0];

        if (inputs.length <= 1) {
            throw new InvalidInputException(
                    "The deadline (use /by) of task must be set. " +
                            "If you wish to create a task without deadline, try using todo command.");
        }

        String by = inputs[1];
        this.addTask(new Deadline(description, by));
    }

    private void handleEventCommand(String arg) throws InvalidInputException {
        if (arg.isEmpty()) {
            throw new InvalidInputException("The description of event must not be empty.");
        }

        String[] inputs = arg.split(" /at ");
        String description = inputs[0];

        if (inputs.length <= 1) {
            throw new InvalidInputException("The datetime (use /at) of event must be set. " +
                    "If you wish to create a task without date or time, try using todo command.");
        }

        String at = inputs[1];
        this.addTask(new Event(description, at));
    }

    private void handleDeleteCommand(String arg) throws InvalidInputException {
        try {
            int index = Integer.parseInt(arg);

            if (this.tasks.size() == 0) {
                throw new InvalidInputException("There is no tasks here. Feel free to add a task.");
            }

            if (index <= 0) {
                throw new InvalidInputException("The number of task to be deleted must be greater than 0.");
            }

            if (index > this.tasks.size()) {
                throw new InvalidInputException(
                        String.format(
                                "The number of task to be deleted must be less than or equal to %d.",
                                this.tasks.size()
                        )
                );
            }

            System.out.printf("Noted. I've removed this task:\n" +
                    "%s\n" +
                    "Now you have %d tasks in the list.\n",
                    this.tasks.get(index - 1),
                    this.tasks.size() - 1
            );

            this.tasks.remove(index - 1);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The number of task to be deleted passed must be a number.");
        }
    }

    private void startup() {
        System.out.println(GREETING);

        Scanner scanner = new Scanner(System.in);
        System.out.print(INPUT_PREFIX);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0].toLowerCase();
            String argument = input.length() > command.length() + 1 ? input.substring(command.length() + 1) : "";

            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again!");
                    break;
                } else if (command.equals("list")) {
                    this.handleListCommand(argument);
                } else if (command.equals("mark")) {
                    this.handleMarkCommand(argument);
                }  else if (command.equals("unmark")) {
                    this.handleUnmarkCommand(argument);
                } else if (command.equals("todo")) {
                    this.handleToDoCommand(argument);
                } else if (command.equals("deadline")) {
                    this.handleDeadlineCommand(argument);
                } else if (command.equals("event")) {
                    this.handleEventCommand(argument);
                } else if (command.equals("delete")) {
                    this.handleDeleteCommand(argument);
                } else {
                    System.out.println("I'm sorry. I don't understand what that means.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }

            System.out.print(INPUT_PREFIX);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Ted bot = new Ted();
        bot.startup();
    }
}
