package duke.tools;

import java.util.HashMap;

import duke.exception.ContentNotFoundException;
import duke.exception.TaskNotFoundException;

/**
 * User input parser to make sense of user input for type of tasks.
 */
public class TaskParser extends Parser {
    private String[] keywords;
    private String input;
    public enum Tasks {
        TODO,
        DEADLINE,
        EVENT
    }

    private final HashMap<String, Tasks> taskMap = new HashMap<>();

    /**
     * Creates a task parser to make sense of user inputs.
     * @param input User input string.
     */
    public TaskParser(String input) {
        taskMap.put("todo", Tasks.TODO);
        taskMap.put("deadline", Tasks.DEADLINE);
        taskMap.put("event", Tasks.EVENT);
        this.input = input;
        this.keywords = input.split(" ", 2);
    }

    /**
     * Returns a constant of the corresponding user input task
     * @return a constant of the corresponding user input task
     * @throws TaskNotFoundException When user input command does not correspond to recognised command.
     */
    @Override
    public Tasks getCommand() throws TaskNotFoundException {
        String command = this.keywords[0];

        if (!taskMap.containsKey(command)) {
            throw new TaskNotFoundException(input);
        }

        return taskMap.get(command);
    }

    /**
     * Returns information about the task
     * @return information about the task
     * @throws ContentNotFoundException When user input a task command without any content.
     */
    public String getTaskInfo() throws ContentNotFoundException {
        if (keywords.length <= 1) {
            throw new ContentNotFoundException(
                    "Input error: no content found after task type.");
        }

        return this.keywords[1];
    }

}
