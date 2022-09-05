package gibson.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gibson.Storage;

/**
 * Parser is a class for parsing and processing
 * user inputs for the Gibson program.
 */
public class Parser {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a parser object to parse and process user inputs.
     */
    public Parser() {
        storage = new Storage("data", "gibson.txt");
        if (storage.hasDataToLoad()) {
            taskList = new TaskList(storage.load());
        } else {
            taskList = new TaskList();
        }
    }

    /**
     * Processes user inputs and returns a reply.
     * @param input the user input string
     * @return a reply from processing the input
     */
    public String processInput(String input) {
        // BYE
        if (input.equals("bye")) {
            return processByeCommand();
        // LIST
        } else if (input.equals("list")) {
            return processListCommand();
        // MARK
        } else if (Pattern.matches("mark [0-9]+", input)) {
            return processMarkCommand(input);
        // UNMARK
        } else if (Pattern.matches("unmark [0-9]+", input)) {
            return processUnmarkCommand(input);
        // TODOS
        } else if (Pattern.matches("(todo .+)|(todo( )?)", input)) {
            return processTodoCommand(input);
        // DEADLINES
        } else if (Pattern.matches("(deadline .+ /by .+)|(deadline .+ /by( )?)", input)) {
            return processDeadlineCommand(input);
        // EVENTS
        } else if (Pattern.matches("(event .+ /at .+)|(event .+ /at( )?)", input)) {
            return processEventCommand(input);
        // REMOVE
        } else if (Pattern.matches("delete [0-9]+", input)) {
            return processRemoveCommand(input);
        // FIND
        } else if (Pattern.matches("(find .+)|(find( )?)", input)) {
            return processFindCommand(input);
        // NOT RECOGNIZED
        } else {
            return processNotRecognizedCommand();
        }
    }

    private String processByeCommand() {
        return "Bye! Hope to see you soon.";
    }

    private String processListCommand() {
        return "Here are the task(s) in your list:\n" + taskList.toString();
    }

    private String processMarkCommand(String input) {
        int number = Parser.getTrailingInt(input);
        int index = number - 1;
        try {
            Task t = taskList.mark(index);
            storage.save(taskList.toString());
            return "Nice! I've marked this task as done:\n" + t.toString();
        } catch (IndexOutOfBoundsException e) {
            return "There is no task numbered as " + number + ".";
        }
    }

    private String processUnmarkCommand(String input) {
        int number = Parser.getTrailingInt(input);
        int index = number - 1;
        try {
            Task t = taskList.unmark(index);
            storage.save(taskList.toString());
            return "OK, I've marked this task as not done yet:\n" + t.toString();
        } catch (IndexOutOfBoundsException e) {
            return "There is no task numbered as " + number + ".";
        }
    }

    private String processTodoCommand(String input) {
        String taskString = Parser.substringAfterToken(input, "todo");
        try {
            Task task = new Task(taskString);
            return processAddTask(task);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private String processDeadlineCommand(String input) {
        String taskString = Parser.substringAfterToken(input, "deadline");
        String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/by");
        try {
            Deadline deadline = new Deadline(stringArray[0], stringArray[1]);
            return processAddTask(deadline);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private String processEventCommand(String input) {
        String taskString = Parser.substringAfterToken(input, "event");
        String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/at");
        try {
            Event event = new Event(stringArray[0], stringArray[1]);
            return processAddTask(event);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private String processRemoveCommand(String input) {
        int number = Parser.getTrailingInt(input);
        int index = number - 1;
        try {
            Task t = taskList.remove(index);
            storage.save(taskList.toString());
            return "Noted. I've removed this task:\n"
                    + t.toString()
                    + "\nNow you have " + taskList.size() + " task(s) in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "There is no task numbered as " + number + ".";
        }
    }

    private String processFindCommand(String input) {
        String searchQuery = Parser.substringAfterToken(input, "find");
        TaskList result = taskList.find(searchQuery);
        if (!result.isEmpty()) {
            return "Here are your results:\n" + result.toString();
        } else {
            return "Unable to find task with \"" + searchQuery + "\".";
        }
    }

    private String processNotRecognizedCommand() {
        return "I'm sorry. I do not know what that means.";
    }

    private String processAddTask(Task task) {
        if (!taskList.isDuplicate(task)) {
            taskList.add(task);
            storage.save(taskList.toString());
            return "Got it. I've added this task:\n"
                    + task
                    + "\nNow you have " + taskList.size() + " task(s) in the list.";
        } else {
            return "Duplicate task detected! Task was not added.";
        }
    }

    /**
     * Returns trailing integers from a given string.
     * @param string the string to get trailing integers from
     * @return the trailing integers
     */
    public static int getTrailingInt(String string) {
        Pattern pattern = Pattern.compile("[^0-9]+([0-9]+)$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalArgumentException("String is invalid");
        }
    }

    /**
     * Returns substring after the first instance of the token string.
     * @param string the string to be substring-ed
     * @param token the string that marks where to substring-ed
     * @return substring after the token string
     * @throws IllegalArgumentException if token is not found in the string
     */
    public static String substringAfterToken(String string, String token) {
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                return string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                return "";
            }
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }

    /**
     * Returns substring before and after the first instance of the token string.
     * Before stored in [0]. After stored in [1].
     * @param string the string to be split
     * @param token the string that marks where to split
     * @return an array of 2 strings
     * @throws IllegalArgumentException if token cannot be found in given string
     */
    public static String[] substringBeforeAfterToken(String string, String token) {
        String[] stringArray = new String[2];
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            try {
                stringArray[0] = string.substring(0, matcher.start() - 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[0] = "";
            }
            try {
                stringArray[1] = string.substring(matcher.end() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                stringArray[1] = "";
            }
            return stringArray;
        } else {
            throw new IllegalArgumentException("Unable to find token in string");
        }
    }
}
