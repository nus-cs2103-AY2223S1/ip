package duke;

/**
 * Parse class that handles the string inputted by user.
 */
public class Parser {
    private Duke duke;

    /**
     * Constructor for Parser Class.
     *
     * @param duke The duke object the user is operating on.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the various inputs inputted by user.
     *
     * @param input A String representing the user's input.
     * @return A String of response based on user's input.
     */
    public String start(String input) {
        String[] splitInput = input.split(" ", 2);
        try {
            switch (splitInput[0]) {
            //Handle case when task aTodo
            case "todo":
                checkForMissingArgs(splitInput);
                String tDescription = splitInput[1].trim();
                return duke.addTodo(tDescription);

            //Handle case when task is a deadline
            case "deadline": {
                checkForMissingArgs(splitInput);
                String str = splitInput[1].trim();
                return duke.addDeadline(str);
            }

            //Handle case when task is an event
            case "event": {
                checkForMissingArgs(splitInput);
                String str = splitInput[1].trim();
                return duke.addEvent(str);
            }

            //Handle case when user wants to list tasks
            case "list":
                return duke.printList();

            //Handle case when user wants to handleMark task
            case "mark": {
                //-1 to get index in 0 indexing
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.handleMark(index);
            }

            //Handle case when user wants to handleUnmark task
            case "unmark": {
                //-1 to get index in 0 indexing
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.handleUnmark(index);
            }

            //Handle case when user wants to handleDelete task
            case "delete": {
                //-1 to get in 0 indexing
                int index = Integer.parseInt(splitInput[1]) - 1;
                return duke.handleDelete(index);
            }

            //Handle case when user wants to find tasks
            case "find":
                String str = splitInput[1].trim();
                return duke.find(str);

            //Handle case when user wants to quit bot
                case "bye":
                    return duke.handleBye();

            //Default case: Not any of the tasks(aTodo, Deadline, Event) and hence, throws an Exception
            default:
                //To handle any extra words the user keyed in
                throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");

            }
        } catch (DukeException dE) {
            Ui ui = new Ui();
            return ui.showErrorMessage(dE.getMessage());
        }
    }

    /**
     * Checks for any missing arguments from user's input.
     *
     * @param input An Array of String from the user's input.
     * @throws DukeException
     */
    public void checkForMissingArgs(String[] input) throws DukeException {
        if (input.length == 1 || input[1].trim().isEmpty()) {
            throw new DukeException("OOPS! You are missing some information in your command!");
        }
    }
}
