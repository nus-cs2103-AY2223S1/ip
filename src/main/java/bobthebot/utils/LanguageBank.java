package bobthebot.utils;

public class LanguageBank {
    public static final String MATCHING_ITEMS_MESSAGE =  "\tHere are the matching items on your list: \n";
    public static final String INVALID_INPUT_ERROR_MESSAGE = "\tDeepest apologies, I am a mere automated bot.\n"
            + "\tPlease stick to input that start with \n"
            + "\t1. todo - for items that you have to do\n"
            + "\t2. deadline - for items which have an upcoming deadline\n"
            + "\t3. event - for events with a date and time\n"
            + "\n"
            + "\t4. mark - to mark an event as done\n"
            + "\t5. unmark - to mark an event as undone\n"
            + "\t6. delete - to delete an event\n"
            + "\t7. list - to view all the events on your todo list\n"
            + "\t8. find - to find items in your list containing a certain keyword\n"
            + "\t9. bye - to wish me a (temporary) farewell";
    public static final String MARK_DONE_EMPTY_LIST_ERROR_MESSAGE = "\tPlease add items to your list "
            + "before wanting to mark them as done!";
    public static final String MARK_DONE_INVALID_INDEX_ERROR_MESSAGE = "\tPlease input an index to mark as done!";
    public static final String MARK_UNDONE_INVALID_INDEX_ERROR_MESSAGE = "\tPlease input an index to mark as undone!";
    public static final String MARK_UNDONE_EMPTY_LIST_ERROR_MESSAGE = "\tPlease add items to your list before "
            + "wanting to mark them as undone!";
    public static final String DELETE_INVALID_INDEX_ERROR_MESSAGE = "\tPlease input an index to delete!";
    public static final String DELETE_EMPTY_LIST_ERROR_MESSAGE = "\tPlease input an index of an existing task!";
    public static final String DEADLINE_INVALID_FORMAT_ERROR_MESSAGE = "\tInvalid formatting for deadline entered!\n"
            + "\tWrite your deadlines in the following format: \n"
            + "\t=> deadline <deadline-name> by <date>";
    public static final String DATE_INVALID_FORMAT_ERROR_MESSAGE = "\tInvalid formatting for date entered!\n"
            + "\tWrite your dates in the following format: YYYY-MM-DD HHHH";
    public static final String EVENT_INVALID_FORMAT_ERROR_MESSAGE = "\tInvalid formatting for event entered!\n"
            + "\tWrite your event in the following format: \n"
            + "\t=> event <event-name> by <event-date>";
    public static final String TODO_INVALID_FORMAT_ERROR_MESSAGE = "\tInvalid formatting for todo entered!\n"
            + "\tWrite your todos in the following format: \n"
            + "\t=> todo <todo-name>";
    public static final String DATE_BEFORE_PRESENT_ERROR_MESSAGE = "\tI might be a non-sentient robot but "
            + "you seem to be a time traveller!\n"
            + "\tPlease input dates AFTER the current date and time.";

    public static final String IOEXCEPTION_ERROR_MESSAGE = "Not sure what an IOException is but it has occurred.";
    public static final String FILE_LOADING_ERROR_MESSAGE = "Error occurred during file loading. I do not "
            + "process this task type.";

    public static final String WELCOME_MESSAGE = "\tHello! I am Bob the Bot, your friendly task manager! \uD83D\uDE0A\n"
            + "\tWhen using me, please stick to the following commands:\n"
            + "\t\t1. todo - for items that you have to do\n"
            + "\t\t2. deadline - for items which have an upcoming deadline\n"
            + "\t\t3. event - for events with a date and time\n"
            + "\t\t4. mark - to mark an event as done\n"
            + "\t\t5. unmark - to mark an event as undone\n"
            + "\t\t6. delete - to delete an event\n"
            + "\t\t7. list - to view all the events on your todo list\n"
            + "\t\t8. find - to find items in your list containing a certain keyword\n"
            + "\t\t9. bye - to wish me a (temporary) farewell";
    public static final String GOODBYE_MESSAGE = "\tBye! Hope to see you again soon!";
    public static final String COMPLETED_ALL_TASKS_GOODBYE_MESSAGE = "I'm so happy that you've completed all "
            + "your tasks! \n\tCome back soon "
            + "if you want to accomplish more things! \uD83D\uDE0A";
    public static final String LINE_BREAK = "  **********************************************************"
            + "********************************\n";
    public static final String TASK_ADDED_MESSAGE = "\tGot it. I've added this task: \n";
    public static final String TASK_DELETED_MESSAGE = "\tGot it. I've removed this task: \n";
    public static final String TASK_MARKED_DONE_MESSAGE = "\tGOOD JOB! I'm marking this task as done: \n";
    public static final String TASK_MARKED_UNDONE_MESSAGE = "\tIt's sad that you thought you finished "
            + "your work but didnt.\n"
            + "\tBut alright, marking this task as undone: \n";
    public static final String NO_TASKS_LEFT_MESSAGE = "\tYAY! There are no items in your list!";
    public static final String TASKS_LEFT_MESSAGE = "\tHere are your tasks:\n";
}
