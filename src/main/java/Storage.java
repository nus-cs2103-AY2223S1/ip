import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY = System.getProperty("user.home") + "/Duke";
    private static final String FILE_PATH = DIRECTORY + "/Duke.txt";
    private Path path;
    protected File dukeFile;
    private List tasks;

    public Storage(String filePath) throws DukeException{
        try {
            File parentDirectory = new File(DIRECTORY);

            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }

            dukeFile = new File(FILE_PATH);

            if(!dukeFile.exists()) {
                dukeFile.createNewFile();
            }
            path = Paths.get(FILE_PATH);
        } catch (IOException e) {
            throw new DukeException("Ugh. I cannot seem to create a file for you ...");
        }
    }

    public void load(List tasks) throws DukeException {
        this.tasks = tasks;
        try {
            Scanner sc = new Scanner(dukeFile);
            while (sc.hasNextLine()) {
                String commands = sc.nextLine();
                String taskType = commands.substring(1, 2);
                String taskStatus = commands.substring(4, 5);
                String taskDescription = commands.substring(7);

                switch (taskType) {
                    case "T":
                        Task toDo = new ToDo(taskDescription);
                        if (taskStatus == "X") {
                            toDo.markTaskAsDone();
                        }
                        tasks.addTask(toDo);
                        break;
                    case "D":
                        String[] deadlineDescription = taskDescription.split("by:");
                        String deadlineName = deadlineDescription[0]
                                .substring(0, deadlineDescription[0].length() - 2)
                                .trim();
                        String by = deadlineDescription[1].substring(1, deadlineDescription[1].length() - 2).trim();
                        LocalDate date = LocalDate.parse(by);
                        Task deadline = new Deadline(deadlineName, date);
                        if (taskStatus == "X") {
                            deadline.markTaskAsDone();
                        }
                        tasks.addTask(deadline);
                        break;
                    case "E":
                        String[] eventDescription = taskDescription.split("at:");
                        String eventName = eventDescription[0]
                                .substring(0, eventDescription[0].length() - 2)
                                .trim();
                        String at = eventDescription[1].substring(0, eventDescription[1].length() - 2).trim();
                        Task event = new Event(eventName, at);
                        if (taskStatus == "X") {
                            event.markTaskAsDone();
                        }
                        tasks.addTask(event);
                        break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("Sorry! I think I cannot read your file.");
        }
    }

    public void save() throws DukeException {
        try {
            FileWriter writer = new FileWriter(dukeFile);
            for (int i = 0; i < tasks.taskList.size(); i++) {
                Task task = tasks.taskList.get(i);
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Sorry! I think I cannot write your file.");
        }
    }

    public String getPath() {
        return path.toString();
    }

}
