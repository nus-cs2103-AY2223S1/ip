import command.Command;
import command.CommandResponse;
import command.CommandException;
import command.CommandFactory;
import command.CommandHandler;

import data.TaskList;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {

    // ChatBot constants
    public static final String NAME = "Duke";
    public static final Path CACHE_PATH = Paths.get(".duke.cache");
    // UI constants
    public static final String LINE_STR = "-".repeat(50);

    private static TaskList taskList;

    private static void respond(String response) {
        System.out.printf("\t%s\n", LINE_STR);
        System.out.printf("\t%s\n", response.replaceAll("\\n", "\n\t"));
        System.out.printf("\t%s\n", LINE_STR);
    }

    private static void respondError(String errorMsg) {
        respond(String.format("X %s", errorMsg));
    }

    private static boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(CACHE_PATH.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(taskList);
            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException saveError) {
            return false;
        }
        return true;
    }

    private static boolean load() {
        if (!Files.exists(CACHE_PATH)) {
            taskList = new TaskList();
            return true;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(CACHE_PATH.toString());
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

            taskList = (TaskList) objInputStream.readObject();
            objInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException loadError) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Greetings
        respond(String.format("Hi I'm %s\n%s", NAME, "What can I do for you?"));

        // Load
        if (!load()) {
            respondError(String.format("Failed to load from cache (%s)", CACHE_PATH));
        }

        // Chat
        CommandFactory commandFactory = new CommandFactory();
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String commandStr = input.nextLine();
            try {
                Command command = commandFactory.parseCommand(commandStr);
                if (command == Command.BYE) {
                    terminate = true;
                    respond("Bye. Hope to see you again soon!");
                    continue;
                }

                CommandHandler commandHandler = commandFactory.getCommandHandler(command,
                    commandStr);
                CommandResponse commandResponse = commandHandler.run(taskList);
                respond(commandResponse.responseStr);

                if (commandResponse.triggerSave && !save()) {
                    respondError(String.format("Failed to save to cache (%s)", CACHE_PATH));
                    terminate = true;
                }
            } catch (CommandException commandError) {
                respondError(commandError.getMessage());
            }
        }
    }
}
