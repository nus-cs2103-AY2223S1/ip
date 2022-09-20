package bestie.utils;

/**
 * Class which stores all the constants which act as responses for Bestie.
 */
public class LanguageBank {
    public static final String MATCHING_ITEMS_MESSAGE = "\tSLAYYYY these slays match:  \n";
    public static final String INVALID_INPUT_ERROR_MESSAGE = "\tSO SORRY BESTIE! I'm just a chatbot...\n"
            + "\tPlease just use these commands \n"
            + "\t1. todo - for slays that you have to slay\n"
            + "\t2. deadline - for slays which have an upcoming deadline\n"
            + "\t3. event - for slays with a date and time\n"
            + "\t4. mark - to mark a slay as done\n"
            + "\t5. unmark - to mark a slay as undone\n"
            + "\t6. delete - to delete a slay\n"
            + "\t7. list - to view all the slays on your todo list\n"
            + "\t8. find - to find slays in your list containing a certain keyword\n"
            + "\t9. bye - to leave me";
    public static final String MARK_DONE_EMPTY_LIST_ERROR_MESSAGE = "\tUr list is empty bestie! "
            + "add some slays before telling me you've slayed!";
    public static final String MARK_DONE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay "
            + "you've slayed bestie!";
    public static final String MARK_UNDONE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay "
            + "you wanna unslay bestie...";
    public static final String MARK_UNDONE_EMPTY_LIST_ERROR_MESSAGE = "\tUr list is empty bestie! "
            + "add some slays before telling me you've unslayed!";
    public static final String DELETE_INVALID_INDEX_ERROR_MESSAGE = "\tTell me which slay you wanna "
            + "slay [literal] bestie!";
    public static final String DELETE_EMPTY_LIST_ERROR_MESSAGE = "\tBestie! You can't slay "
            + "[literal] that task tho....";
    public static final String DEADLINE_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u "
            + "stick to this format??\n"
            + "\t=> deadline <description> by <date> <time>";
    public static final String DATE_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick "
            + "to this format??\n"
            + "\tYYYY-MM-DD HHHH";
    public static final String EVENT_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick "
            + "to this format??\n"
            + "\t=> event <description> at <date> <time>";
    public static final String TODO_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie.... Could u stick to "
            + "this format??\n"
            + "\t=> todo <todo-name>";
    public static final String DATE_BEFORE_PRESENT_ERROR_MESSAGE = "\tBESTIE!!! "
            + "I didn't know you could go back in time omg... "
            + "\tBut seriously.. Could you put the date n time as AFTER the current date n time??"
            + "";
    public static final String FIND_INVALID_FORMAT_ERROR_MESSAGE = "\tBestie! Enter a keyword please!";
    public static final String FIND_EMPTY_LIST_MESSAGE = "\tBestie! Your list is empty omgg!!";
    public static final String FIND_NOTHING_FOUND_MESSAGE = "\tNothing in your list matches bestie ☹";
    public static final String IOEXCEPTION_ERROR_MESSAGE = "Not sure what an IOException is but... "
            + "I think something's wrong bestie!";
    public static final String FILE_LOADING_ERROR_MESSAGE = "Bestie I can't process this task type";
    public static final String WELCOME_MESSAGE = "\tHey BESTIE! I'm here to help you SLAYYYY ALL UR TASKS!!\n"
            + "\t1. todo - for slays that you have to slay\n"
            + "\t2. deadline - for slays which have an upcoming deadline\n"
            + "\t3. event - for slays with a date and time\n"
            + "\t4. mark - to mark a slay as done\n"
            + "\t5. unmark - to mark a slay as undone\n"
            + "\t6. delete - to delete a slay\n"
            + "\t7. list - to view all the slays on your todo list\n"
            + "\t8. find - to find slays in your list containing a certain keyword\n"
            + "\t9. bye - to leave me";
    public static final String GOODBYE_MESSAGE = "\tBye! COME BACK SOON TO SLAYYYY!";
    public static final String COMPLETED_ALL_TASKS_GOODBYE_MESSAGE = "I'm so happy that you've slayed all "
            + "your slays! \n\tCome back soon "
            + "if you wanna keep slayinggg!";
    public static final String TASK_ADDED_MESSAGE = "\tSLAY BESTIE! Your slay is in the list! \n";
    public static final String TASK_DELETED_MESSAGE = "\tSLAY BESTIE! I've deleted your slay! \n";
    public static final String TASK_MARKED_DONE_MESSAGE = "\tSLAYYY BESTIE!! Keep SLAYING\n";
    public static final String TASK_MARKED_UNDONE_MESSAGE = "\tIt's sad that you thought you finished "
            + "your work but didnt.\n"
            + "\tITS OK BESTIE KEEP SLAYING\n";
    public static final String NO_TASKS_LEFT_MESSAGE = "\tSLAYYYY! There are no items in your list!";
    public static final String TASKS_LEFT_MESSAGE = "\tHere are your slays:\n";
    public static final String REMINDER_UPCOMING_TASKS_MESSAGE = "\tWatch out! These slays are "
            + "due super soon bestie!\n";
    public static final String REMINDER_NO_UPCOMING_TASKS_MESSAGE = "\tSLAYYY BESTIE NOTHING DUE SOON!!!\n";
    public static final String INVALID_ARGUMENT_ERROR_MESSAGE = "\tBestie..... Cld u enter the index instead?? "
            + "THanks lmao..";
}
