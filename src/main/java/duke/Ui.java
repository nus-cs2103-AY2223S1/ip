package duke;
import java.time.LocalDate;

public class Ui {
    String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    String toPrint = "Hello from\n" + logo + "\n Hello! I'm Duke\n What can I do for you?";

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
            case "list":
                return myTasks.listTasks();
            case "mark":
                toPrint = myTasks.markTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            case "unmark":
                toPrint = myTasks.unmarkTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            case "todo":
                toPrint = myTasks.addTask(new Todo(userInput.substring(5)));
                myTasks.saveTasks();
                return toPrint;
            case "deadline":
                toPrint = myTasks.addTask(new Deadline(
                    userInput.substring(9).split("/by")[0], LocalDate.parse(
                    userInput.split("/by ")[1])));
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
            case "delete":
                toPrint = myTasks.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
                myTasks.saveTasks();
                return toPrint;
            default:
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
        return "";
    }
    
    /**
     * Start message of Duke
     */
    public String startMessage() {
        return toPrint;
    }
}