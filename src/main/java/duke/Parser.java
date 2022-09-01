package duke;


import java.io.IOException;

/**
 * Parse user input.
 */
public class Parser {

    protected static String[] parseTypingInput(String str) {
        String allLowerCase = str.toLowerCase();
        return allLowerCase.strip().split(" ", 2);
    }

    /**
     * Parses user input.
     */
    public static String parseInput(String string) {
        //boolean running = true;

        String s = "";
        try {
            new TaskList(Storage.loadTask());
            //parse
            String[] wordArray = parseTypingInput(string);
            String str2 = "";

            if (wordArray.length >= 2) {
                str2 = wordArray[1];
            }
            String firstWord = wordArray[0];

            switch (firstWord) {

            case "bye":

                return UI.end();

            case "list":
                s = UI.printList();
                break;

            case "mark":
                s = TaskList.markAsDone(str2);

                break;

            case "unmark":
                s = TaskList.markAsUndone(str2);
                break;

            case "delete":
                s = TaskList.deleteTaskFromArray(str2);
                break;

            case "deadline":
                s = TaskList.addTaskToArray(str2, Task.TYPE.DEADLINE);
                break;

            case "todo":
                s = TaskList.addTaskToArray(str2, Task.TYPE.TODO);
                break;

            case "event":
                s = TaskList.addTaskToArray(str2, Task.TYPE.EVENT);
                break;

            case "find":
                s = TaskList.findTasks(str2);
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
            Storage.save(TaskList.getTaskList());
            return s;
        } catch (DukeException e) {
            return e.getMessage();

        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
