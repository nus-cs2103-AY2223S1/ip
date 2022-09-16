package duke;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * A class that encapsulates the SavedTaskHandler object
 * which deals with loading tasks from the file and saving tasks in the file
 *
 * @author Wee Xin Yang, Markus
 * @version 0.1
 * @since 2022-8-24
 */
public class SavedTaskHandler {

    private final String filePath = "./data/saved.txt";
    private final File file;
    private final Path path;
    private final String EMPTY_TASKS = "Go ahead, your first task, tell LUNA and she will write it down...";
    private final String FILE_DOES_NOT_EXIST = "You must be new here... Worry not,"
            + "LUNA has saved you her finest crater...";
    private final String FILE_EXISTS = "LUNA still has your previous tasks written on this fine crater...\n";
    private final TaskList taskList;
    private boolean isFileExists = false;
    private String previousList = "";

    /**
     * Constructor for SavedTaskHandler Object
     */
    SavedTaskHandler() throws IOException, ParseException {

        this.file = new File(filePath);
        String dir = System.getProperty("user.dir");
        this.path = Paths.get(dir, filePath);
        this.taskList = new TaskList();
        if (Files.exists(path)) {
            this.isFileExists = true;
            Scanner scanner = new Scanner(file);
            processPreviousList(scanner);

        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();

        }
    }

    private void processPreviousList(Scanner scanner) throws ParseException {

        while (scanner.hasNextLine()) {
            String nextTaskStr = scanner.nextLine();
            String[] strArr = nextTaskStr.split("] ");
            String str = strArr[1];
            if (nextTaskStr.contains("[T]")) {
                ToDos todo = new ToDos(str);
                if (nextTaskStr.contains("[X]")) {
                    todo.mark();
                }

                if (nextTaskStr.contains("[H]")) {
                    todo.setPriority("[H]");
                }

                if (nextTaskStr.contains("[M]")) {
                    todo.setPriority("[M]");
                }

                if (nextTaskStr.contains("[L]")) {
                    todo.setPriority("[L]");
                }
                taskList.add(todo);
            } else {
                String[] findDate = str.split(":");
                String[] findDate2 = findDate[1].split("\\)");
                String[] findTask = str.split(" \\(");
                String date = findDate2[0];
                String task = findTask[0];

                if (nextTaskStr.contains("[D]")) {
                    SimpleDateFormat format1 = new SimpleDateFormat("MMM dd yyyy");
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = format1.parse(date);
                    String formattedDate = format2.format(date1);
                    Deadlines deadline = new Deadlines(" " + task, formattedDate);
                    if (nextTaskStr.contains("[X]")) {
                        deadline.mark();
                    }

                    if (nextTaskStr.contains("[H]")) {
                        deadline.setPriority("[H]");
                    }

                    if (nextTaskStr.contains("[M]")) {
                        deadline.setPriority("[M]");
                    }

                    if (nextTaskStr.contains("[L]")) {
                        deadline.setPriority("[L]");
                    }
                    taskList.add(deadline);
                } else {
                    Events events = new Events(" " + task, date);
                    if (nextTaskStr.contains("[X]")) {
                        events.mark();
                    }

                    if (nextTaskStr.contains("[H]")) {
                        events.setPriority("[H]");
                    }

                    if (nextTaskStr.contains("[M]")) {
                        events.setPriority("[M]");
                    }

                    if (nextTaskStr.contains("[L]")) {
                        events.setPriority("[L]");
                    }
                    taskList.add(events);
                }
            }
            this.previousList += nextTaskStr + "\n";
        }

    }

    /**
     * Returns the string representation of the previously saved TaskList
     *
     * @return a String representing the previously saved TaskList
     */
    public String getPreviousList() {
        return this.previousList;
    }

    /**
     * Returns the TaskList object encapsulated in this SavedTaskHandler object
     *
     * @return TaskList object
     */
    public TaskList getTaskList() {

        return this.taskList;
    }

    /**
     * Returns the String displaying a message on whether a previous list existed
     *
     * @return String message of whether a previous list existed
     */
    public String getFileMessage() {
        if (isFileExists) {
            return FILE_EXISTS;
        } else {
            return FILE_DOES_NOT_EXIST;
        }
    }

    /**
     * Writes the Tasks inside the inputted TaskList into a .txt file
     *
     * @param taskList The tasklist object to be written into a .txt file
     */
    public void write(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            Consumer<? super Task> consumer = x -> {
                try {
                    writer.write(x + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            taskList.forEach(consumer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}