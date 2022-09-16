import java.io.File;
import java.util.Scanner;

import duke.Duke;
import duke.data.FileStorage;
import duke.data.IStorage;
import duke.gui.Gui;
import duke.service.Service;
import duke.service.Ui;
import javafx.application.Application;

/**
 * Main entrypoint for Duke Application
 */
public class Main {

    /** Launches the Duke Application. */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
        Ui ui = new Ui();
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                ui.receiveMessage("Could not create /data directory");
            }
        }
        IStorage storage = new FileStorage("./data/duke.txt");
        Service service = new Service(storage, ui);
        Duke duke = new Duke(service);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String message = duke.handleCommand(userInput);
            ui.receiveMessage(message);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

}
