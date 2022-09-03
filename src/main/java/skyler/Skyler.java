package skyler;

import java.io.IOException;

/**
 * Represents a personal chat assistant
 */
public class Skyler {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a personal chat assistant object
     *
     * @param filePath Filepath for storage of task data, relative to project folder.
     */
    public Skyler(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage, storage.load());
        } catch (SkylerException e) {
            taskList = new TaskList(storage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.parser = new Parser(taskList);
    }

    /**
     * Returns Skyler's response to user's input commands
     *
     * @param input User input.
     * @return Skyler's response.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (EmptyDescriptionException e) {
            return ui.showEmptyDescriptionError();
        } catch (TaskNotRecognisedException e) {
            return ui.showTaskNotRecognisedError();
        }
    }

}
