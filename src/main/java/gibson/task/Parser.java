package gibson.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gibson.Storage;

/**
 * Parser is a utility class for parsing user inputs into meaningful data.
 */
public final class Parser {
    private Storage storage;
    private TaskList taskList;

    public Parser() {
        storage = new Storage("data", "gibson.txt");
        taskList = new TaskList(storage.load());
    }

    /**
     * Start running the Gibson program from an instance of Gibson.
     */
    public String processInput(String input) {
        // BYE
        if (input.equals("bye")) {
            return "Bye! Hope to see you soon.";
            // LIST
        } else if (input.equals("list")) {
            return "Here are the task(s) in your list:\n" + taskList.toString();
            // MARK
        } else if (Pattern.matches("mark [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.mark(index);
                storage.save(taskList.toString());
                return "Nice! I've marked this task as done:\n" + t.toString();
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
            // UNMARK
        } else if (Pattern.matches("unmark [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.unmark(index);
                storage.save(taskList.toString());
                return "OK, I've marked this task as not done yet:\n" + t.toString();
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
            // TODOS
        } else if (Pattern.matches("(todo .+)|(todo( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "todo");
            try {
                Task task = new Task(taskString);
                taskList.add(task);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            // DEADLINES
        } else if (Pattern.matches("(deadline .+ /by .+)|(deadline .+ /by( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "deadline");
            String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/by");
            try {
                Deadline deadline = new Deadline(stringArray[0], stringArray[1]);
                taskList.add(deadline);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + deadline
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            // EVENTS
        } else if (Pattern.matches("(event .+ /at .+)|(event .+ /at( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "event");
            String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/at");
            try {
                Event event = new Event(stringArray[0], stringArray[1]);
                taskList.add(event);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + event
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
            // REMOVE
        } else if (Pattern.matches("delete [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.remove(index);
                storage.save(taskList.toString());
                return "Noted. I've removed this task:\n"
                        + t.toString()
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
            // FIND
        } else if (Pattern.matches("(find .+)|(find( )?)", input)) {
            String searchQuery = Parser.substringAfterToken(input, "find");
            TaskList result = taskList.find(searchQuery);
            if (!result.isEmpty()) {
                return "Here are your results:\n" + result.toString();
            } else {
                return "Unable to find task with \"" + searchQuery + "\".";
            }
            // NOT RECOGNIZED
        } else {
            return "I'm sorry. I do not know what that means.";
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
