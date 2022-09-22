package meower;

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
import exception.MeowerException;
import exception.MeowerFileAddressInvalidException;
import exception.MeowerFileNotFoundException;
import exception.MeowerIOException;
import exception.InvalidCommandException;
import task.Task;

public class Storage {

    private final String MESSAGE_ERROR_ARCHIVENAME = "Archive name not allowed";
    private final String MESSAGE_ERROR_FILEPATH = "File path specified does not exist";
    private final String MESSAGE_ERROR_FILESAVE = "Error in saving Tasks";
    private final String MESSAGE_FILE_ADDRESS_ERROR = "User file address invalid, please check pathing";
    private final String LOG_FILE_DIRECTORY = "./src/main/resources/logs"; //log files must be in resources directory
    private final String ARCHIVE_PATH = "./src/main/resources/archive";

    private String logFileAddress = "./src/main/resources/logs/meowerLog.txt"; //default log file address
    private String saveFileAddress = "./src/main/resources/logs/meowerLog.txt"; //default archive file address

    private Archive archive = new Archive();
    private ArrayList<String[]> loggedTasks = new ArrayList<String[]>();
    private TaskList existingTasks;
    private Ui ui;

    public Storage(TaskList existingTasks, Ui ui) {
        this.existingTasks = existingTasks;
        this.ui = ui;
    }

    public void createArchive() throws MeowerFileAddressInvalidException {
        if (!this.verifyPath(ARCHIVE_PATH)) { 
            this.createDirectory(ARCHIVE_PATH);
            this.archive = new Archive();
        }
    }

    public void clearArchive() {
        this.archive.clear();
    }
    
    /** 
     * loadLog loads the tasks stored in the log file into the current tasklist, 
     * throws DukeExceptions if theres issues with the format of the log file or file address is invalid
     * @return TaskList
     * @throws MeowerException Main Meower chatbot Exception
     */
    public int loadFile(boolean isLog) throws MeowerException {
        this.loggedTasks.clear();
        if (isLog) {
            this.saveFileAddress = this.logFileAddress;
        }
        assert this.saveFileAddress != null: "There must be a log file address at this point";
        
        try {
            //initialise parser, file scanner and tasklist
            Parser parser = new Parser();
            Scanner fileReader = new Scanner(new File(this.saveFileAddress));
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
            throw new MeowerFileNotFoundException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Overloaded constructor for case where user defines a new file address
     * @param newPath String representation of user defined new file path, relative path from ip folder
     * @return int
     * @throws MeowerException Main Meower chatbot Exception
     */
    public int loadFile(String newPath) throws MeowerException {
        String fullPath = this.LOG_FILE_DIRECTORY + "/" + newPath;
        if (!this.verifyPath(fullPath)) {
            throw new MeowerFileAddressInvalidException(MESSAGE_ERROR_FILEPATH);
        }
        this.logFileAddress = fullPath;
        this.saveFileAddress = fullPath;
        return this.loadFile(true);
    }

    public int archiveToFile(String archiveName) throws MeowerException {
        //create archive directory structure if it doesn't yet exist
        this.createArchive();

        //archive by storing file names in a arraylist, dedicated archive folder
        if (!this.verifyAddress(archiveName)) { //note to self use better method to verify
            throw new MeowerFileAddressInvalidException(MESSAGE_ERROR_ARCHIVENAME); //since archive name is name of file must not have spaces etc
        }
        this.saveFileAddress = ARCHIVE_PATH + "/" + archiveName;
        int numOfTasks = this.saveToFile(false);
        this.existingTasks.clear();
        archive.add(archiveName);
        return numOfTasks;
    }

    public int loadArchive(String archiveName) throws MeowerException {
        if (!this.archive.contains(archiveName)) {
            throw new MeowerFileAddressInvalidException(MESSAGE_FILE_ADDRESS_ERROR);
        }
        String fullPath = this.ARCHIVE_PATH + "/" + archiveName;
        this.existingTasks.clear();
        this.saveFileAddress = fullPath;
        int numOfTasks = loadFile(false);
        return numOfTasks;
    }
    
    /** 
     * Saves the tasks in the tasklist into a logfile given by a pre-loaded file address
     * @throws MeowerException Main Meower chatbot Exception
     */
    public int saveToFile(boolean isLog) throws MeowerException {
        if (isLog) {
            this.saveFileAddress = this.logFileAddress;
        }
        assert this.saveFileAddress != null: "There must be a log file address at this point";

        //if no tasks to be saved, exit with message
        if (existingTasks.getSize() == 0) {
            return 0;
        }
        try {
            //Verify filepath exists
            this.verifyAddress(this.saveFileAddress);
            this.createDirectory(this.saveFileAddress);

            //initialise file writer and integer counter
            FileWriter fileWriter = new FileWriter(this.saveFileAddress);
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
            throw new MeowerIOException(MESSAGE_ERROR_FILESAVE);
        }
    }

    /**
     * Overloaded constructor that allows for a new user defined file path
     * @param newFilePath user inputted new file path for saving log files, relative path from ip folder
     * @throws MeowerException Main Meower chatbot Exception
     */
    public int saveToFile(String newFilePath) throws MeowerException{
        if (!this.verifyAddress(newFilePath)) {
            throw new MeowerFileAddressInvalidException(MESSAGE_FILE_ADDRESS_ERROR);
        }
        this.logFileAddress = this.LOG_FILE_DIRECTORY + "/" + newFilePath;
        this.saveFileAddress = this.logFileAddress;
        return this.saveToFile(true);
    }

    
    /** 
     * Verifies if the path already exists
     * @param fullPath file path to be verified
     * @return boolean
     */
    private boolean verifyPath(String fullPath) {
        Path fullPathToCheck = Paths.get(fullPath);
        if (Files.exists(fullPathToCheck)) {
            return true;
        } else {
            return false;
        }
    }

        /** 
     * verify if user given file address is valid
     * @param address file path address given by user
     * @return boolean
     */

    private boolean verifyAddress(String address) {
        //pre-process address string
        String addressToCheck = address.strip();
        String[] addressSplit = addressToCheck.split(" ");

        //verification
        if (addressSplit.length > 1) {
            return false;
        }
        if (address.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * creates directories along the file path saved in the log file address property
     */
    private void createDirectory(String path) throws MeowerFileAddressInvalidException{
        String directoriesPath = this.removeFilePath(path);
        Path dirPath = Paths.get(directoriesPath);
        if (!Files.exists(dirPath)) {

            //if path doesnt exist, create directory
            File directory = new File(directoriesPath);
            if (!directory.mkdirs()) {
                throw new MeowerFileAddressInvalidException("cannot create directory as file address is invalid");
            }
        }
    }

    public String listArchive() {
        return this.archive.toString();
    }

    
    /** 
     * Removes the directories in the file path, leaving only the file name
     * @param fullPath file path to a file
     * @return String
     */
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
}
