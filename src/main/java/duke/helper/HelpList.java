package duke.helper;

/**
 * Class containing all the help messages
 */
public class HelpList {
    /**
     * Method to return todo help message
     */
    private static String todoHelp() {
        return "todo - Add a todo task - todo {description}";
    }

    /**
     * Method to return deadline help message
     */
    private static String deadlineHelp() {
        return "deadline - Add a deadline task - deadline {description} /by {date & time}";
    }

    /**
     * Method to return event help message
     */
    private static String eventHelp() {
        return "event - Add a event task - event {description} /at {date & time}";
    }

    /**
     * Method to return mark help message
     */
    private static String markHelp() {
        return "mark - mark a task as done - mark {task no.}";
    }

    /**
     * Method to return unmark help message
     */
    private static String unmarkHelp() {
        return "unmark - unmark a task as done - unmark {task no.}";
    }

    /**
     * Method to return delete help message
     */
    private static String deleteHelp() {
        return "delete - delete a task - delete {task no.}";
    }

    /**
     * Method to return list help message
     */
    private static String listHelp() {
        return "list - list all the tasks - list";
    }

    /**
     * Method to return clear help message
     */
    private static String clearHelp() {
        return "clear - clear all the tasks - clear";
    }

    /**
     * Method to return find help message
     */
    private static String findHelp() {
        return "find - find tasks with a keyword - find {keyword}";
    }

    /**
     * Method to return bye help message
     */
    private static String byeHelp() {
        return "bye - ends Phil - bye";
    }

    /**
     * Method to return all the help messages
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
