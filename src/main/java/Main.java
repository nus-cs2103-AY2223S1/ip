import java.io.File;
import java.util.Scanner;

import zupey.Zupey;
import zupey.data.FileStorage;
import zupey.data.IStorage;
import zupey.gui.Gui;
import zupey.service.Service;
import zupey.service.Ui;
import javafx.application.Application;

/**
 * Main entrypoint for Zupey Application
 */
public class Main {

    /** Launches the Zupey Application. */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
        Ui ui = new Ui();
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                ui.receiveMessage("Could not create /data directory");
            }
        }
        IStorage storage = new FileStorage("./data/Zupey.txt");
        Service service = new Service(storage, ui);
        Zupey Zupey = new Zupey(service);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String message = Zupey.handleCommand(userInput);
            ui.receiveMessage(message);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

}
