package duke.helper;

/**
 * Encapsulates a container all the help messages
 */
public class HelpList {
    /**
     * Returns todo help message
     */
    private static String todoHelp() {
        return "todo - Add a todo task - todo {description}";
    }

    /**
     * Returns deadline help message
     */
    private static String deadlineHelp() {
        return "deadline - Add a deadline task - deadline {description} /by {date & time}";
    }

    /**
     * Returns event help message
     */
    private static String eventHelp() {
        return "event - Add a event task - event {description} /at {date & time}";
    }

    /**
     * Returns mark help message
     */
    private static String markHelp() {
        return "mark - mark a task as done - mark {task no.}";
    }

    /**
     * Returns unmark help message
     */
    private static String unmarkHelp() {
        return "unmark - unmark a task as done - unmark {task no.}";
    }

    /**
     * Returns delete help message
     */
    private static String deleteHelp() {
        return "delete - delete a task - delete {task no.}";
    }

    /**
     * Returns list help message
     */
    private static String listHelp() {
        return "list - list all the tasks - list";
    }

    /**
     * Returns clear help message
     */
    private static String clearHelp() {
        return "clear - clear all the tasks - clear";
    }

    /**
     * Returns find help message
     */
    private static String findHelp() {
        return "find - find tasks with a keyword - find {keyword}";
    }

    /**
     * Returns bye help message
     */
    private static String byeHelp() {
        return "bye - ends Phil - bye";
    }

    /**
     * Returns all the help messages
     */
    public static String help() {
        String message = "";
        message += todoHelp() + "\n";
        message += deadlineHelp() + "\n";
        message += eventHelp() + "\n";
        message += markHelp() + "\n";
        message += unmarkHelp() + "\n";
        message += deleteHelp() + "\n";
        message += findHelp() + "\n";
        message += listHelp() + "\n";
        message += clearHelp() + "\n";
        message += byeHelp();

        return message;
    }
}
