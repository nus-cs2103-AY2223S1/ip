package duke.ui;

import duke.command.CommandWord;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String BORDER = ">>=========================="
            + "============[**]============="
            + "=========================<<";
    private static final String MORT = "                                 .---.        .-----------\n"
            + "                                /     \\  __  /    ------\n"
            + "                               / /     \\(  )/    -----\n"
            + "                              //////   ' \\/ `   ---\n"
            + "                             //// / // :    : ---\n"
            + "                            // /   /  /`    '--\n"
            + "                           //          //..\\\\\n"
            + ">>====================================UU[**]UU====================================<<\n"
            + "                                      '//||\\\\`\n"
            + "                                        ''``";
    private Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public String readCommand() {
        return this.sc.nextLine();
    }
    
    public void showWelcome() {
        System.out.println(MORT);
        System.out.println("  Oh, it's you again...\n  Mort, begrudgingly at your service.");
        System.out.println("  Hmph, what do you want now?");
    }
    
    public void exit() {
        this.sc.close();
        System.out.println("  With all due disrespect, leave me alone next time.");
    }
    
    public void printBorder() {
        System.out.println(BORDER);
    }
    
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
    public void showLoadingError() {
        System.out.println("  Error: Unable to load tasks. A new task list will be created.");
    }
    
    public void showAddMessage(Task task, int size) {
        System.out.println("  Seriously? Another one?\n  Give me strength...\n"
                + "    " + task + "\n" + "  You have " + size + " task"
                + (size > 1 ? "s" : "") + ". Bummer.");
    }
    
    public void showDeleteMessage(Task task, int size) {
        System.out.println("  Good riddance, I say.\n    " + task
                + "\n  You have " + size + " task" + (size == 1 ? "" : "s") + ".");
    }
    
    public void showListMessage(int size) {
        if (size == 0) {
            System.out.println("  You don't have any tasks. Make yourself useful.");
        } else {
            System.out.println("  *rolls eyes*\n"
                    + "  Do I have to?\n"
                    + "  Fine. Here are your tasks:");
        }
    }
    
    public String getMissingTaskError(CommandWord commandWord, int num) {
        StringBuilder sb = new StringBuilder();
        switch (commandWord) {
        case DELETE:
            sb.append("  Impressive. You've figured out how to delete non-existent tasks.\n");
            break;
        case MARK:
            sb.append("  Brilliant. You've asked me to mark an imaginary task as complete.\n");
            break;
        case UNMARK:
            sb.append("  Brilliant. You've asked me to mark an imaginary task as incomplete.\n");
            break;
        }
        return sb.append("  Task number ").append(num).append(" does not exist.").toString();
    }
    
    public static String getUnknownCommandMessage(String command) {
        return "  '" + command + "' is not a valid command.\n  If you want my help, the least "
                + "you could do is say something I understand.";
    }
    
    public static String getCommandHelp(CommandWord keyword) {
        StringBuilder sb = new StringBuilder("  Type \"");
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
        }
        return sb.toString();
    }
    
    public void showFindMessage(String keyword, String result) {
        if (result.isBlank()) {
            System.out.println("  No matches found for '" + keyword + "'. Did you have fun wasting my time?");
        } else {
            System.out.print("  Really? Find them yourself next time.\n  Here's what I found for '"
                    + keyword + "':\n" + result);
        }
    }
}
