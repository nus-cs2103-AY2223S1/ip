package duke.services;

import javafx.application.Platform;

import java.io.IOException;
import java.util.Arrays;

/**
 * Personal Assistant that helps you keep track of your tasks
 */
public class Duke {

    public static String response;

    /**
     * Receives user's inputs and responds to them until "bye" is entered
     */
    public static String getResponse(String input) {
        response = "";
        String[] words = Arrays.stream(input.strip().split(" ")).toArray(String[]::new);
        if (words.length > 0) {
            try {
                if (words.length == 1 && words[0].equals("bye")) {
                    stop();
                } else if (words.length == 1 && words[0].equals("list")) {
                    TaskList.listTasks(); //could put words.length == 1 cases all here
                } else if (words.length == 1 && words[0].equals("SAVE")) {
                    Storage.wipeDataOnExit(false);
                } else if (words.length == 1 && words[0].equals("WIPE")) {
                    Storage.wipeDataOnExit(true);
                } else if (words[0].equals("todo")) {
                    TaskList.addTodo(words);
                } else if (words[0].equals("deadline")) {
                    TaskList.addDeadline(words);
                } else if (words[0].equals("event")) {
                    TaskList.addEvent(words);
                } else if (words[0].equals("mark")) {
                    TaskList.markTaskAsDone(words);
                } else if (words[0].equals("unmark")) {
                    TaskList.markTaskAsNotDone(words);
                } else if (words[0].equals("delete")) {
                    TaskList.deleteTask(words);
                } else if (words[0].equals("find")) {
                    TaskList.findTasksContainingKeyword(words);
                } else {
                    Ui.sayLines(new String[]{"I'm sorry, I don't know what that means"});
                }
            } catch (IllegalArgumentException | IOException e) {
                Ui.sayLines(new String[]{e.getMessage()});
            }
        }
        assert !response.isEmpty(): "Response can't be empty";
        return response;
    }

    public static void start() throws IOException {
        Storage.loadData();
        Ui.introduceSelf();
    }

    public static void stop() throws IOException {
        Storage.saveData();
        Ui.sayGoodbye();
        Platform.exit();
        System.exit(0);
    }
}
