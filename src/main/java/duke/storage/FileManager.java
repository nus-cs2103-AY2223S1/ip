package duke.storage;

import static duke.common.Messages.DATE_TIME_FORMAT;
import static duke.common.Messages.DEADLINE_ID;
import static duke.common.Messages.EVENT_ID;
import static duke.common.Messages.TODO_ID;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with the saving and loading of TaskList data.
 * Contains two read and write static method to perform saving and loading operation.
 */

public class FileManager {

    private static final String home = System.getProperty("user.home");
    private static final java.nio.file.Path path = java.nio.file.Paths.get(home,
            "CS2103", "ip", "src", "main", "java", "data", "duke.txt");
    private static final String SECTION_DIVIDER = " __ ";
    private static final int ID_POS = 0;
    private static final int IS_DONE_POS = 1;
    private static final int DETAIL_POS = 2;
    private static final int DATE_TIME_POS = 3;

    /**
     * Reads the data of TaskRecords saved from the previous duke operation at the start of duke bot operation.
     *
     * @return TaskRecords of the saved task list information.
     * @throws FileNotFoundException - thrown if there is no data saved in the text file or the file does not exist.
     */
    public static TaskList read() throws FileNotFoundException {
        TaskList savedList = new TaskList();
        File previousCache = new File(path.toUri());
        Scanner scn = new Scanner(previousCache);
        while (scn.hasNextLine()) {
            String[] info = scn.nextLine().split(SECTION_DIVIDER);
            if (info.length < 3) {
                break;
            }
            String taskID = info[ID_POS];
            switch (taskID) {
            case TODO_ID:
                savedList.addTask(new ToDo(info[DETAIL_POS], Boolean.parseBoolean(info[IS_DONE_POS])));
                break;
            case EVENT_ID:
                savedList.addTask(new Event(info[DETAIL_POS], Boolean.parseBoolean(info[IS_DONE_POS]),
                    Parser.convertTime(info[DATE_TIME_POS])));
                break;
            case DEADLINE_ID:
                savedList.addTask(new Deadline(info[DETAIL_POS], Boolean.parseBoolean(info[IS_DONE_POS]),
                    Parser.convertTime(info[DATE_TIME_POS])));
                break;
            default:
                assert false : "Something when wrong while reading data!";
            }
        }
        return savedList;
    }

    /**
     * Writes the data of TaskRecords to text file whenever changes is made.
     *
     * @throws IOException - thrown if the writing process has fail.
     */
    public static void write(TaskList taskList) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task t : taskList.getList()) {
            content.append(t.getId()).append(SECTION_DIVIDER)
                    .append(t.isDone()).append(SECTION_DIVIDER)
                    .append(t.getDetail()).append(SECTION_DIVIDER);
            if (!t.getId().equals(TODO_ID)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                content.append(t.getTime().format(formatter)).append(SECTION_DIVIDER);
            }
            content.append("\n");
        }
        FileWriter writer = new FileWriter(path.toString());
        writer.write(content.toString());
        writer.close();
    }

}
