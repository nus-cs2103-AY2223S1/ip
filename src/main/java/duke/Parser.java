package duke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Handles the main logic of parsing raw input
 */
public class Parser {

    private static final List<String> PERMISSIBLE_TASKS = new ArrayList<>(
            Arrays.asList("todo", "event", "deadline"));
    public static void parseData(String input, TaskList taskList) {

        //TODO: Update with a switch statement for parsing data
        //Case 1: Mark
        if (input.startsWith("mark")) {
            //split by space, then the second value
            int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
            taskList.markTask(taskIndex);

        //Case 2: Unmark
        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
            taskList.unmarkTask(taskIndex);

        //Case 3: List
        } else if (input.equals("list")) {
            taskList.list();

        //Case 4: Delete
        } else if (input.startsWith("delete")) {
            int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;

            taskList.deleteTask(taskIndex);

        } else if (input.startsWith("find")) {
            String[] tempArr = input.split(" ", 2); //split into 2
            String keyword = tempArr[1]; //the remainder of the input minus whitespace
            taskList.findTask(keyword);
        //Case 5: Add a valid task
        } else {

            try {
                validateTask(input);
            } catch (InvalidCommandException ice) {
                Ui.displayException(ice);
                Ui.displayMessage("This was your invalid command: " + input);
            } catch (EmptyTaskException ete) {
                Ui.displayException(ete);
                String[] tempArr = input.split(" ", 0);
                if (tempArr[0].equals("todo")) {
                    Ui.displayMessage("todo requires at least a task description");
                } else {
                    Ui.displayMessage("Event/Deadline requires both a task description and a date");
                }
            }

            Task newTask = generateTask(input);
            taskList.addTask(newTask);
        }
    }

    private static void validateTask(String input) throws InvalidCommandException, EmptyTaskException {
        String[] tempArr = input.split(" ", 0); //splits into words

        //first word is invalid
        if (! PERMISSIBLE_TASKS.contains(tempArr[0])) {
            throw new InvalidCommandException("I'm sorry, I don't understand what that means \n"
                    + "Please enter a valid response in the future");
        }

        if (tempArr.length <= 1) {
            throw new EmptyTaskException("The description of a task cannot be empty.");
        }

    }


    //changed to public for testing, TODO: change private after validation
    public static Task generateTask(String input) {
        String[] tempArr = input.split(" ", 2);
        if (input.startsWith("todo")) {
            return new Todo(tempArr[1]);
        } else if (input.startsWith("deadline")) {
            int firstSplit = tempArr[1].indexOf("/by");
            String eventName = tempArr[1].substring(0, firstSplit);
            String date = tempArr[1].substring(firstSplit + 4);
            return new Deadline(eventName, date);
        } else { //must be Event
            int firstSplit = tempArr[1].indexOf("/at");
            String eventName = tempArr[1].substring(0, firstSplit);
            String date = tempArr[1].substring(firstSplit + 4);
            return new Event(eventName, date);
        }
    }
}
