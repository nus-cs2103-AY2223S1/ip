package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.*;
import duke.ui.Ui;

public class Duke {



    private StorageFile storageFile;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up required objects, loads the data from the storage file.
     * Prints welcome message.
     */
    public Duke() {
        ui = new Ui();
        storageFile = new StorageFile();
        tasks = new TaskList(storageFile.loadTasks());
    }

    /** Runs the program until termination */
    public void run() throws Exception {
        ui.showWelcomeMessage();
        runProgram();
        exit();
    }

    public void runProgram() {
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                final String userCommand = ui.getUserCommand();
                final Command command = new Parser().parseCommand(userCommand);
                command.execute(tasks, ui, storageFile);
                hasEnded = command.hasEnded();
            } catch (Exception e) {
                ui.showMessages(e.getMessage());
            }
        }
    }

    public void exit() {
        ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {

        new Duke().run();


//        try {
//            ArrayList<Task> toDoList = new ArrayList<>();
//            String path="./test.txt";
//            File file = new File(path);
//            if (!file.exists()) {
//                file.createNewFile();
//            } else {
//                toDoList = loadTasks();
//                printTasksInList(toDoList);
//            }
//
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//
//            bw.write(saveList(toDoList));
//            bw.close();
//        } catch (EmptyBodyException | InvalidInputException | IOException e) {
//            System.out.println(e.getMessage());
//        }

    }

}
