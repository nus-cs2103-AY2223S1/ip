package duke.helper;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskCreator;

public class Storage {

    protected File file;

    public Storage(String filePath) {
        try {
            int indexOfSplit = filePath.lastIndexOf("/");
            String directory = filePath.substring(0, indexOfSplit);
            this.file = new File(filePath);

            if (!file.exists()) {
                Files.createDirectories(Paths.get(directory));
                boolean result = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                list.add(TaskCreator.createFromStorage(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
