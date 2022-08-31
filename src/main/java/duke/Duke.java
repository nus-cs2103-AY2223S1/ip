package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private static String taskDataPath = "data";
    private static String taskDataFileName = "duke.txt";

    private static Scanner sc = new Scanner(System.in);
    private static UI UI = new UI();
    private static Parser parser = new Parser();
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage(taskDataPath, taskDataFileName);

    Duke() {
        loadStorage();
    }


    private static String loadStorage() {
        try {
            storage.readTaskData(taskList);
            return "";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";

        switch (input) {
            case "list":
            case "list\n":
                response = taskList.printTaskList();
                break;
            case "bye":
            case "bye\n":
                response = UI.goodBye;
                storage.updateTaskData(taskList);
                break;
            default:
                try {
                    String[] commands = input.split(" ");

                    if (commands.length < 2) {
                        throw new DukeException("I do not understand that command. Please elaborate more!\n");
                    }

                    if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                        int index = Integer.parseInt(commands[1]) - 1;

                        if (commands[0].equals("mark")) {
                            response = taskList.markTaskAsDone(index);
                        } else if (commands[0].equals("unmark")) {
                            response = taskList.markTaskAsNotDone(index);
                        } else if (commands[0].equals("delete")) {
                            response = taskList.deleteTask(index);
                        } else {
                            throw new DukeException("I do not understand that command :(\n");
                        }
                    } else if (commands[0].equals("find")) {
                        String description = commands[1];
                        response = taskList.find(description);
                    } else {
                        Task newTask;
                        if (commands[0].equals("todo")) {
                            newTask = parser.generateToDoFromInput(input);
                        } else if (commands[0].equals("deadline")) {
                            newTask = parser.generateDeadlineFromInput(input);
                        } else if (commands[0].equals("event")) {
                            newTask = parser.generateEventFromInput(input);
                        } else {
                            throw new DukeException("I do not understand that command :(\n");
                        }
                        response = taskList.add(newTask);
                    }
                } catch (DukeException e) {
                    response = e.toString();
                }
        }


        return response;
    }
}
