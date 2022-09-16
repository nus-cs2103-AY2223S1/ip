package bloop;

import java.io.IOException;

import bloop.gui.Main;
import javafx.application.Application;

/**
 * Chatbot to keep track of tasks.
 */
public class Bloop {

    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList(storage);
    private static Parser parser = new Parser(tasks);

    private static void chatStart() {
        storage.makeFile(tasks.getList());
    }

    public String getResponse(String input) {
        try {
            return Parser.parse(input);
        } catch (BloopException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        chatStart();
        Application.launch(Main.class, args);
    }
}
