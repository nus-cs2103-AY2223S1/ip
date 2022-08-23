package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.ToDos;
import duke.task.Events;
import duke.task.Deadlines;
import duke.task.Task;
import duke.inputparser.Parser;

public class FileManager {

    private static String home = System.getProperty("user.home");
    private static java.nio.file.Path path = java.nio.file.Paths.get(home
            , "OneDrive - National University of Singapore", "AY2223S1", "CS2103", "ip"
            , "data", "duke.txt");
    private static final String SECTION_DIVIDER = " __ ";

    public static TaskRecords read() throws FileNotFoundException {
        TaskRecords savedList = new TaskRecords();
        File previousCache = new File(path.toUri());
        Scanner scn = new Scanner(previousCache);
        while(scn.hasNextLine()) {
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

    public static void write(TaskRecords taskList) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task t : taskList.getList()) {
            content.append(t.getId()).append(SECTION_DIVIDER)
                    .append(t.isDone()).append(SECTION_DIVIDER)
                    .append(t.getDetail()).append(SECTION_DIVIDER);
            if(!t.getId().equals("[T]")) {
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
