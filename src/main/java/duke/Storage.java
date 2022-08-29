package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Stores and retrieves information of the tasklist for Duke.
 */
public class Storage {
    private final String filePath;
    private final String dirPath;

    /**
     * Constructor for Storage.
     */
    public Storage() {
        this.filePath = "data" + File.separator + "lilychat.txt";
        this.dirPath = "data";
    }

    /**
     * Initialises the initial ArrayList.
     * @return ArrayList to be passed into TaskList.
     */
    public ArrayList<Task> initialise() {
        File file = new File(filePath);
        try {
            Scanner s = new Scanner(file);
            ArrayList<Task> output = new ArrayList<>();
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] splitTask = task.split("\\|");
                Task toAdd;
                if (splitTask[0].equals("T")) {
                    toAdd = new ToDo(splitTask[2]);
                } else if (splitTask[0].equals("E")) {
                    toAdd = new Event(splitTask[2], splitTask[3]);
                } else {
                    toAdd = new Deadline(splitTask[2], splitTask[3]);
                }
                if (splitTask[1].equals("1")) {
                    toAdd.markDone();
                }
                output.add(toAdd);
            }
            return output;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the taskList given by Duke into user's computer.
     * @param list taskList which is being saved into user's computer.
     */
    public void writeFile(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (int i = 0; i < list.getSize(); i++) {
                if (i == 0) {
                    textToAdd += list.taskSaveToString(i);
                } else {
                    textToAdd += "\n" + list.taskSaveToString(i);
                }
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            File file = new File(dirPath);
            if (file.mkdir()) {
                writeFile(list);
            } else {
                System.out.println("Failed to create file");
            }
        }
    }
}
