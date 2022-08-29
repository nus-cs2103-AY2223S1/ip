package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String file) {
        this.filePath = file;
        this.checkDirectory();
        this.createFile();
    }
    public void checkDirectory() {
        String PATH = "./data";
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    public  void createFile() {
        try {
            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error has occured");
        }
    }
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myObj = new File(this.filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] taskBreakdown = data.split("\\| ");
                String taskType = taskBreakdown[0].trim();
                boolean isTaskCompleted = taskBreakdown[1].trim().equals("1");
                String taskMessage = taskBreakdown[2].trim();
                String taskDate = taskBreakdown.length > 3 ? taskBreakdown[3].trim(): "";

                if (taskType.equals("T")) {
                    tasks.add(new Todo("todo " + taskMessage, isTaskCompleted));
                }
                else if (taskType.equals("E")) {
                    tasks.add(new Event("event " + taskMessage + "/at " + taskDate, isTaskCompleted));
                }
                else if (taskType.equals("D")) {
                    tasks.add(new Deadline("deadline" + taskMessage + "/by " + taskDate, isTaskCompleted));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File is not found.");
        }
        return tasks;
    }
    public void writeFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task nextTask = tasks.get(i);
                myWriter.write(( nextTask.toFormattedString()) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
