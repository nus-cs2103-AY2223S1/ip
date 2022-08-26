package blob;

import blob.commands.Command;
import blob.commands.CommandResult;
import blob.commands.TaskCommand;
import blob.exception.BlobException;
import blob.exception.InvalidDateFormatException;
import blob.tasks.TaskList;
import blob.tasks.Task;
import blob.tasks.Event;
import blob.tasks.ToDo;
import blob.tasks.Deadline;
import blob.parser.Parser;
import blob.ui.TextUi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Blob {
    // File path for text file which stores the data of all tasks
    private static final Path SAVED_TASKS_FILE_PATH = Paths.get("data","tasks.txt");

    private final TaskList taskList;
    private final TextUi ui;
    private final Parser parser;

    Blob() {
        this.ui = new TextUi();
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    /**
     * Loads task based on data in text file
     */
    private void loadTasks() throws InvalidDateFormatException {
        try {
            Files.createDirectories(SAVED_TASKS_FILE_PATH.getParent());
            File taskFile = SAVED_TASKS_FILE_PATH.toFile();
            if (!taskFile.createNewFile()) {
                Scanner sc = new Scanner(new FileReader(taskFile));
                while (sc.hasNextLine()) {
                    String[] deconstructedDetails = sc.nextLine().trim().split("\\s+\\|\\s+");
                    String taskType = deconstructedDetails[0];
                    String done = deconstructedDetails[1];
                    String description = deconstructedDetails[2];
                    Task task = null;

                    if (Objects.equals(taskType, "T")) {
                        task = new ToDo(description);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadline(description, deconstructedDetails[3]);
                    } else if (Objects.equals(taskType, "E")) {
                        task = new Event(description, deconstructedDetails[3]);
                    }

                    if (Objects.equals(done, "1")) {
                        task.markAsDone();
                    }

                    taskList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR!: " + e);
            System.exit(1);
        }
    }

    /**
     * Saves task to text file based on task list
     */
    private void saveTasks() {
        try {
            FileWriter taskFileWriter = new FileWriter(SAVED_TASKS_FILE_PATH.toFile());
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                taskFileWriter.write(taskList.getTask(i).toFileString());
                taskFileWriter.write(System.lineSeparator());
            }
            taskFileWriter.close();
        } catch(IOException e) {

        }
    }

    /**
     * Start the interaction with an instance of blob.Blob
     */
    public void start() {
        try {
            this.loadTasks();
        } catch (InvalidDateFormatException e) {
            ui.speakToUser(e.getBlobMessages());
        }
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        while (true) {
            ui.promptUserInput();
            try {
                Command command = parser.parseUserInput(sc.nextLine());
                CommandResult result;

                if (command.isTaskCommand()) {
                    TaskCommand taskCommand = (TaskCommand) command;
                    taskCommand.setTaskList(taskList);
                    result = taskCommand.execute();
                } else {
                    result = command.execute();
                }

                ui.speakToUser(result.getResultMessages());

                if (command.isByeCommand()) {
                    end();
                }
            } catch (BlobException exception) {
                ui.speakToUser(exception.getBlobMessages());
            }
        }
    }

    public void end() {
        saveTasks();
        System.exit(0);
    }

    public static void main(String[] args) {
        Blob blob = new Blob();
        blob.start();
    }
}
