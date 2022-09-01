package dan;

import java.time.format.DateTimeParseException;


/**
 * A parser to help parse and execute command-line inputs by the user
 */
public class Parser {
    private TaskList tasks;

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
    public boolean parse(String input) {
        String action = input.split(" ")[0];
        try {
            switch (action) {
            case "bye":
                Ui.sayonara();
                return true;

            case "list":
                tasks.showTasks();
                break;

            case "mark":
                tasks.markTask(Integer.parseInt(input.split(" ")[1]));
                break;

            case "unmark":
                tasks.unMarkTask(Integer.parseInt(input.split(" ")[1]));
                break;

            case "delete":
                tasks.deleteTask(Integer.parseInt(input.split(" ")[1]));
                break;

            case "todo":
                //fall through
            case "deadline":
                //fall through
            case "event":
                tasks.addTask(input);
                break;

            default:
                throw new DanException("I don't really understand what do you mean by that...");
            }

        } catch (DanException e) {
            Ui.printBlock(e.getMessage());
        } catch (NumberFormatException nfe) {
            Ui.printBlock("Please use an integer instead");
        } catch (DateTimeParseException dte) {
            Ui.printBlock("Please use the format dd/MM/yyyy HHmm for dates");
        }
        return false;
    }
}
