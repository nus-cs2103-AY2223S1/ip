import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final File saveFile;

    Storage(File saveFile) {
        this.saveFile = saveFile;
    }

    Storage(String filePath) {
        this.saveFile = new File(filePath);
    }

    TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.saveFile);
            TaskList ret = new TaskList();
            while (sc.hasNext()) {
                String[] curTask = sc.nextLine().split(" / ");
                // Task type saved in the third parameter
                switch (curTask[2]) {
                    case "T":
                        ret.addTask(new Todo(curTask[0], curTask[1].equals("true")));
                        break;
                    case "D":
                        ret.addTask(new Deadline(curTask[0], curTask[3], curTask[1].equals("true")));
                        break;
                    case "E":
                        ret.addTask(new Event(curTask[0], curTask[3], curTask[1].equals("true")));
                        break;
                    default:
                        throw new DukeException("Invalid save file data. Will ignore save file.");
                }
            }
            return ret;
        } catch (FileNotFoundException e) {
            try {
                this.saveFile.getParentFile().mkdirs();
                this.saveFile.createNewFile();
            } catch (IOException e1) {
                throw new DukeException("Unable to create save-file.", e1);
            } catch (SecurityException e2) {
                throw new DukeException("Write access denied to save file. Please make sure TedBot has access to duke/save.txt.", e2);
            }
            throw new DukeException("No save file found. A new save file is generated.");
        }
    }

    void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.saveFile);
            for (int i = 0; i < taskList.getLength(); i++) {
                fw.write(taskList.getTask(i).toStorageString() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to save file. Last change is not added to save file.", e);
        }
    }
}
