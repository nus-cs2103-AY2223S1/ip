import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;

public class SaveLoad {
    private static String projectRoot = System.getProperty("user.dir");
    private static Path dataFolderPath = Paths.get(projectRoot, "data");
    private static boolean directoryExists = java.nio.file.Files.exists(dataFolderPath);
    private static Path saveFilePath = dataFolderPath.resolve("duke.txt");
    private static boolean fileExists = Files.exists(saveFilePath);

    public static void saveTaskList(ArrayList<Task> taskList) {
        // Create directory if it does not exist
        if (!directoryExists) {
            try {
                Files.createDirectory(dataFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Save task list into save file
        try {
            if (fileExists) {
                // Overwrite the current save file
                clearSaveFile();
                for (Task t : taskList) {
                    String reformattedTask = t.changeFormat();
                    Files.write(saveFilePath, (reformattedTask + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                }
            } else {
                // Create new file and save
                Files.createFile(saveFilePath);
                for (Task t : taskList) {
                    String reformattedTask = t.changeFormat();
                    Files.write(saveFilePath, (reformattedTask + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearSaveFile() {
        try {
            Files.newBufferedWriter(saveFilePath, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTaskList(ArrayList<Task> taskList) throws DukeException {
        try {
        String[] linesArr = Files.lines(saveFilePath).toArray(String[]::new);
            for (String l : linesArr) {
                taskList.add(parseString(l));
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }

    }

    private static Task parseString(String taskString) throws DukeException{
        // Split the string into an array of properties
        String[] taskProperties = taskString.split(" \\| ", 4);
        try {
            String taskType = taskProperties[0];
            String taskDone = taskProperties[1];
            String taskDescription = taskProperties[2];
            Task task = null;

            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                break;

            case "E":
                task = new Event(taskDescription);
                task.setTime(taskProperties[3]);
                break;

            case "D":
                task = new Deadline(taskDescription);
                task.setDeadline(taskProperties[3]);
                break;
            }
            if (taskDone.equals("X") && task != null) {
                task.mark();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Error in saved file!");
        }
    }
}
