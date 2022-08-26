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
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void loadFileData(ArrayList<Task> taskArr) throws DukeException {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                if ("D".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Deadline(split[2], localDate);
                } else if ("E".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Event(split[2], localDate);
                } else if ("T".equals(split[0])) {
                    task = new ToDo(split[2]);
                } else {
                    throw new DukeException("Unable to parse items in file.");
                }
                if (split[1].equals("Y")) {
                    task.toggleDone();
                }
                taskArr.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/"));
                File file = new File("data/duke.txt");
                System.out.println("New file created to store tasks.");
            } catch (IOException ex) {
                throw new DukeException(ex.getMessage());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse dates in file.");
        }
    }


    public static void saveFileData(ArrayList<Task> taskArr) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskArr) {
                String str = task.stringify();
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
