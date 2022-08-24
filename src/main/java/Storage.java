import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Storage {
    public static void saveTasks(List<Task> tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream("duke_tasks");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred while writing to 'duke_tasks'");
        }
    }

    public static List<Task> loadTasks() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream("duke_tasks");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Task> tasks = (List<Task>) ois.readObject();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred while reading from 'duke_tasks'.");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Failed to read 'duke_tasks'.");
        }
    }
}
