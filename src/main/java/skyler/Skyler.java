package skyler;

import java.io.IOException;
import java.util.Scanner;

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
     * Activates the personal chat assistant
     */
    public void run() {
        ui.greet();

        int active = 1;

        try (Scanner sc = new Scanner(System.in)) {
            while (active == 1 && sc.hasNext()) {
                String description = sc.nextLine();
                active = parser.parse(description);
            }
        } catch (EmptyDescriptionException e) {
            ui.showEmptyDescriptionError();
        } catch (TaskNotRecognisedException e) {
            ui.showTaskNotRecognisedError();
        } finally {
            ui.bye();
        }
    }

    public static void main(String[] args) {
        new Skyler("data/skyler.txt").run();
    }
}
