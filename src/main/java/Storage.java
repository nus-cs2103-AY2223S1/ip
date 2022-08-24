import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public Storage() {
    }

    /**
     * @return ArrayList<Task> from the save file at /data/saveFile.txt if it exists.
     * Otherwise, creates the data directory and a new saveFile and returns empty ArrayList
     */
    public static ArrayList<Task> load() {
        File folder = new File("./data");
        File saveFile = new File("./data/saveFile.txt");
        ArrayList<Task> arrayList = new ArrayList<>();
        if (!folder.exists()) { // folder does not exist, hence save also does not exist
            System.out.println("No data folder detected. Making data directory and save file...");
            try {
                folder.mkdirs();
                saveFile.createNewFile();
                System.out.println("Success!");
                return arrayList;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // folder exists, check if save exists
            if (saveFile.exists()) {
                System.out.println("Retrieving save file...");
                System.out.println("Success!");
                return convertSaveToArr(saveFile);

            } else {
                try {
                    System.out.println("No save file detected, creating save file...");
                    saveFile.createNewFile();
                    System.out.println("Success!");
                    return arrayList;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    private static ArrayList<Task> convertSaveToArr(File file) {
        ArrayList<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.startsWith("[T]")) { // TodoClass
                    result.add(Todo.stringToTodo(line));
                } else if (line.startsWith("[D]")) { // Deadline Class
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

    public static void save(TaskList tL) {
        try {
            FileWriter fw = new FileWriter("./data/saveFile.txt");
            ArrayList<Task> lst = tL.getTaskArr();
            for (Task task: lst) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
