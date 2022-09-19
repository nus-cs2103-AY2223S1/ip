package jarvis;

import jarvis.task.TaskList;
import jarvis.exception.JarvisException;
import javafx.application.Application;

import java.io.IOException;


public class Jarvis{
    static Parser parser;
    public static void main(String[] args){

        String filePath = "data/taskList.txt";

        TaskList tasks;
        Storage storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);

        Application.launch(Main.class, args);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws JarvisException {
        return parser.readCommand(input);
    }

}
