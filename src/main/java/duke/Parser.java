package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

public class Parser {

    private static final String TIME_INPUT_PATTERN = "dd-MM-yyyy HHmm";
    private static final String DEADLINE_TIME_INPUT_PATTERN = "dd-MM-yyyy";

    /**
     * Parser to interpret the commands.
     *
     * @param fullCommand The instructions to execute.
     * @return A command that holds the information as specified by fullCommand.
     * @throws DukeException The exception to be thrown when an error occurs.
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
                            ui.showNothingToDoMessage();
                        } else {
                            ui.listAllTasks(tasks);
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
                                ui.showError("This task is already marked as done!");
                            } else {
                                task.mark();
                                ui.showMarkedAsDoneMessage(task);
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
                                ui.showError("This task is already marked as not done yet!");
                            } else {
                                task.unmark();
                                ui.showMarkedAsNotDoneMessage(task);
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

                            String taskName = parameters.split(Deadline.KEYWORD_TO_SPLIT)[0];
                            String by = parameters.split(Deadline.KEYWORD_TO_SPLIT)[1];

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_INPUT_PATTERN);
                            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
                            Task task = new Deadline(taskName, byDateTime);

                            tasks.add(task);

                            ui.showAddedTaskMessage(task);
                            ui.countTasks(tasks);

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

                            ui.showAddedTaskMessage(task);
                            ui.countTasks(tasks);

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

                        ui.showAddedTaskMessage(task);
                        ui.countTasks(tasks);
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

                            ui.showRemovedTaskMessage(taskToRemove);
                            ui.countTasks(tasks);

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
                            SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd");
                            Predicate<? super Task> areDatesEqual = t -> d.format(deadlineDate).equals(d.format(t.getDateTime()));

                            ui.showTasksWithThisProperty(tasks, areDatesEqual);

                        } catch (DateTimeParseException e) {
                            ui.showError("Please input the date and time in the following format: " + DEADLINE_TIME_INPUT_PATTERN);
                        }
                    }
            );
            break;

        case "find":
            command = new Command(
                    (tasks, ui, storage) -> {

                        Predicate<? super Task> doesTaskNameContain = t -> t.getDescription().contains(parameters);

                        ui.showTasksWithThisProperty(tasks, doesTaskNameContain);

                    }
            );
            break;

        case "tag":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String itemString = fullCommand.split(" ")[1];
                            String tagString = fullCommand.split(" ")[2];
                            int itemNumber = Integer.parseInt(itemString) - 1;
                            Task taskToTag = tasks.get(itemNumber);

                            if (taskToTag.containsTag(tagString)) {
                                ui.showError("This task already has that tag!");
                            } else {
                                taskToTag.addTag(tagString);

                                ui.showAddedTagMessage(taskToTag);
                                ui.countTasks(tasks);
                            }


                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid item to tag!");
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Task not found!");
                        }
                    }
            );
            break;

        case "removetag":
            command = new Command(
                    (tasks, ui, storage) -> {
                        try {

                            String itemString = fullCommand.split(" ")[1];
                            String tagToRemove = fullCommand.split(" ")[2];
                            int itemNumber = Integer.parseInt(itemString) - 1;
                            Task taskWithTagToRemove = tasks.get(itemNumber);

                            if (!taskWithTagToRemove.containsTag(tagToRemove)) {
                                ui.showError("This task does not contain that tag!");
                            } else {
                                taskWithTagToRemove.removeTag(tagToRemove);

                                ui.showRemovedTagMessage(taskWithTagToRemove);
                                ui.countTasks(tasks);
                            }


                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input a valid item to remove a tag!");
                        } catch (NumberFormatException e) {
                            System.out.println("Please input a number!");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Task not found!");
                        }
                    }
            );
            break;

        case "findtag":
            command = new Command(
                    (tasks, ui, storage) -> {

                        Predicate<? super Task> doesTaskContainTag = t -> t.containsTag(parameters);

                        ui.showTasksWithThisProperty(tasks, doesTaskContainTag);

                    }
            );
            break;

        default:
            throw new DukeException("Invalid Command!");
        }

        return command;
    }

}
