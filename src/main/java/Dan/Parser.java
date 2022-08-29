package dan;

import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }
    
    public boolean Parse(String input) {
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
