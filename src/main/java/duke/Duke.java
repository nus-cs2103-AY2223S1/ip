package duke;

import duke.data.FileStorage;
import duke.data.IStorage;
import duke.entities.Tasklist;
import duke.exceptions.DukeException;
import duke.handlers.IHandler;
import duke.service.Parser;
import duke.service.Service;
import duke.service.Ui;

import java.io.File;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n" +
            "\n";
    private final static Tasklist list = new Tasklist();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userInput;
        Ui ui = new Ui();
        ui.customPrint(LOGO + "Hello! I'm Duke\nWhat can I do for you?");
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                ui.customPrintError("Could not create /data directory");
            }
        }
        IStorage storage = new FileStorage("./data/duke.txt");
        Service service = new Service(storage, ui);
        while (sc.hasNextLine()) {
            if ((userInput = sc.nextLine()).equals("bye")) {
                break;
            }
            try {
                handleCommand(service, userInput);
            } catch (DukeException ex) {
                ui.customPrintError(ex);
            }

        }
        ui.customPrint("Bye. Hope to see you again soon!");
    }



    private static void handleCommand(Service s, String str) throws DukeException {
        IHandler handler = Parser.parse(str);
        handler.handle(s);
        s.updateStorage();
    }


}
