package bloop;

import java.io.IOException;

/**
 * Parses the input and performs relevent operations.
 */
public class Parser {

    private static TaskList tasks;

    /**
     * Constructor for Parser object.
     *
     * @param tasks Tasklist object.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the input by the user.
     *
     * @param text Input by the user.
     * @throws BloopException If the command is not recognised or incomplete.
     * @throws IOException If there is a problem writing to file.
     */
    public static String parse(String text) throws BloopException, IOException {
        String[] textArr = text.split(" ");
        String command = textArr[0];
        String response = "";
        switch (command) {
        case "list":
            response = tasks.listOut(tasks.getList());
            break;
        case "unmark":
            response = tasks.unmark(tasks.get(Integer.parseInt(textArr[1]) - 1));
            break;
        case "mark":
            response = tasks.mark(tasks.get(Integer.parseInt(textArr[1]) - 1));
            break;
        case "todo":
            response = tasks.addTask(text, 'T');
            break;
        case "event":
            response = tasks.addTask(text, 'E');
            break;
        case "deadline":
            response = tasks.addTask(text, 'D');
            break;
        case "delete":
            Task task3 = tasks.get(Integer.parseInt(textArr[1]) - 1);
            response = tasks.remove(task3);
            break;
        case "find":
            response = tasks.findTasks(textArr[1]);
            break;
        case "sort":
            response = tasks.sortDeadlines();
            break;
        case "bye":
            response = "Until next time :)";
            break;
        default:
            throw new BloopException("I do not recognise your command.");
        }
        return response;
    }
}
