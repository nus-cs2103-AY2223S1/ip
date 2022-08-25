package models;

import exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private static final String taskDirectoryName = "src/main/data/";
    private static final String taskFileName = "tasklist.txt";
    private static final String taskFilePath = taskDirectoryName + taskFileName;

    private static Task parseTextToTask(String text) throws DukeException{
        String[] taskDetails = text.split("\\s\\|\\s", 0);
        for (String t : taskDetails) {
            System.out.println(t);
        }
        switch (taskDetails[0]) {
            case("T"):
                // TODO: Replace with Enums
                return new Todo(taskDetails[2], taskDetails[1].equals("1"));
            case("D"):
                // TODO: Replace with Enums
                return new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]), taskDetails[1].equals("1"));
            case("E"):
                // TODO: Replace with Enums
                return new Event(taskDetails[2],LocalDate.parse(taskDetails[3]), taskDetails[1].equals("1"));
        }
        throw new DukeException("Cannot parse saved tasks!");
    }

    public static TaskList loadTasksFromDisk() throws DukeException {
        try {
            // Create folder and file if not exist
            File dir = new File(taskDirectoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File myFile = new File(taskFilePath);
            myFile.createNewFile();

            Scanner fileReader = new Scanner(myFile);

            TaskList taskList = new TaskList();
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println("Reading");
                System.out.println(data);
                taskList.AddTask(parseTextToTask(data));
            }
            fileReader.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Cannot create file to save tasks!");
        }
    }

    public static void saveTaskToDisk(TaskList tasks) throws DukeException{
        try {
            FileWriter out = new FileWriter(taskFilePath);
            tasks.forEach((task) -> {
                try {
                    out.write(task.formatForSave() + "\n");
                } catch (IOException e) {
                    // TODO: Add error handling
                    System.out.println("An error occurred.");
                }
            });
            out.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot write task to disk!");
        } catch (IOException e) {
            // TODO: Add error handling
            System.out.println("An error occurred.");
        }
    }
}
