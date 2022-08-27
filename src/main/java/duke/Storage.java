package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String tempFilePath;

    public Storage(String filePath, String tempFilePath) {
        this.filePath = filePath;
        this.tempFilePath = tempFilePath;
    }

    public ArrayList<Task> startUp() {
        // Ensure file exists
        File hardDiskTasks = new File(filePath);
        File tempTasks = new File(tempFilePath);
        try {
            hardDiskTasks.createNewFile();
            tempTasks.createNewFile();
        } catch (IOException | SecurityException e) {
            System.out.println("     " + e.getMessage());
        }

        // Add disk info to taskList
        ArrayList<Task> pastTasks = loadTasksFromDisk(hardDiskTasks);
        return pastTasks;
    }

    public ArrayList<Task> loadTasksFromDisk(File file) {
        ArrayList<Task> pastTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String memo = s.nextLine();

                // first letter to identify task
                String task = memo.substring(0, 1);

                // Retrieve task status
                int indexOfFirstBreak = memo.indexOf("|");
                String status = memo.substring(indexOfFirstBreak + 2, indexOfFirstBreak + 3);
                boolean statusIsDone = status.equals("1");

                // skip "| x | " to get task description
                String descriptionAndTime = memo.substring(indexOfFirstBreak + 6);
                int indexOfThirdBreak = descriptionAndTime.indexOf("|");
                String description = descriptionAndTime;
                String time = "";

                // if time exists, retrieve time and update task description
                if (indexOfThirdBreak != -1) {
                    description = descriptionAndTime.substring(0, indexOfThirdBreak - 1);
                    time = descriptionAndTime.substring(indexOfThirdBreak + 2);
                }

                // update list
                Task currentTask = createTask(task, description, time, statusIsDone);
                pastTasks.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
        return pastTasks;
    }

    public Task createTask(String task, String description, String time, boolean isDone) {
        Task newTask = null;
        LocalDate date = null;
        if (time != "") {
            date = LocalDate.parse(time);
        }
        switch (task) {
        case "T":
            newTask = new ToDo(description);
            break;
        case "D":
            newTask = new Deadline(description, date);
            break;
        case "E":
            newTask = new Event(description, date);
            break;
        default:
            throw new DukeException("OOPS!!! The Disk memory is invalid");
        }
        newTask.setTaskStatus(isDone);
        return newTask;
    }

    public void addTaskToDisk(String task) {
        appendToFile(filePath, task);
    }

    public void setTaskStatusOnDisk(int taskNumber, boolean isDone) {
        File inputFile = new File(filePath);
        File tempFile = new File(tempFilePath);
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                if (taskNumber == 1) {
                    // before status "x | description"
                    int indexOfFirstBreak = currentLine.indexOf("|");
                    String beforeStatus = currentLine.substring(0, indexOfFirstBreak + 2);

                    // after " X | x"
                    String afterStatus = currentLine.substring(indexOfFirstBreak + 3);
                    String status = isDone ? "1" : "0";
                    currentLine = beforeStatus + status + afterStatus;
                }
                appendToFile(tempFilePath, currentLine + System.lineSeparator());
                taskNumber -= 1;
            }
            s.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public void deleteTaskFromDisk(int taskNumber) {
        File inputFile = new File(filePath);
        File tempFile = new File(tempFilePath);
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                if (taskNumber != 1) {
                    appendToFile(tempFilePath, currentLine + System.lineSeparator());
                }
                taskNumber -= 1;
            }
            s.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("     " + e.getMessage());
        }
    }

    public void appendToFile(String file, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("     " + e.getMessage());
        }
    }
}
