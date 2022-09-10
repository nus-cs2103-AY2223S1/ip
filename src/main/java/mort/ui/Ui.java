package mort.ui;

import mort.command.CommandWord;
import mort.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that deals with user interactions.
 */
public class Ui {
    public Ui() {
    }
    
    public String getWelcomeMessage() {
        return "Oh, it's you again...\nMort, begrudgingly at your service.\n"
                + "Hmph, what do you want now?";
    }
    
    public String getExitMessage() {
        return "With all due disrespect, leave me alone next time.";
    }
    
    public String getError(String errorMessage) {
        return errorMessage;
    }
    
    public String getAddMessage(Task task, int size) {
        return "Seriously? Another one?\nGive me strength...\n"
                + "  " + task + "\n" + "You have " + size + " task"
                + (size > 1 ? "s" : "") + ". Bummer.";
    }
    
    public String getDeleteMessage(Task task, int size) {
        return "Good riddance, I say.\n  " + task
                + "\nYou have " + size + " task" + (size == 1 ? "" : "s") + ".";
    }
    
    public String getListMessage(String tasks) {
        if (tasks.isBlank()) {
            return "You don't have any tasks. Make yourself useful.\n";
        } else {
            return "*rolls eyes*\nFine. Here are your tasks:\n" + tasks;
        }
    }
    
    public String getMissingTaskError(CommandWord commandWord, int num) {
        StringBuilder sb = new StringBuilder();
        switch (commandWord) {
        case DELETE:
            sb.append("Impressive. You've figured out how to delete non-existent tasks.\n");
            break;
        case MARK:
            sb.append("Brilliant. You've asked me to mark an imaginary task as complete.\n");
            break;
        case UNMARK:
            sb.append("Brilliant. You've asked me to mark an imaginary task as incomplete.\n");
            break;
        }
        return sb.append("Task number ").append(num).append(" does not exist.").toString();
    }
    
    public static String getUnknownCommandMessage(String command) {
        return "'" + command + "' is not a valid command.\nIf you want my help, the least "
                + "you could do is say something I understand.";
    }
    
    public static String getCommandHelp(CommandWord keyword) {
        StringBuilder sb = new StringBuilder("Type \"");
        switch (keyword) {
        case TODO:
            sb.append("todo <description>\" to add a new todo.");
            break;
        case DEADLINE:
            sb.append("deadline <description> /by <date> <time>[optional]\" to add a new deadline.");
            break;
        case EVENT:
            sb.append("event <description> /at <date> <time>[optional]\" to add a new event.");
            break;
        case MARK:
            sb.append("mark <task number>\" to mark a task as complete.");
            break;
        case UNMARK:
            sb.append("unmark <task number>\" to mark a task as incomplete.");
            break;
        case DELETE:
            sb.append("delete <task number>\" to delete a task.");
            break;
        case FIND:
            sb.append("find <keyword>\" to search for a task.");
            break;
        case VIEW:
            sb.append("view <date>\" to view all tasks on that date.");
            break;
        }
        return sb.toString();
    }
    
    public String getFindMessage(String keyword, String result) {
        if (result.isBlank()) {
            return "No matches found for '" + keyword + "'. Did you have fun wasting my time?";
        } else {
            return "Really? Find them yourself next time.\nHere's what I found for '"
                    + keyword + "':\n" + result;
        }
    }
    
    public String getViewMessage(String tasks, LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (tasks.isBlank()) {
            return "You don't have any tasks on " + formattedDate + ". Make yourself useful.\n";
        } else {
            return "Do I have to?\nWhatever. Here are your tasks for " + formattedDate + ":\n" + tasks;
        }
    }
}
