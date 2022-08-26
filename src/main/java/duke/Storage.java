package duke;

import duke.task.Task;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private final File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public boolean isFileExists() {
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