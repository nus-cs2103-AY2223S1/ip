package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.function.BiFunction;

/**
 * Defines <Code>Parser</Code> class.
 * <p>
 *  <Code>Parser</Code> class is in charge of the following:
 *  <ul>
 *      <li>Parse user inputs given as `String`.</li>
 *      <li>Check for erroneous inputs.</li>
 *      <li>Return relevant command to handle input.</li>
 *  </ul>
 * </p>
 */
public class Parser {
    /**
     * <Code>BiFunction</Code> to add task to `taskList`.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> addTask =
            TaskList::add;

    /**
     * <Code>BiFunction</Code> to delete task from <Code>taskList</Code>.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> deleteTask =
            TaskList::delete;

    /**
     * <Code>BiFunction</Code> to mark task as done.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> markTask = (
            taskList, input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        return taskList.markDone(i);
    };

    /**
     * <Code>BiFunction</Code> to mark task as undone.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> unmarkTask = (
            taskList, input) -> {
        int i = Integer.parseInt(input.split(" ")[1]);
        return taskList.markUndone(i);
    };

    /**
     * <Code>BiFunction</Code> to list out all tasks in <Code>taskList</Code>.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> listTasks = (
            taskList, input) -> {
        // List inputs in 'userInput' list.
        return taskList.toString();
    };

    /**
     * <Code>BiFunction</Code> to find word among tasks in the task list.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> findTask = (
            taskList, input) -> {
        return taskList.findWord(input.split(" ")[1]);
    };

    /**
     * <Code>BiFunction</Code> to exit program.
     * <p>
     *  Accepts:
     *  <ol>
     *     <li><Code>TaskList</Code> object of the running <Code>Duke</Code>
     *     program.</li>
     *     <li>Full <Code>String</Code> input from user.</li>
     *  </ol>
     *  Returns:
     *  <ol>
     *     <li><Code>String</Code> output to be displayed to user.</li>
     *  </ol>
     * </p>
     */
    private static final BiFunction<TaskList, String, String> quit = (
            taskList, input) -> {
        // Exit
        return "exit sequence initiated";
    };

    /**
     * <Code>HashMap</Code> of commands that maps to their
     * respective <Code>BiFunction</Code>s.
     */
    private static final HashMap<String,
            BiFunction<TaskList, String, String>> commands = new HashMap<>();
    static {
        commands.put("deadline", addTask);
        commands.put("event", addTask);
        commands.put("todo", addTask);
        commands.put("delete", deleteTask);
        commands.put("mark", markTask);
        commands.put("unmark", unmarkTask);
        commands.put("list", listTasks);
        commands.put("find", findTask);
        commands.put("bye", quit);
    }

    /**
     * Points to <Code>Duke</Code>'s current taskList. Used to keep track
     * of size.
     */
    private final TaskList taskList;

