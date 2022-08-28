package bobthebot.utils;

import bobthebot.tasks.Task;
import bobthebot.tasks.Deadline;
import bobthebot.tasks.Event;
import bobthebot.tasks.Todo;


import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class which handles the interaction of the BobTheBot with the file storing the ToDo List items.
 * */
public class Storage {
    private String filePath;
    private String dirPath;

    /**
     * Constructs a storage object.
     * */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    /**
     * Method which loads data from the specified file into an ArrayList, which can be used for the ToDo List object.
     * If the file is not found, an empty ArrayList is returned.
     * @return An ArrayList containing the tasks in the specified file, or an empty ArrayList if there is no
     * file or the file is empty.
     * */
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

    /**
     * Method which takes in an ArrayList of tasks and stores it into the specified file.
     * @param list An ArrayList containing the Tasks in the ToDo list object.
     * */
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
