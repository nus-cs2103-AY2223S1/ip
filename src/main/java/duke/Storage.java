package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with the saving and loading of previous Tasks from a saveFile.
 */
public class Storage {

    /**
     * Returns ArrayList of Tasks from the saveFile if it exists.
     * Otherwise, creates the data directory and a new saveFile and returns empty ArrayList.
     *
     * @return ArrayList<duke.Task> from the save file at /data/saveFile.txt if it exists.
     */
    public static ArrayList<Task> load() {
        File folder = new File("./data");
        File saveFile = new File("./data/saveFile.txt");
        ArrayList<Task> arrayList = new ArrayList<>();
        if (!folder.exists()) { // folder does not exist, hence save also does not exist
            Ui.show("No data folder detected. Making data directory and save file...");
            try {
                folder.mkdirs();
                saveFile.createNewFile();
                Ui.show("Success!");
                return arrayList;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // folder exists, check if save exists
            if (saveFile.exists()) {
                Ui.show("Retrieving save file...");
                Ui.show("Success!");
                return convertSaveToArr(saveFile);

            } else {
                try {
                    Ui.show("No save file detected, creating save file...");
                    saveFile.createNewFile();
                    Ui.show("Success!");
                    return arrayList;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    /**
     * Returns ArrayList of Tasks from the saveFile by converting each line to a Task
     *
     * @param file saveFile in /data/saveFile.txt
     * @return ArrayList of Tasks from the saveFile
     */
    private static ArrayList<Task> convertSaveToArr(File file) {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.startsWith("[T]")) { // TodoClass
                    result.add(Todo.stringToTodo(line));
                } else if (line.startsWith("[D]")) { // duke.Deadline Class
                    result.add(Deadline.stringToDeadline(line));
                } else if (line.startsWith("[E]")) { // Evemt Class
                    result.add(Event.stringToEvent(line));
                } else { // unknown text
                    result.add(new Task(line));
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Saves the TaskList into memory by overwriting the save file at /data/saveFile.txt
     *
     * @param tL The updated Tasklist
     */
    public static void save(TaskList tL) {
        try {
            FileWriter fw = new FileWriter("./data/saveFile.txt");
            ArrayList<Task> lst = tL.getTaskArr();
            for (Task task : lst) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
