import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskList {
    protected final ArrayList<Task> tasks;
    private final File dataFile;

    public TaskList(Path path) {
        tasks = new ArrayList<>();
        dataFile = new File(path.toUri());

        if (dataFile.isDirectory()) {
            throw new DukeException("Datafile path is a directory");
        }
        // Ref: https://stackoverflow.com/a/4040667
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Could not create directory: " + parent);
        }
        if (!dataFile.exists()) {
            try {
                if (!dataFile.createNewFile()) {
                    throw new DukeException("Could not create file: " + dataFile);
                }
            } catch (IOException e) {
                throw new DukeException("An I/O error occurred creating the file: " + dataFile);
            }
        }

        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String[] splits = scanner.nextLine().split(" \\| ", -1);

                if (splits.length < 3) {
                    throw new DukeException("Datafile is corrupted");
                }

                String[] args = Arrays.copyOfRange(splits, 2, splits.length);

                Task task;
                switch (splits[0]) {
                case "T":
                    task = Task.of(CommandType.TODO, args);
                    break;
                case "D":
                    task = Task.of(CommandType.DEADLINE, args);
                    break;
                case "E":
                    task = Task.of(CommandType.EVENT, args);
                    break;
                default:
                    throw new DukeException("Datafile is corrupted");
                }

                try {
                    task.isDone = Integer.parseInt(splits[1]) == 1;
                } catch (NumberFormatException e) {
                    throw new DukeException("Datafile is corrupted");
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException ignored) {
            throw new DukeException("Datafile is corrupted");
        }
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file: " + dataFile);
        }
    }
}
