import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;
import exceptions.DukeException;
import handlers.HandlerFactory;
import handlers.IHandler;
import utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Utils.customPrint;

public class Duke {
    private static final Pattern COMMAND_REGEX = Pattern.compile("^([a-zA-Z]+)(?: ([^/]*))?(?: /([a-zA-Z]+))?(?: ([^/]*))?$");
    private static final String LOGO = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n" +
            "\n";
    private final static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        customPrint(LOGO + "Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (sc.hasNextLine()) {
            if ((userInput = sc.nextLine()).equals("bye")) {
                break;
            }
            try {
                handleCommand(userInput);
            } catch (DukeException ex) {
                customPrint(" ☹ OOPS!!! " + ex.getMessage());
            }

        }
        customPrint("Bye. Hope to see you again soon!");
    }



    private static void handleCommand(String s) throws DukeException {
        try {
            Matcher m = COMMAND_REGEX.matcher(s);
            m.find();
            String command = m.group(1);
            String value = m.group(2);
            String flag = m.group(3);
            String options = m.group(4);

            HandlerFactory handlerFactory = new HandlerFactory(command);
            IHandler handler = handlerFactory.taskName(value).flag(flag).flagOption(options).build();
            handler.handle(list);
        } catch (IllegalStateException ex) {
            // catch when no match found
            throw new DukeException("Unknown command. Please try again!");
        }
    }


}
