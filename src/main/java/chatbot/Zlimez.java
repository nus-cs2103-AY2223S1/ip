package chatbot;

import java.io.IOException;

import chatbot.commands.Command;
import chatbot.exceptions.DukeException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Response;

/**
 * The Duke chatbot named Zlimez functions as a task manager
 * that helps users keep track of future tasks, their date
 * and completion status.
 *
 * @author James Chiu
 */
public class Zlimez {
    private boolean isActive;
    private Response resp;
    private Storage storage;
    private TaskList taskList;
    private Command currentCommand;

    /**
     * The constructor initializes the duke chatbot instance
     * equipped with Storage and Task list components.
     */
    public Zlimez() {
        this.resp = new Response();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.isActive = true;
    }

    /**
     * Execute the command as per the user input and returns the corresponding
     * response that should be displayed on the GUI.
     *
     * @param input user input.
     * @return the response after executing a command.
     */
    public String getResponse(String input) {
        try {
            currentCommand = Parser.parse(input);
            if (currentCommand.isExit()) {
                this.exit();
            }

            return currentCommand.execute(taskList, resp);
        } catch (DukeException e) {
            return resp.reprimand(e);
        }
    }


    /**
     * The method is called immediately after the chatbot instance is created
     * to kickstart the interaction with the user.
     * If a task list is generated during a previous session, its data will be
     * read by the current chatbot.
     *
     * @throws IOException
     */
    public String start() {
        StringBuffer init = new StringBuffer(resp.greet());
        try {
            storage.initData(taskList);
        } catch (DukeException e) {
            init.append("\n");
            init.append(resp.reprimand(e));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return init.toString();
    }

    /**
     * Ends the current chatbot session and demands the data in the current
     * task list to be saved.
     *
     * @throws IOException
     */
    public void exit() {
        try {
            storage.saveData(taskList);
            this.isActive = false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkStatus() {
        return this.isActive;
    }
}
