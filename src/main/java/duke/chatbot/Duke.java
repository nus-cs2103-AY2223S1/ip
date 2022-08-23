package duke.chatbot;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.command.InvalidInputCommand;
import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.parser.Parser;
import duke.chatbot.storage.Storage;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    private static final String MESSAGE_SEPARATOR = "\t____________________________________________________________";

    private Storage storage;
    private TaskList taskList;

    private void displayMessage(CommandResult result) {
        System.out.println(MESSAGE_SEPARATOR);
        for (String line : result.getMessage()) {
            System.out.println("\t" + line);
        }
        System.out.println(MESSAGE_SEPARATOR);
    }

    private void handleGreet() {
        System.out.println(MESSAGE_SEPARATOR);
        System.out.println(MessageConstants.MESSAGE_WELCOME);
        System.out.println(MESSAGE_SEPARATOR);
    }

    private void handleSave() {
        storage.save(taskList);
    }

    public void applicationLoop() {
        Scanner input = new Scanner(System.in);

        Command command = null;
        while (!Command.isExit(command)) {
            try {
                command = Parser.parseCommand(input.nextLine());
                command.initData(taskList);
                CommandResult result = command.execute();
                displayMessage(result);
            } catch (InvalidInputException e) {
                displayMessage(new InvalidInputCommand().execute());
            }
        }

        handleSave();
    }

    public void exit() {
        System.exit(0);
    }

    public void run() {
        try {
            storage = Storage.of("duke.txt");
            taskList = storage.getTaskList();
        } catch (InvalidInputException | FileNotFoundException e) {
            // Print init error message
            e.printStackTrace();
            exit();
        }

        handleGreet();
        applicationLoop();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
