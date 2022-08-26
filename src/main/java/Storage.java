import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private Scanner scanner;

    public Storage(Ui ui, String outputFileFolder, String outputFileName) {
        // create file and directory if does not exist
        if (!Files.exists(Paths.get(outputFileFolder))) {
            try {
                Files.createDirectories(Paths.get(outputFileName));
            } catch (IOException e) {
                ui.printWithDivider("There was a problem creating the output file directory.");
                System.exit(1);
            }

            try {
                Files.createFile(Paths.get(outputFileFolder + File.separator + outputFileName));
            } catch (IOException e) {
                ui.printWithDivider("There was a problem creating the output file.");
                System.exit(1);
            }
        }

        // initialise the scanner for the file
        File file = new File(outputFileFolder + File.separator + outputFileName);
        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            ui.printWithDivider("There was a problem creating the output file.");
            System.exit(1);
        }
    }

    // initialises the tasklist with commands from output file
    public void load(TaskList taskList, Ui ui) {
        if (!this.scanner.hasNext()) {
            ui.printWithDivider("No previous tasks found, starting with empty task list!");
            return;
        } else {
            ui.printWithDivider("Reading previous tasks...");
            ui.printDivider();
            while (this.scanner.hasNext()) {
                String commandString = this.scanner.nextLine();
                String[] commandArr = commandString.split(" ");
                Command.Commands commandWord = null;
                try {
                    commandWord = Command.Commands.valueOf(commandArr[0]);
                } catch (IllegalArgumentException e) {
                    ui.println(String.format("Error reading command in output file: %s", commandString));
                    break;  // move to next command if error reading this line
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
                    command.execute();
                }
            }
            ui.printDivider();
        }
    }

}
