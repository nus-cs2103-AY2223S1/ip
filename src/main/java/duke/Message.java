package duke;

import duke.task.TaskList;

public class Message {

    public static final String HORIZONTAL_BORDER = "________________________________________________________";
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String INVALID_TODO_INPUT = "The description of a todo cannot be empty.";
    public static final String INVALID_DEADLINE_INPUT =
            "Please use proper deadline formatting: deadline {task} /by {time}";
    public static final String INVALID_DATE_FORMAT =
            "Please indicate your date after {/bye} as YYYY-MM-DD (e.g 2019-12-09)";
    public static final String INVALID_EVENT_INPUT = "Please use proper event formatting: event {task} /at {time}";
    public static final String INVALID_ACCESS_EMPTY_TASKLIST =
            "Task does not exist. Initialise a task first, then try again";
    public static final String INVALID_MARK_TASK_FORMAT =
            "To mark a task, please input this format: mark {task number}";
    public static final String INVALID_UNMARK_TASK_FORMAT =
            "To unmark a task, please input this format: unmark {task number}";
    public static final String INVALID_DELETE_TASK_FORMAT =
            "To delete a task, please input this format: delete {task number}";
    public static final String INVALID_USER_INPUT =
            "Please use one of these keywords: {deadline, event, todo} followed by \\\"by\\\" and \\\"at\\\" "
                    + "for deadline and event tasks respectively.";
    public static final String INVALID_DATE_INPUT = "The date given should not be before today's date";
    public static final String FILE_NOT_FOUND = "The memory file cannot be found.";
    public static final String FILE_READ_ERROR = "There is an error when reading the memory file.";
    public static final String FILE_CREATE_ERROR = "There is an error when creating the memory file";
    public static final String INVALID_FIND_TASK_FORMAT = "To find a task, please input this format: find {Keyword}";

    /**
     * Returns a String that describes that the task does not exist within the specified tasklist
     * and suggests potential numbers for the user to try to locate the task. This method is called
     * when the task is not found in the tasklist specified
     *
     * @param tasks the tasklist of type TaskList
     * @return a String message describing the task is not found
     */
    public static String returnTaskNotFound(TaskList tasks) {
        return "Task does not exist. Try another number between 1 and " + tasks.getCount();
    }

}
