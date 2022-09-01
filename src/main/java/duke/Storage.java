package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.ListCommand;

/**
 * Interacts with output file that saves the task list.
 */
public class Storage {
    private File file;
    private Scanner scanner;

    /**
     * Constructor for Storage.
     * Creates the output file and its parent directories if not present yet.
     *
     * @param ui Ui to print messages for user if IOException occurs.
     * @param fileName name of output file.
     * @param directories output file path's directory names in order.
     */
    public Storage(Ui ui, String fileName, String... directories) {
        // platform independent paths
        String directoriesString = Arrays
                .stream(directories)
                .reduce("", (prev, curr) -> prev + curr + File.separator);
        String pathString = directoriesString + fileName;

        // create file and directory if does not exist
        if (!Files.exists(Paths.get(pathString))) {
            try {
                Files.createDirectories(Paths.get(directoriesString));
            } catch (IOException e) {
                ui.printWithDivider("There was a problem creating the output file directory.");
                System.exit(1);
            }

            try {
                Files.createFile(Paths.get(pathString));
            } catch (IOException e) {
                ui.printWithDivider("There was a problem creating the output file.");
                System.exit(1);
            }
        }

        // initialise the scanner for the file
        this.file = new File(pathString);
        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            ui.printWithDivider("There was a problem creating the output file.");
            System.exit(1);
        }
    }

    /**
     * Loads the TaskList by treating the text from output file
     * as input strings from user to be parsed, and its resulting commands executed.
     *
     * @param taskList TaskList with no tasks, to be initialised with previous tasks from output file.
     * @param ui Ui to print messages to user.
     * @throws IOException if writing change to output file is unsuccessful while executing a command.
     */
    public void load(TaskList taskList, Ui ui) throws IOException {
        if (!this.scanner.hasNext()) {
            ui.printWithDivider("No previous tasks found, starting with empty task list!");
            return;
        } else {
            ui.printDivider();
            ui.println("Reading previous tasks...");
            while (this.scanner.hasNext()) {
                String commandString = this.scanner.nextLine();
                String[] commandArr = commandString.split(" ");
                Command.Commands commandWord = null;
                try {
                    commandWord = Command.Commands.valueOf(commandArr[0]);
                } catch (IllegalArgumentException e) {
                    ui.println(String.format("Error reading command in output file: %s", commandString));
                    break; // move to next command if error reading this line
                }

                Command command = null;
                switch(commandWord) {
                case todo:
                    command = AddTodoCommand.of(commandString);
                    break;
                case deadline:
                    command = AddDeadlineCommand.of(commandString);
                    break;
                case event:
                    command = AddEventCommand.of(commandString);
                    break;
                default:
                    ui.println(String.format(
                            "Invalid command in output file (should only contain add command): %s",
                            commandString));
                    break;
                }

                if (!Objects.isNull(command)) {
                    command.execute(taskList, this);
                }
            }
            ui.println("Successfully read previous tasks.");
            ui.printDivider();
            ui.println("");
            new ListCommand("list").execute(taskList, this, ui);
        }
    }

    /**
     * Writes the given string to the output file.
     *
     * @param s string to be written.
     * @throws IOException if an error occurs while writing to the output file.
     */
    public void write(String s) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(s);
        fw.close();
    }

}
