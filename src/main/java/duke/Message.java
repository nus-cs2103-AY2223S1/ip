package duke;

import duke.task.TaskList;

//@@author Sampy147-reused
//Reused from https://github.com/nus-cs2103-AY1920S1/duke/pull/266/files
//with minor modifications, appropriated the idea of a Message class
public class Message {

    public static final String HORIZONTAL_BORDER = "______________________________________________________";
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_MESSAGE = "Bye! I'll be closing soon, till we meet again!";
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
            "Unfortunately, I am unable to recognise your command :( please input \"help\" for for more details";
    public static final String INVALID_DATE_INPUT = "The date given should not be before today's date";
    public static final String FILE_NOT_FOUND = "The memory file cannot be found.";
    public static final String FILE_READ_ERROR = "There is an error when reading the memory file.";
    public static final String FILE_CREATE_ERROR = "There is an error when creating the memory file";
    public static final String INVALID_FIND_TASK_FORMAT = "To find a task, please input this format: find {Keyword}";
    public static final String HELP_MESSAGE =
            "Hello! Here is some commands to help you use this app better!\n\n"
            + "1. bye:\nEnds the session and app will close\n\n"
            + "2. todo {task description}:\nAdds a to-do task to your list of tasks\n\n"
            + "3. event {task description} /at {time or place}:\nAdds a event task to your list of tasks\n\n"
            + "4. deadline {task description} /by {date time in YYYY-MM-DD}:\n"
                    + "Adds a deadline task to your list of tasks\n\n"
            + "5. list:\nReturns all tasks in the task list\n\n"
            + "6. help!:\nMore advanced Duke features!";
    public static final String ADVANCED_HELP_MESSAGE =
            "More advanced commands here!\n\n"
            + "1. mark {task number}:\nmarks the task with index corresponding to the task number as done\n\n"
            + "2. unmark {task number}:\nun-marks the task with index corresponding to the task number as done\n\n"
            + "3. delete {task number}:\ndeletes the task with index corresponding to the task number\n\n"
            + "4. find {keyword}:\nfind the task with description corresponding to the keyword input";

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
//@@author