package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import exception.InvalidCommandException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import task.Task;

public class Storage {

    private static String logFileAddress = "./ip/dukeLog.txt";

    private ArrayList<String[]> loggedTasks = new ArrayList<String[]>();
    private TaskList existingTasks;
    private Ui ui;
    private Storage storage;

    public Storage(TaskList existingTasks, Ui ui, Storage storage) {
        this.existingTasks = existingTasks;
        this.ui = ui;
        this.storage = storage;
    }
    
    public TaskList loadLog(String fileAddress, Parser parser) throws InvalidCommandException, InvalidDateException, MissingArgumentException, FileNotFoundException{
        try {
            Scanner fileReader = new Scanner(new File(fileAddress));
            TaskList newTaskList = new TaskList();
            while (fileReader.hasNextLine()) {
                String nextLogLine = fileReader.nextLine();
                String[] parsedLogLine = nextLogLine.split(",", 2);
                loggedTasks.add(parsedLogLine);
            }
            fileReader.close();
            for (String[] loggedTask : loggedTasks) {
                boolean isDone = Integer.parseInt(loggedTask[0]) == 1;
                Command parsedCommand = parser.parseCommand(loggedTask[1]);
                newTaskList.add(parsedCommand.getTask());
            }
        } catch (MissingArgumentException e) {
            throw e;
        } catch (InvalidCommandException e) {
            throw e;
        } catch (InvalidDateException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public void cleanUp() throws IOException {
        if (existingTasks.size() == 0) {
            this.sendNoTasksMessage();
        }
        try {
            FileWriter fileWriter = new FileWriter(logFileAddress);
            int numOfTasks = 0;
            for (Task task : existingTasks.getTasks()) {
                fileWriter.write(task.log());
                numOfTasks += 1;
            }
            fileWriter.close();
            this.sendEndMessage(numOfTasks);
        }
        catch (IOException e) {
            throw new IOException("Error in saving Tasks\n");
        }
    }

    public void sendEndMessage(int numOfTasks) {
        this.ui.chat(String.format("Saved %d tasks to log file\n", numOfTasks));
    }

    public void sendNoTasksMessage() {
        this.ui.chat("No tasks saved to log file \n");
    }

}
