package duke;
import java.time.LocalDate;

public class Ui {
    String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    String welcomeMessage = "Hello from\n" + logo + "\n Hello! I'm Duke\n What can I do for you?";

    Ui() {}

    /**
     * Handling User Input
     */
    String getUserCommand(TaskList myTasks, String userInput) {
        String toPrint = "";
        while (userInput != "") {
            switch(userInput.split(" ")[0]) {
            case "bye":
                return "Bye. Hope to see you again soon!";
            case "deadline":
                toPrint = myTasks.addTask(new Deadline(
                    userInput.substring(9).split("/by")[0], LocalDate.parse(
                    userInput.split("/by ")[1])));
                myTasks.saveTasks();
                return toPrint;
            case "delete":
                toPrint = myTasks.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            case "event":
                toPrint = myTasks.addTask(new Event(
                    userInput.substring(6).split("/at")[0], LocalDate.parse(
                    userInput.split("/at ")[1])));
                myTasks.saveTasks();
                return toPrint;
            case "find":
                toPrint = myTasks.findTasks(userInput.substring(5));
                return toPrint;
            case "help":
                return getHelpMessage();
            case "list":
                return myTasks.listTasks();
            case "mark":
                toPrint = myTasks.markTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            case "todo":
                toPrint = myTasks.addTask(new Todo(userInput.substring(5)));
                myTasks.saveTasks();
                return toPrint;
            case "unmark":
                toPrint = myTasks.unmarkTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            default:
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        return toPrint;
    }
    
    /**
     * Start message of Duke
     */
    public String startMessage() {
        return welcomeMessage;
    }

    public String getHelpMessage() {
        String helpMessage = "Here are the following commands you can use!\n";
        helpMessage += "list: List all tasks\n";
        helpMessage += "todo <taskName>: Add a new task\n";
        helpMessage += "event <eventName> /at <date>: Add a new event\n";
        helpMessage += "deadline <eventName> /by <date>: Add a new deadline\n";
        helpMessage += "find <itemName>: Look for an item\n";
        helpMessage += "delete <itemNumber>: Delete an item from list\n";
        helpMessage += "mark <itemNumber>: Mark an item as done\n";
        helpMessage += "unmark <itemNumber>: Mark an item as undone\n";
        helpMessage += "bye: Exit the application\n";

        return helpMessage;
    }
}