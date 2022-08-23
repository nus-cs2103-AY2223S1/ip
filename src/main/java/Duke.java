import data.FileStorage;
import data.IStorage;
import entities.Tasklist;
import exceptions.DukeException;
import handlers.HandlerFactory;
import handlers.IHandler;
import service.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Utils.customPrint;

public class Duke {
    private static final Pattern COMMAND_REGEX = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: (.*))?$");
    private static final String LOGO = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n" +
            "\n";
    private final static Tasklist list = new Tasklist();

    public static void main(String[] args) {
        customPrint(LOGO + "Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                customPrint(" ☹ OOPS!!! " + "Could not create /data directory");
            }
        }
        IStorage storage = new FileStorage("./data/duke.txt");
        Service service = new Service(storage);
        while (sc.hasNextLine()) {
            if ((userInput = sc.nextLine()).equals("bye")) {
                break;
            }
            try {
                handleCommand(service, userInput);
            } catch (DukeException ex) {
                customPrint(" ☹ OOPS!!! " + ex.getMessage());
            }

        }
        customPrint("Bye. Hope to see you again soon!");
    }



    private static void handleCommand(Service s, String str) throws DukeException {
        try {
            Matcher m = COMMAND_REGEX.matcher(str);
            m.find();
            String command = m.group(1);
            String value = m.group(2);
            String flag = m.group(3);
            String options = m.group(4);

            HandlerFactory handlerFactory = new HandlerFactory(command);
            IHandler handler = handlerFactory.taskName(value).flag(flag).flagOption(options).build();
            handler.handle(s);
            s.updateStorage();
        } catch (IllegalStateException ex) {
            // catch when no match found
            throw new DukeException("Unknown command. Please try again!");
        }
    }


}
