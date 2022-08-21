package duke.command;

/**
 * Command to execute helping the user to know all the tasks Duke can do
 * @author Nephelite
 * @version 0.1
 */
public class HelpCommand extends Command {
    @Override
    public void execute() {
        System.out.println("These are the commands I know.");
        for (RecognisedCommand e : RecognisedCommand.values()) {
            switch (e) {
            case BYE:
                System.out.println("Ends my service.");
                break;
            case LIST:
                System.out.println("Lists all the tasks I have been given to track.");
                break;
            case HELP:
                System.out.println("Lists all the commands I know.");
                break;
            case MARK:
                System.out.println("Format: mark x, where x is an integer."
                        + "\nMarks the duke.task that is index x on the list as done.");
                break;
            case UNMARK:
                System.out.println("Format: unmark x, where x is an integer."
                        + "\nMarks the duke.task that is index x on the list as not done.");
                break;
            case DELETE:
                System.out.println("Format: delete x, where x is an integer."
                        + "\nMarks the duke.task that is index x on the list as done.");
                break;
            case TODO:
                System.out.println("Format: todo <duke.task>"
                        + "\nI will add the <duke.task> to the list of tasks.");
                break;
            case DEADLINE:
                System.out.println("Format: todo <duke.task> /by <time/date>"
                        + "\nI will add the <duke.task> to the list of tasks."
                        + "\nThe <duke.task> will also display its deadline at <time/date>.");
                break;
            case EVENT:
                System.out.println("Format: todo <duke.task> /at <time/date"
                        + "\nI will add the <duke.task> to the list of tasks."
                        + "\nThe <duke.task> will also display the <time/date> the duke.task should be done.");
                break;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
