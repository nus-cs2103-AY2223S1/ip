package duke.tools;

import duke.exception.ContentNotFoundException;
import duke.exception.TaskNotFoundException;
import java.util.HashMap;

public class TaskParser extends Parser {
    private String[] keywords;
    private String input;
    public enum TASKS {
        TODO,
        DEADLINE,
        EVENT
    }

    private final HashMap<String, TASKS> taskMap = new HashMap<>();

    public TaskParser(String input) {
        taskMap.put("todo", TASKS.TODO);
        taskMap.put("deadline", TASKS.DEADLINE);
        taskMap.put("event", TASKS.EVENT);
        this.input = input;
        this.keywords = input.split(" ", 2);
    }

    @Override
    public TASKS getCommand() throws TaskNotFoundException {
        String command = this.keywords[0];
        if (!taskMap.containsKey(command)) {
            throw new TaskNotFoundException(input);
        }
        return taskMap.get(command);
    }

    public String getTaskInfo() throws ContentNotFoundException {
        if (keywords.length > 1) {
            return this.keywords[1];
        } else {
            throw new ContentNotFoundException(
                    "Input error: no content found after todo");
        }
    }

}
