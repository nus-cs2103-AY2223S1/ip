package skyler;

import java.io.IOException;
import java.util.Scanner;

public class Skyler {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

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