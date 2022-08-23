package henry;

import command.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private static final String home = System.getProperty("user.home");
    private static final Path FILE_PATH = java.nio.file.Paths.get(home, "Desktop", "henry.txt");
    private List<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File savedList = new File(filePath);
            if (savedList.createNewFile()) {
                tasks = new ArrayList<>();
            } else {
                tasks = parseTasksFromFile(savedList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() {
        return tasks;
    }

    private List<Task> parseTasksFromFile(File savedList) throws FileNotFoundException {
        Scanner s = new Scanner(savedList);
        List<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] tokens = line.split("\\|");
            Commands type;
            String description;
            LocalDateTime date = null;
            boolean isComplete;

            String prefix;
            String cleaned;
            prefix = tokens[0].trim();
            switch (prefix) {
            case "T":
                type = Commands.TODO;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                break;
            case "D":
                type = Commands.DEADLINE;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                cleaned = tokens[3].replace("(by:", "").replace(")", "").trim();
                date = parseDateTime(cleaned.split(" ")[0], cleaned.split(" ")[1]);
                break;
            default:
                type = Commands.EVENT;
                isComplete = tokens[1].trim().equals("1");
                description = tokens[2].trim();
                cleaned = tokens[3].replace("(at:", "").replace(")", "").trim();
                date = parseDateTime(cleaned.split(" ")[0], cleaned.split(" ")[1]);
                break;
            }
            tasks.add(new Task(type, description, date, isComplete));
        }
        return tasks;
    }

    private LocalDateTime parseDateTime(String date, String time) {
        String[] tokens = date.split("-");
        String[] timeTokens = time.split(":");
        return LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]))
                        .atTime(Integer.parseInt(timeTokens[0]), Integer.parseInt(timeTokens[1]));
    }

    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
