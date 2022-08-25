package duke;

import duke.task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public boolean fileExists() {
        return f.exists();
    }

    public void createNewFile() throws IOException {
        f.createNewFile();
    }

    public void update(ArrayList<Task> tasks) {
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(tasks);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        try (FileInputStream fis = new FileInputStream(f);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (ArrayList<Task>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}