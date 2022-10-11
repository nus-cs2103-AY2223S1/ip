package dan.parser;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import dan.exceptions.DanException;
import dan.task.TaskList;
import dan.ui.Ui;

/**
 * A parser to help parse and execute command-line inputs by the user
 */
public class Parser {
    private TaskList tasks;
    private boolean isExit = false;

    /**
     * Constructor method. Associates the parser to its list of tasks to perform actions on.
     *
     * @param tasks TaskList of tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the command-line input before executing the command.
     *
     * @param input The raw input data entered by the user
     * @return A boolean of the program exit status
     */
    public String parse(String input) {
        String[] inputArr = input.strip().split(" ");
        String action = inputArr[0];
        Stream<String> remainingInputs = Stream.of(inputArr).skip(1);
        assert !isExit : "The program should have exited already";
        try {
            switch (action) {
            case "bye":
                isExit = true;
                return Ui.bye();
            case "list":
                return tasks.showTasks();
            case "mark":
                return tasks.markTask(convertToIntArray(remainingInputs));
            case "unmark":
                return tasks.unmarkTask(convertToIntArray(remainingInputs));
            case "delete":
                return tasks.deleteTask(convertToIntArray(remainingInputs));
            case "find":
                return tasks.findTask(convertToStringArray(remainingInputs));
            case "todo":
                return tasks.addToDoTask(input);
            case "deadline":
                return tasks.addDeadlineTask(input);
            case "event":
                return tasks.addEventTask(input);
            default:
                throw DanException.userInputError();
            }
        } catch (DanException e) {
            return Ui.printIndent(e.getMessage());
        } catch (NumberFormatException nfe) {
            return Ui.printIndent("Please use an integer instead");
        } catch (DateTimeParseException dte) {
            return Ui.printIndent("Please use the format dd/MM/yyyy HHmm for dates");
        }
    }

    public boolean getIsExit() {
        return isExit;
    }

    private int[] convertToIntArray(Stream<String> stream) {
        return stream.mapToInt(x -> Integer.parseInt(x))
                .toArray();
    }

    private String[] convertToStringArray(Stream<String> stream) {
        return stream.toArray(String[]::new);
    }
}
