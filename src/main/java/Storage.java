import duke.exceptions.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    public Storage() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data");
        File dataDir = new File(path.toString());
        if (!dataDir.exists()) {
            if (!dataDir.mkdir()) {
                throw new DukeException("Unable to create 'data' directory");
            }
        }
    }

    public void loadData(TaskList data) throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        try {
            File savedData = new File(path.toString());
            Scanner sc = new Scanner(savedData);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] taskData = line.split("\\|");
                if (taskData.length < 3 || (taskData.length < 4 && !"T".equals(taskData[0]))) {
                    throw new DukeException("File format invalid!");
                }
                Task task = null;
                switch (taskData[0]) {
                    case "T":
                        task = new ToDo(taskData[2]);
                        break;
                    case "D":
                        task = new Deadline(taskData[2], LocalDate.parse(taskData[3]));
                        break;
                    case "E":
                        task = new Event(taskData[2], LocalDate.parse(taskData[3]));
                        break;
                }
                if (task != null) {
                    data.add(task);
                    if ("1".equals(taskData[1])) task.markDone();
                    else task.markNotDone();
                }
            }
            sc.close();
        } catch (FileNotFoundException ignored){}
    }

    public void saveData(TaskList data) throws DukeException {
        try {
            FileWriter file = new FileWriter("data/duke.txt", false);
            for (int i = 0; i < data.size(); i++) {
                file.write(data.get(i).stringToSave() + System.lineSeparator());
            }
            file.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when writing to file");
        }
    }
}
