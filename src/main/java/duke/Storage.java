package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        String[] path = filePath.split("/");
        String fileName = path[path.length - 1];
        String[] directoryName = filePath.split(fileName);
        newDir(directoryName[0]);
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ");
            if (task.length <= 1) {
                throw new DukeException("☹ OOPS!!! The file is corrupted.");
            }
            switch (task[0]) {
            case "T":
                tasks.add(new Todo(task[2]));
                break;
            case "D":
                tasks.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                tasks.add(new Event(task[2], task[3]));
                break;
            default:
                throw new DukeException("☹ OOPS!!! The file is corrupted.");
            }
            if (task[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    public static void newFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newDir(String dirName) {
        File file = new File(dirName);
        try {
            file.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void save(TaskList tasklist) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        ArrayList<Task> tasks = tasklist.getTasks();
        for (Task t : tasks) {
            writer.write(t.fileFormat() + "\n");
        }
        writer.close();
    }
}
