package dan.parser;

import java.time.format.DateTimeParseException;

import dan.DanException;
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
        String action = input.split(" ")[0];
        try {
            switch (action) {
            case "bye":
                isExit = true;
                return Ui.bye();
            case "list":
                return tasks.showTasks();
            case "mark":
                return tasks.markTask(Integer.parseInt(input.split(" ")[1]));
            case "unmark":
                return tasks.unmarkTask(Integer.parseInt(input.split(" ")[1]));
            case "delete":
                return tasks.deleteTask(Integer.parseInt(input.split(" ")[1]));
            case "find":
                return tasks.findTask(input.split(" ", 2)[1]);
            case "todo":
                //fall through
            case "deadline":
                //fall through
            case "event":
                return tasks.addTask(input);
            default:
                throw new DanException("I don't really understand what do you mean by that...");
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

}
