package bobthebot.utils;

import bobthebot.tasks.Deadline;
import bobthebot.tasks.Event;
import bobthebot.tasks.Task;
import bobthebot.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;
    private String dirPath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    // method for loading whatever is in the file to an array list
    // returns an array list
    // if the file is not found, then return an empty list
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File dir = new File(this.dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(this.filePath);
        if (dir.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Not sure what an IOException is but it has occurred.");
            }
        }

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                System.out.println(input);
                String[] parsedInput = input.split(" \\| ");
                Task task;

                switch (parsedInput[0]) {
                case "T":
                    task = new Todo(parsedInput[2]);
                    break;
                case "D":
                    task = new Deadline(parsedInput[2], parsedInput[3]);
                    break;
                case "E":
                    task = new Event(parsedInput[2], parsedInput[3]);
                    break;
                default:
                    task = null;
                    System.err.println("Error occurred during file loading. I do not process this task type.");
                    break;
                }

                // checking if the task is done
                if (Integer.parseInt(parsedInput[1]) == 1) {
                    task.markDone();
                }

                list.add(task);
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
            return list;
        }
        return list;
    }

    // function to make a task stored in the storage file
    public void store(ArrayList<Task> list) {
        File dir = new File(this.dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(this.filePath);
        if (dir.exists() && !file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Not sure what an IOException is but it has occurred.");
            }
        }

        try {
            FileWriter fw = new FileWriter(this.filePath, false);

            for (Task t : list) {
                fw.append(t.toStorageFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error occurred during file storage. I do not process this task type.");
        }
    }
}
