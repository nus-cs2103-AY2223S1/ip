package bestie.utils;

/**
 * Class which stores all the constants which act as responses for Bestie.
 */
public class LanguageBank {
    public static final String MATCHING_ITEMS_MESSAGE = "\tSLAYYYY these slays\u2728 match:  \n";
    public static final String INVALID_INPUT_ERROR_MESSAGE = "\tSO SORRY BESTIE! I'm just a chatbot...\n"
            + "\tPlease just use these commands \n"
            + "\t1. todo - for slays\u2728 that you have to slay\n"
            + "\t2. deadline - for slays\u2728 which have an upcoming deadline\n"
            + "\t3. event - for slays\u2728 with a date and time\n"
            + "\t4. mark - to mark a slay\u2728 as done\n"
            + "\t5. unmark - to mark a slay\u2728 as undone\n"
            + "\t6. delete - to delete a slay\u2728\n"
            + "\t7. list - to view all the slays\u2728 on your todo list\n"
            + "\t8. find - to find slays\u2728 in your list containing a certain keyword\n"
            + "\t9. bye - to leave me\uD83D\uDE2D\uD83D\uDE2D";
    public static final String MARK_DONE_EMPTY_LIST_ERROR_MESSAGE = "\tUr list is empty bestie! "
            + "add some slays\u2728 before telling me you've slayed\u2728!";
    public static final String MARK_DONE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay\u2728 "
            + "you've slayed\u2728 bestie!";
    public static final String MARK_UNDONE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay\u2728 "
            + "you wanna unslay\uD83D\uDC4E bestie...";
    public static final String MARK_UNDONE_EMPTY_LIST_ERROR_MESSAGE = "\tUr list is empty bestie! "
            + "add some slays\u2728 before telling me you've unslayed\uD83D\uDC4E!";
    public static final String DELETE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay\u2728 you wanna "
            + "slay [literal] bestie!";
    public static final String DELETE_EMPTY_LIST_ERROR_MESSAGE = "\tBestie! You can't slay "
            + "[literal] that task tho....";
    public static final String DEADLINE_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u "
            + "stick to this format??\u2728\u2728\u2728\n"
            + "\t=> deadline <description> by <date> <time>";
    public static final String DATE_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick "
            + "to this format??\u2728\u2728\u2728\n"
            + "\tYYYY-MM-DD HHHH";
    public static final String EVENT_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick "
            + "to this format??\u2728\u2728\u2728\n"
            + "\t=> event <description> at <date> <time>";
    public static final String TODO_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick to "
            + "this format??\u2728\u2728\u2728\n"
            + "\t=> todo <todo-name>";
    public static final String DATE_BEFORE_PRESENT_ERROR_MESSAGE = "\tBESTIE\u2728\u2728\u2728!!! "
            + "I didn't know you could go back in time omg... "
            + "\tBut seriously.. Could you put the date n time as AFTER the current date n time??"
            + "\uD83D\uDE0A\uD83D\uDE0A";
    public static final String FIND_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie! Enter a keyword please!";
    public static final String FIND_EMPTY_LIST_MESSAGE = "\tBestie! Your list is empty omgg!!";
    public static final String FIND_NOTHING_FOUND_MESSAGE = "\tNothing in your list matches bestie ☹";
    public static final String IOEXCEPTION_ERROR_MESSAGE = "Not sure what an IOException is but... "
            + "I think something's wrong bestie!";
    public static final String FILE_LOADING_ERROR_MESSAGE = "Bestie I can't process this task type";
    public static final String WELCOME_MESSAGE = "\tHey BESTIE! I'm here to help you \u2728SLAYYYY\u2728 ALL UR TASKS\u2728!! "
            + "\uD83D\uDE0A\n"
            + "\t1. todo - for slays\u2728 that you have to slay\n"
            + "\t2. deadline - for slays\u2728 which have an upcoming deadline\n"
            + "\t3. event - for slays\u2728 with a date and time\n"
            + "\t4. mark - to mark a slay\u2728 as done\n"
            + "\t5. unmark - to mark a slay\u2728 as undone\n"
            + "\t6. delete - to delete a slay\u2728\n"
            + "\t7. list - to view all the slays\u2728 on your todo list\n"
            + "\t8. find - to find slays\u2728 in your list containing a certain keyword\n"
            + "\t9. bye - to leave me\uD83D\uDE2D\uD83D\uDE2D";
    public static final String GOODBYE_MESSAGE = "\tBye! COME BACK SOON TO \u2728SLAYYYY\u2728!";
    public static final String COMPLETED_ALL_TASKS_GOODBYE_MESSAGE = "I'm so happy that you've slayed\u2728\u2728 all "
            + "your slays\u2728! \n\tCome back soon "
            + "if you wanna keep slayinggg\u2728\u2728! \uD83D\uDE0A";
    public static final String TASK_ADDED_MESSAGE = "\tSLAY BESTIE! Your slay\u2728 is in the list! \n";
    public static final String TASK_DELETED_MESSAGE = "\tSLAY BESTIE! I've deleted your slay\u2728! \n";
    public static final String TASK_MARKED_DONE_MESSAGE = "\tSLAYYY\u2728\u2728 BESTIE!! Keep SLAYING\uD83D\uDE0A\n";
    public static final String TASK_MARKED_UNDONE_MESSAGE = "\tIt's sad that you thought you finished "
            + "your work but didnt.\n"
            + "\tITS OK BESTIE KEEP SLAYING\u2728\u2728\u2728\n";
    public static final String NO_TASKS_LEFT_MESSAGE = "\tSLAYYYY\u2728\u2728! There are no items in your list!";
    public static final String TASKS_LEFT_MESSAGE = "\tHere are your slays\u2728\u2728:\n";
    public static final String REMINDER_UPCOMING_TASKS_MESSAGE = "\tWatch out! These slays\u2728 are "
            + "due super soon bestie!\n";
    public static final String REMINDER_NO_UPCOMING_TASKS_MESSAGE = "\tSLAYYY BESTIE\u2728\u2728 NOTHING DUE SOON!!!\n";
    public static final String INVALID_ARGUMENT_ERROR_MESSAGE = "\tBestie..... Cld u enter the index instead?? "
            + "THanks lmao..";
}
