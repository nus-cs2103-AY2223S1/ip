import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private static Path filePath;
    private static Path directoryPath;

    Storage(Path directoryPath, Path filePath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    void save(List<Task> internalArray) {
        File directory = new File(directoryPath.toUri());
        directory.mkdir();
        try {
            File file = new File(filePath.toUri());
            file.delete();
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (Task task : internalArray) {
                writer.write(task.saveFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Method to load data from stored file to the chat-bot
     */
    public List<Task> load() {
        Task newTask;
        List<Task> botList = new ArrayList<Task>();
        try {
            File file = new File(filePath.toUri());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                List<String> stored = new ArrayList<>(List.of(data.split("\\|")));
                stored = stored
                        .stream()
                        .filter(e -> !e.equals("") && !e.equals("null"))
                        .collect(Collectors.toList());
                String taskType = stored.remove(0);
                boolean isCompleted = stored.remove(0).equals("1");
                try {
                    switch (taskType) {
                    case ("T"):
                        newTask = new ToDo(stored);
                        break;
                    case ("E"):
                        newTask = new Event(stored);
                        break;
                    case ("D"):
                        newTask = new Deadline(stored);
                        break;
                    default:
                        throw new DekuExceptions("");
                    };
                    newTask.setCompletionStatus(isCompleted);
                    botList.add(newTask);
                } catch (DekuExceptions e) {
                    System.out.println("Error reading data file! Sorry... :(");
                }
            }
            scanner.close();
            return botList;
        } catch (FileNotFoundException e) {
            return botList;
        }
    }
}
