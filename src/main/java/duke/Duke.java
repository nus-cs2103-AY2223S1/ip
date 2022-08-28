package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import duke.command.Command;

/**
 * Duke is a personal assistant Chat-bot that aims to help users to keep track of various things.
 */
public class Duke {
    private static final String DEFAULT_FILE_NAME = "default.txt";
    private StorageList storageList;
    private Ui ui;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    /**
     * Default constructor for Duke.
     */
    public Duke() {
        this(DEFAULT_FILE_NAME);
    }
    /**
     * Constructor for the Duke class.
     *
     * @param filePath Path of the file to be used for the storage list
     */
    public Duke(String filePath) {
        storageList = new StorageList();
        ui = new Ui(storageList);
        try {
            FileIO.load(storageList, filePath);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Runs the program.
     */
    public void run() {
        getGreetings();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandStr = ui.readCommand();
                Command command = Parser.parse(commandStr);
                command.execute(ui, storageList);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Returns the Default file name.
     *
     * @return the DEFAULT_FILE_NAME
     */
    public static String getDefaultFileName() {
        return DEFAULT_FILE_NAME;
    }

    /**
     * Returns the output stream.
     * @param input the input stream
     * @return the output stream
     */
    public String getResponse(String input) {
        outputStream.reset();
        try {
            String commandStr = ui.readCommand(input);
            Command command = Parser.parse(commandStr);
            command.execute(ui, storageList);
            if (command.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    /**
     * Set the output stream for the Duke program.
     */
    public void setOut() {
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
    }

    /**
     * Gets the greeting message.
     */
    public String getGreetings() {
        outputStream.reset();
        Output.GREETINGS.print();
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    /**
     * Gets the help message.
     */
    public String getHelp() {
        outputStream.reset();
        Output.HELP.print();
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}
