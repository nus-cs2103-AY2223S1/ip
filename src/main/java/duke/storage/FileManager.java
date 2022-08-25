package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.parser.Parser;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Deals with the saving and loading of TaskRecords data.
 * Contains two read and write static method to perform saving and loading operation.
 */

public class FileManager {

    private static final String home = System.getProperty("user.home");
    private static final java.nio.file.Path path = java.nio.file.Paths.get(home,
            "CS2103", "ip", "src", "main", "java", "data", "duke.txt");
    private static final String SECTION_DIVIDER = " __ ";

    /**
     * Reads the data of TaskRecords saved from the previous duke operation at the start of duke bot operation.
     *
     * @return TaskRecords of the saved task list information.
     * @throws FileNotFoundException - thrown if there is no data saved in the text file or the file does not exist.
     */
    public static TaskRecords read() throws FileNotFoundException {
        TaskRecords savedList = new TaskRecords();
        File previousCache = new File(path.toUri());
        Scanner scn = new Scanner(previousCache);
        while (scn.hasNextLine()) {
            String[] info = scn.nextLine().split(SECTION_DIVIDER);
            if (info.length < 3) {
                break;
            }
            if (info[0].equals("[T]")) {
                savedList.addProcess(new ToDos(info[2], Boolean.parseBoolean(info[1])));
            } else if (info[0].equals("[E]")) {
                savedList.addProcess(new Events(info[2], Boolean.parseBoolean(info[1]),
                        Parser.convertTime(info[3])));
            } else {
                savedList.addProcess(new Deadlines(info[2], Boolean.parseBoolean(info[1]),
                        Parser.convertTime(info[3])));
            }
        }
        return savedList;
    }

    /**
     * Writes the data of TaskRecords to text file whenever changes is made.
     *
     * @throws IOException - thrown if the writing process has fail.
     */
    public static void write(TaskRecords taskList) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task t : taskList.getList()) {
            content.append(t.getId()).append(SECTION_DIVIDER)
                    .append(t.isDone()).append(SECTION_DIVIDER)
                    .append(t.getDetail()).append(SECTION_DIVIDER);
            if (!t.getId().equals("[T]")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                content.append(t.getTime().format(formatter)).append(SECTION_DIVIDER);
            }
            content.append("\n");
        }
        FileWriter writer = new FileWriter(path.toString());
        writer.write(content.toString());
        writer.close();
    }

}
