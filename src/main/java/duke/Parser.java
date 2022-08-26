package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String TIME_INPUT_PATTERN = "dd-MM-yyyy HHmm";
    private static final String DEADLINE_TIME_INPUT_PATTERN = "dd-MM-yyyy";

    /**
     * Parser to interpret the commands.
     *
     * @param fullCommand
     * @return
     * @throws DukeException
     */

    public static Command parse(String fullCommand) throws DukeException {

        String instruction = fullCommand.contains(" ") ? fullCommand.split(" ", 2)[0] : fullCommand;
        String parameters = fullCommand.contains(" ") ? fullCommand.split(" ", 2)[1] : "";
        Command command;

        switch (instruction) {

        case "bye":
            command = new Command(
                    (tasks, ui, storage) -> ui.goodBye(),
                    true
            );
            break;

        case "list":
            command = new Command(
                    (tasks, ui, storage) -> {
                        if (tasks.size() == 0) {
                            System.out.println("You have nothing to do!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            tasks.forEach((task) -> {
                                int itemNumber = tasks.indexOf(task) + 1;
                                System.out.printf("%d: %s\n", itemNumber, task);
                            });
                        }
                    }
            );
            break;

        case "mark":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String itemString = fullCommand.split(" ")[1];
                            int itemNumber = Integer.parseInt(itemString) - 1;
                            Task task = tasks.get(itemNumber);

                            if (task.isDone()) {
                                System.out.println("This task is already marked as done!");
                            } else {
                                task.mark();

                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(task);
                            }

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid item to mark!");
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Task not found!");
                        }

                    }
            );
            break;

        case "unmark":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String itemString = fullCommand.split(" ")[1];
                            int itemNumber = Integer.parseInt(itemString) - 1;
                            Task task = tasks.get(itemNumber);

                            if (!task.isDone()) {
                                System.out.println("This task is already marked as not done yet!");
                            } else {
                                task.unmark();

                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(task);
                            }

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid item to mark!");
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Task not found!");
                        }

                    }
            );
            break;

        case "deadline":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String taskName = parameters.split(" /by ")[0];
                            String by = parameters.split(" /by ")[1];


                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_INPUT_PATTERN);
                            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
                            Task task = new Deadline(taskName, byDateTime);
                            tasks.add(task);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid date/time!");
                        } catch (DateTimeParseException e) {
                            System.out.println("Please input the date and time in the following format: " + TIME_INPUT_PATTERN);
                        }
                    }
            );
            break;

        case "event":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String taskName = parameters.split(" /at ")[0];
                            String at = parameters.split(" /at ")[1];

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_INPUT_PATTERN);
                            LocalDateTime byDateTime = LocalDateTime.parse(at, formatter);
                            Task task = new Event(taskName, byDateTime);
                            tasks.add(task);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid date/time!");
                        } catch (DateTimeParseException e) {
                            System.out.println("Please input the date and time in the following format: " + TIME_INPUT_PATTERN);
                        }
                    }
            );
            break;

        case "todo":
            command = new Command(
                    (tasks, ui, storage) -> {
                        Task task = new Todo(parameters);
                        tasks.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.printf("Now you have %d task in the list.%n", tasks.size());
                    }
            );
            break;

        case "delete":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String itemString = fullCommand.split(" ")[1];
                            int itemNumber = Integer.parseInt(itemString) - 1;
                            Task taskToRemove = tasks.get(itemNumber);

                            tasks.remove(taskToRemove);

                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskToRemove);
                            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());


                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid item to mark!");
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Task not found!");
                        }
                    }
            );
            break;

        case "deadlines":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEADLINE_TIME_INPUT_PATTERN);
                            LocalDate deadlineDate = LocalDate.parse(parameters, formatter);

                            boolean[] hasElements = {false};

                            tasks
                                    .stream()
                                    .filter(t -> t.getDateTime().getYear() == deadlineDate.getYear())
                                    .filter(t -> t.getDateTime().getDayOfYear() == deadlineDate.getDayOfYear())
                                    .forEach((t) -> {
                                        int itemNumber = tasks.indexOf(t) + 1;
                                        if (!hasElements[0]) {
                                            System.out.println("Here are deadlines in your list");
                                        }
                                        hasElements[0] = true;
                                        System.out.printf("%d: %s\n", itemNumber, t);
                                    });

                            if (!hasElements[0]) {
                                System.out.println("You do not have any deadlines for this date!");
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("Please input the date and time in the following format: " + DEADLINE_TIME_INPUT_PATTERN);
                        }
                    }
            );
            break;

        case "find":
            command = new Command(
                    (tasks, ui, storage) -> {

                        boolean[] hasElements = {false};

                        tasks
                                .stream()
                                .filter(t -> t.getDescription().contains(parameters))
                                .forEach((t) -> {
                                    int itemNumber = tasks.indexOf(t) + 1;
                                    if (!hasElements[0]) {
                                        System.out.println("Here are the matching tasks in you list");
                                    }
                                    hasElements[0] = true;
                                    System.out.printf("%d: %s\n", itemNumber, t);
                                });

                        if (!hasElements[0]) {
                            System.out.println("No such entries were found!");
                        }

                    }
            );
            break;

        default:
            throw new DukeException("Invalid Command!");
        }
        return command;
    }
}
