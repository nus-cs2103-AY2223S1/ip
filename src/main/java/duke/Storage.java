package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The duke.Storage Class represents the
 * storage for duke.Duke.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(t.toStore());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save file.");
        }
    }


    public ArrayList<Task> load() throws FileNotFoundException {
        File localStorage = new File(filePath);
        ArrayList<Task> fromStorage = new ArrayList<>();
        Scanner sc = new Scanner(localStorage);
        while (sc.hasNext()) {
            String[] strings = sc.nextLine().split(" \\| ");
            if (strings[0].equals("T")) {
                fromStorage.add(new ToDo(Integer.parseInt(strings[1]),
                        strings[2]));
            } else if (strings[0].equals("E")) {
                fromStorage.add(new Event(Integer.parseInt(strings[1]),
                        strings[2], strings[3]));
            } else if (strings[0].equals("D")) {
                fromStorage.add(new Deadline(Integer.parseInt(strings[1]),
                        strings[2], strings[3]));
            }
        }
        return fromStorage;
    }
}


