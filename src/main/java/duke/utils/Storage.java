package duke.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import duke.Date;
import duke.DukeException;
import duke.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Stores the data recorded by the user.
 * @author Jason
 */
public class Storage {
    private File file;
    private File directory;

    /**
     * Constructs a Storage object.
     * @param filePath Relative path of the storage file.
     * @param directoryPath Relative path of the directory containing storage file.
     * @throws IOException Thrown if there was an issue creating the files.
     */
    public Storage(String filePath, String directoryPath) throws DukeException, IOException {
        this.file = new File(filePath);
        this.directory = new File(directoryPath);

        try {
            if (this.directory.exists()) {
                System.out.println("Directory located... \n");
            } else {
                System.out.println("Creating a directory to store save file... \n");
                Files.createDirectories(Path.of(directoryPath));
            }

            if (this.file.createNewFile()) {
                System.out.println("Creating a new save file...");
            } else {
                System.out.println("Previous save file located, loading contents of save file...");
            }

        } catch (IOException e) {
            throw new IOException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Parses the save file to load previously saved contents.
     * @throws DukeException Inputs in save file is wrong.
     * @throws IOException Buffered reader fails the reading of data.
     */
    public List<Task> load() throws DukeException, IOException {
        List<Task> list = new ArrayList<>(100);
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String data = br.readLine();

            while (data != null) {
                String[] dataDetails = data.split(" \\| ");
                String command = dataDetails[0];
                boolean marked; // 1 = marked, 0 = unmarked
                String description = dataDetails[2];
                Task task;

                if (!dataDetails[1].equals("1")) {
                    if (!dataDetails[1].equals("0")) {
                        throw new DukeException("☹ OOPS!!! The save file is corrupted, "
                                + "please delete the file and retry!");
                    } else {
                        marked = false;
                    }
                } else {
                    marked = true;
                }

                switch (command) {
                case("T"):
                    task = new Todo(description);
                    break;
                case("D"):
                    if (dataDetails.length != 4) {
                        throw new DukeException("☹ OOPS!!! A duke.task.Deadline task is corrupted!");
                    }
                    Date deadlineDate = Parser.parseDateSave(dataDetails[3]);
                    task = new Deadline(description, deadlineDate);
                    break;
                case("E"):
                    if (dataDetails.length != 4) {
                        throw new DukeException("☹ OOPS!!! An duke.task.Event task is corrupted!");
                    }
                    Date eventDate = Parser.parseDateSave(dataDetails[3]);
                    task = new Event(description, eventDate);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! The save file is corrupted, please delete the file and retry!");
                }

                if (marked) {
                    task.markAsDone();
                }

                list.add(task);

                data = br.readLine();
            }
        }
        System.out.println("I have reloaded your saved file ☺!");
        return list;
    }

    /**
     * Writes all current tasks on the save file.
     */
    public void saveData(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        try {
            for (Task task : taskList) {
                String data = task.saveData();
                fw.write(data + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new IOException("Something went wrong: " + e.getMessage());
        }
    }
}
