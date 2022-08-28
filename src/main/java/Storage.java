import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private File directory;
    private File file;

    public Storage(String dirName, String fileName) {
        this.directory = new File(dirName);
        this.file = new File(dirName + "/" + fileName);
    }

    public ArrayList<Task> getTasksFromDisk() throws FileNotFoundException, DukeException {
        ArrayList<Task> list = new ArrayList<>();
        if (this.file.exists()) {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskArr = line.split(" \\| ");
                if (taskArr[0].equals("T")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    Todo t = new Todo(description);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else if (taskArr[0].equals("D")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Deadline t = new Deadline(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else if (taskArr[0].equals("E")) {
                    String done = taskArr[1];
                    String description = taskArr[2];
                    String date = taskArr[3];
                    Event t = new Event(description, date);
                    if (done.equals("X")) {
                        t.markDone();
                    }
                    list.add(list.size(), t);
                } else {
                    throw new DukeException("Oops, unknown task type.");
                }
            }
            System.out.println("Loaded tasks.");
        }
        return list;
    }

    public void saveTasks(ArrayList<Task> list) throws IOException {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i).save() + System.lineSeparator());
        }
        fw.close();
    }
}
