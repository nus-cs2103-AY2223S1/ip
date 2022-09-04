package dukepro;

import duke.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DukePro {

    //Duke stuff
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Background userBackground;
    private Background botBackground;

    public Background getUserBackground() {
        return userBackground;
    }
    public Background getBotBackground() {
        return botBackground;
    }
    public DukePro(String filePath) {
        userBackground = new Background(new BackgroundFill(Color.web("#b6f542"), new CornerRadii(30.0), null));
        botBackground = new Background(new BackgroundFill(Color.web("#f5cb42"), new CornerRadii(30.0), null));
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks, storage);
    }

    String getResponse(String input) {
        try {
            System.out.println(input);
            String response = parser.read(input).execute();
            return "Duke says: " + response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    String youSaid(String input) {
        return "You: " + input;
    }


}