    /**
     * Constructor for <Code>Parser</Code> object.
     * @param taskList <Code>TaskList</Code> object belonging to
     *                 <Code>Duke</Code>.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handles user inputs and check for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    public BiFunction<TaskList, String, String> handleUserInputs(
            String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        String command = args[0];
        // Check if input is correct.
        switch(command) {
        case "deadline":
            testInputForDeadline(userInput);
            break;
        case "event":
            testInputForEvent(userInput);
            break;
        case "todo":
            testInputForToDo(userInput);
            break;
        case "delete":
            testInputForDelete(userInput);
            break;
        case "mark":
            testInputForMark(userInput);
            break;
        case "unmark":
            testInputForUnmark(userInput);
            break;
        case "list":
            testInputForList(userInput);
            break;
        case "find":
            testInputForFind(userInput);
            break;
        case "bye":
            testInputForBye(userInput);
            break;
        default:
            // Case where no commands are matched.
            throw new DukeException("Sorry, I did not quite get that! "
                    + String.format("(%s)\n", userInput));
        }
        // Call respective 'Consumer' object on input once it has been verified.
        return commands.get(command);
    }

    /**
     * Checks input of "deadline" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForDeadline(String userInput) throws DukeException {
        Boolean hasNoSpaceAtIndexEight = userInput.indexOf(" ") != 8;
        Boolean isMissingDelimiter = userInput.indexOf("/by ") <= 9;
        if (hasNoSpaceAtIndexEight || isMissingDelimiter) {
            throw new DukeException("Wrong format! To add a new "
                    + "deadline, please enter the following:\n"
                    + "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n");
        }
        String dateString =
                userInput.substring(userInput.indexOf("/by") + 4);
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format! To add a new "
                    + "deadline, please enter the following:\n"
                    + "   deadline [TASK DESCRIPTION] /by "
                    + "[YYYY/MM/DD]\n");
        }
    }

    /**
     * Checks input of "event" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForEvent(String userInput) throws DukeException {
        Boolean hasNoSpaceAtIndexFive = userInput.indexOf(" ") != 5;
        Boolean isMissingDelimiter = userInput.indexOf("/at ") <= 6;
        if (hasNoSpaceAtIndexFive || isMissingDelimiter) {
            throw new DukeException("Wrong format! To add a new "
                    + "event, please enter the following:\n"
                    + "   event [TASK DESCRIPTION] /at [VENUE]\n");
        }
    }

    /**
     * Checks input of "todo" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForToDo(String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        if ((args.length < 2)) {
            throw new DukeException("Wrong format! To create a "
                    + "'todo' task, type:\n   todo [DESCRIPTION]\n");
        }
    }

    /**
     * Checks input of "delete" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForDelete(String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        int taskNumber;
        int size = taskList.getSize();
        if ((args.length != 2)) {
            throw new DukeException("Wrong format! To delete a task, "
                    + "type:\n   delete [TASK NUMBER]\n");
        }

        try {
            taskNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be an integer!"
                    + "\n   delete [TASK NUMBER]\n");
        }
        Boolean taskNumberIsNegativeOrZero = taskNumber <= 0;
        Boolean taskNumberIsLargerThanTaskCount = taskNumber > size;
        if (taskNumberIsNegativeOrZero || taskNumberIsLargerThanTaskCount) {
            throw new DukeException("Task number is invalid."
                    + String.format("You have %d tasks!", size)
                    + "\n   delete [TASK NUMBER]\n");
        }
    }

    /**
     * Checks input of "mark" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForMark(String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        int taskNumber;
        int size = taskList.getSize();
        if ((args.length != 2)) {
            throw new DukeException("Wrong format! To mark a task as "
                    + "done, type:\n   mark [TASK NUMBER]\n");
        }

        try {
            taskNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be an integer!\n"
                    + "   mark [TASK NUMBER]\n");
        }
        Boolean taskNumberIsNegativeOrZero = taskNumber <= 0;
        Boolean taskNumberIsLargerThanTaskCount = taskNumber > size;
        if (taskNumberIsNegativeOrZero || taskNumberIsLargerThanTaskCount) {
            throw new DukeException("Task number is invalid."
                    + String.format("You have %d tasks!\n", size)
                    + "   mark [TASK NUMBER]\n");
        }
    }

    /**
     * Checks input of "unmark" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForUnmark(String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        int taskNumber;
        int size = taskList.getSize();
        if ((args.length != 2)) {
            throw new DukeException("Wrong format! To unmark a task "
                    + "as done, type:\n   unmark [TASK NUMBER]\n");
        }

        try {
            taskNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be an integer!\n"
                    + "   unmark [TASK NUMBER]\n");
        }
        Boolean taskNumberIsNegativeOrZero = taskNumber <= 0;
        Boolean taskNumberIsLargerThanTaskCount = taskNumber > size;
        if (taskNumberIsNegativeOrZero || taskNumberIsLargerThanTaskCount) {
            throw new DukeException("Task number is invalid.\n"
                    + String.format("You have %d tasks!\n", size)
                    + "   mark [TASK NUMBER]\n");
        }
    }

    /**
     * Checks input of "list" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForList(String userInput) throws DukeException {
        if (!userInput.equals("list")) {
            // Whole input should only be "list"
            throw new DukeException("Wrong format! To list tasks, "
                    + "type:\n   list\n");
        }
    }

    /**
     * Checks input of "find" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForFind(String userInput) throws DukeException {
        String[] args = userInput.split(" ");
        if ((args.length != 2)) {
            throw new DukeException("Wrong format! To find a word, type:\n"
                    + "   find [WORD TO FIND]\n");
        }
    }

    /**
     * Checks input of "bye" commands for errors.
     * @param userInput      Full <Code>String</Code> input from user.
     * @throws DukeException Throw exception if command format is wrong.
     */
    private void testInputForBye(String userInput) throws DukeException {
        if (!userInput.equals("bye")) {
            // Whole input should only be "bye"
            throw new DukeException("Wrong format! To exit, type:\n"
                    + "   bye\n");
        }
    }


}
