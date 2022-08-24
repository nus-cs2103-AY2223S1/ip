package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private final String DIRECTORY_PATH;
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.DIRECTORY_PATH = null;
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> loadLocalData() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {

            File file = new File(this.FILE_PATH);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                if ("D".equals(split[0])) {
                    LocalDate ld = LocalDate.parse(split[3]);
                    task = new Deadline(split[2], ld);
                } else if ("E".equals(split[0])) {
                    LocalDate ld = LocalDate.parse(split[3]);
                    task = new Event(split[2], ld);
                } else if ("T".equals(split[0])) {
                    task = new Todo(split[2]);
                } else {
                    throw new DukeException("Unable to read file.");
                }
                if (split[1].equals("Y")) {
                    task.toggleDoneness();
                }
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/"));
                File file = new File("data/duke.txt");
                file.createNewFile();
                System.out.print("New file created to store tasks.");
            } catch (IOException ex) {
                throw new DukeException(ex.getMessage());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to read file.");
        } finally {
            return taskList;
        }
    }

    public void saveLocalData(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskList) {
                String str = task.stringify();
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
