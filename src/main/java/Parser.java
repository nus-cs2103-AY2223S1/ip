import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Parser(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }

    public static Command parseCommand(String command) throws SallyException {
        // Bye
        if (command.equals("bye")) {
            new ByeCommand();
        }

        // List
        else if (command.startsWith("list")) {
            System.out.println("enters list");
            System.out.println("tasks: " + tasks);
            if (tasks == null) {
                System.out.println("You currently have no list :(");
            } else {
                ui.showList(tasks);
            }
        }

        // Delete
        else if (command.startsWith("delete")) {
            if (command.length() > 7 && command.substring(7).trim().chars().allMatch(Character::isDigit)) {
                int taskNum = Integer.parseInt(command.substring(7).trim()) - 1;
                return new DeleteCommand(taskNum);
            } else {
                throw new SallyException.SallyInvalidInputException();
            }
        }

        // Unmark
        else if (command.startsWith("unmark")) {
            int taskNum = Integer.parseInt(command.substring(7)) - 1; // -1 so that index is constant
            if (taskNum >= 0 && taskNum < tasks.getNumOfTasks()) {
                return new UnmarkCommand(taskNum);
            } else {
                throw new SallyException.SallyTaskNotFoundException();
            }
        }

        // Mark
        else if (command.startsWith("mark")) {
            int taskNum = Integer.parseInt(command.substring(5)) - 1; // -1 so that index is constant
            if (taskNum >= 0 && taskNum < tasks.getNumOfTasks()) {
                return new MarkCommand(taskNum);
            } else {
                throw new SallyException.SallyTaskNotFoundException();
            }
        }

        // Task Commands
        else {
            // ToDos
            if (command.startsWith("todo")) {
                if (command.length() > 4) {
                    String description = command.substring(5);
                    Task task = new ToDo(description, true);
                    return new AddCommand(task);
                } else {
                    throw new SallyException.SallyNoDescriptionException();
                }
            }

            // Deadlines
            else if (command.startsWith("deadline")) {
                String description, by;
                if (command.length() <= 8) {
                    throw new SallyException.SallyInvalidInputException();
                } else if (command.contains("/by ")) {
                    description = command.substring(9, command.indexOf("/by") - 1);
                    by = command.substring(command.indexOf("/by") + 4);
                    LocalDate localDate;
                    try {
                        localDate = LocalDate.parse(by);
                        by = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Oops! Date has to be in the format of yyyy-mm-dd");
                    }
                    Task task = new Deadline(description, by,true);
                    return new AddCommand(task);
                } else {
                    throw new SallyException.SallyNoDeadlineException();
                }
            }

            // Events
            else if (command.startsWith("event")) {
                String description, at;
                if (command.length() <= 5) {
                    throw new SallyException.SallyInvalidInputException();
                } else if (command.contains("/at ")) {
                    description = command.substring(6, command.indexOf("/at") - 1);
                    at = command.substring(command.indexOf("/at") + 4);
                    Task task = new Event(description, at, true);
                    return new AddCommand(task);
                } else {
                    throw new SallyException.SallyNoPlaceException();
                }
            }

            // Any other messages
            else {
                throw new SallyException.SallyInvalidInputException();
            }
        }

        return new ByeCommand(); // Perlu diganti
    }
}
