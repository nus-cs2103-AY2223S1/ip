package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import exception.DukeException;
import exception.DukeFileAddressInvalidException;
import exception.DukeFileNotFoundException;
import exception.DukeIOException;
import exception.InvalidCommandException;
import task.Task;

public class Storage {

    private final String LOG_FILE_DIRECTORY = "./resources"; //log files must be in resources directory
    private String logFileAddress = "./resources/dukeLog.txt"; //default file address

    private ArrayList<String[]> loggedTasks = new ArrayList<String[]>();
    private TaskList existingTasks;
    private Ui ui;

    public Storage(TaskList existingTasks, Ui ui) {
        this.existingTasks = existingTasks;
        this.ui = ui;
    }
    
    
    /** 
     * loadLog loads the tasks stored in the log file into the current tasklist, 
     * throws DukeExceptions if theres issues with the format of the log file or file address is invalid
     * @return TaskList
     * @throws DukeException
     */
    public int loadLog() throws DukeException{
        try {
            //initialise parser, file scanner and tasklist
            Parser parser = new Parser();
            Scanner fileReader = new Scanner(new File(logFileAddress));
            TaskList newTaskList = new TaskList();

            //read the log file and store tasks read to the temporary arraylist of task logs
            while (fileReader.hasNextLine()) {
                String nextLogLine = fileReader.nextLine();
                String[] parsedLogLine = nextLogLine.split(",", 2);
                loggedTasks.add(parsedLogLine);
            }

            //close the scanner
            fileReader.close();

            //add all the tasks in the temporary array list of task logs into the tasklist
            int numOfTasks = 0;
            for (String[] loggedTask : loggedTasks) {
                boolean isDone = Integer.parseInt(loggedTask[0]) == 1;
                Command parsedCommand = parser.parse(loggedTask[1]);
                Task newTask = parsedCommand.getTask();
                numOfTasks += 1;
                if (isDone) {
                    newTask.mark();
                }
                newTaskList.add(parsedCommand.getTask());
            }

            //return the new tasklist to be used
            this.existingTasks.replace(newTaskList);
            return numOfTasks;
        } catch (InvalidCommandException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e.getLocalizedMessage());
        }
    }

    public int loadLog(String newPath) throws DukeException{
        String fullPath = this.LOG_FILE_DIRECTORY + "/" + newPath;
        if (!this.verifyPath(fullPath)) {
            throw new DukeFileAddressInvalidException("File path specified does not exist");
        }
        this.logFileAddress = fullPath;
        return this.loadLog();
    }
    
    /** 
     * @throws DukeException
     */
    public int cleanUp() throws DukeException {
        //if no tasks to be saved, exit with message
        if (existingTasks.getSize() == 0) {
            return 0;
        }
        try {
            //Verify filepath exists
            this.createDirectory();

            //initialise file writer and integer counter
            FileWriter fileWriter = new FileWriter(logFileAddress);
            int numOfTasks = 0;

            //log tasks in tasklist
            for (Task task : existingTasks.getTasks()) {
                fileWriter.write(task.log());
                numOfTasks += 1;
            }

            //close file writer and show message to user
            fileWriter.close();
            return numOfTasks;
        }
        catch (IOException e) {
            throw new DukeIOException("Error in saving Tasks\n");
        }
    }

    /**
     * 
     * @param newFileName
     * @throws DukeException
     */
    public int cleanUp(String newFileName) throws DukeException{
        logFileAddress = this.LOG_FILE_DIRECTORY + "/" + newFileName;
        return this.cleanUp();
    }

    private boolean verifyPath(String fullPath) {
        Path fullPathToCheck = Paths.get(fullPath);
        if (Files.exists(fullPathToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    private void createDirectory() {
        String directoriesPath = this.removeFilePath(this.logFileAddress);
        Path dirPath = Paths.get(directoriesPath);
        if (!Files.exists(dirPath)) {

            //if path doesnt exist, create directory
            File directory = new File(directoriesPath);
            if (!directory.mkdirs()) {
                this.ui.logFileError();
            }
        }
    }

    private String removeFilePath(String fullPath) {
        return fullPath.substring(0, fullPath.lastIndexOf("/") + 1);
    }
    
    /** 
     * @param numOfTasks
     */
    public void sendEndMessage(int numOfTasks) {
        this.ui.chat(String.format("Saved %d tasks to log file\n", numOfTasks));
    }

    public void sendNoTasksMessage() {
        this.ui.chat("No tasks saved to log file \n");
    }

    public void sendLoadMessage(int numOfTasks) {
        this.ui.chat(String.format("Loaded %d tasks", numOfTasks));
    }

}
