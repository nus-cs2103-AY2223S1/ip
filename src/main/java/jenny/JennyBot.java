package jenny;

import jenny.commands.Command;
import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.storage.TaskStorage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Parser;
import jenny.util.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Initialise the JennyBot application.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class JennyBot {
    public boolean isExit = false;
    private final Storage<ArrayList<Task>> storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for a JennyBot application.
     * Will initialise a storage at the default location under the specified name.
     */
    public JennyBot() {
        String fileName = "tasks.txt";
        ui = new Ui(System.in, System.out);
        storage = new TaskStorage<>(fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (JennyException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Run the JennyBot Application
     */
    public void run() {
        ui.greet();
        isExit = false;
        while (!isExit) {
            try {
                String nextLine = ui.read();
                Command cmd = Parser.parse(nextLine);
                cmd.run(tasks, ui, storage); // throws JennyException
                isExit = cmd.isExit();
            } catch (JennyException e) {
                ui.print(e.getMessage());
            }
        }
        assert isExit;
    }

    /**
     * Send a greeting message to the user.
     *
     * @return a greeting message.
     */
    public String greet() {
        ByteArrayOutputStream res = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(res, true, StandardCharsets.UTF_8);
        ui.setPrintStream(out);
        ui.greet();
        String response = res.toString();
        out.close();
        return response;
    }

    /**
     * Run the user input as a command and returns the response from the application.
     *
     * @param input the command to be run.
     * @return the response from the application after running the command.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream res = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(res, true, StandardCharsets.UTF_8);
        ui.setPrintStream(out);

        try {
            Command cmd = Parser.parse(input);
            cmd.run(tasks, ui, storage); // throws JennyException
            isExit = cmd.isExit();
        } catch (JennyException e) {
            ui.print(e.getMessage());
        }

        String response = res.toString();
        out.close();
        return response;
    }
}
