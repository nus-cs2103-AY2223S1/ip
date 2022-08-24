package duke;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.util.function.Consumer;

/**
 * A class that encapsulates the SavedTaskHandler object
 * which deals with loading tasks from the file and saving tasks in the file
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class SavedTaskHandler {

    private final String filePath = "./data/saved.txt";
    private final File file;
    private final Path path;
    private final String EMPTY_TASKS = "Go ahead, your first task, tell LUNA and she will write it down...";
    private final TaskList taskList;

    SavedTaskHandler() throws IOException, ParseException {

        this.file = new File(filePath);
        String dir = System.getProperty("user.dir");
        this.path = Paths.get(dir, filePath);
        this.taskList = new TaskList();
        if (Files.exists(path)) {
            System.out.println("LUNA still has your previous tasks written on this fine crater...");
            Scanner scanner =  new Scanner(file);

            if (!scanner.hasNextLine()) {
                System.out.println(EMPTY_TASKS);
            } else {
                System.out.println("These were your previous tasks");

                while (scanner.hasNextLine()) {
                    String nextTaskStr = scanner.nextLine();
                    String[] strArr = nextTaskStr.split("] ");
                    String str = strArr[1];
                    if (nextTaskStr.contains("[T]")) {
                        ToDos todo = new ToDos(" " + str);
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
                            taskList.add(deadline);
                        } else {
                            Events events = new Events(" " + task, date);
                            taskList.add(events);
                        }
                    }

                    System.out.println(nextTaskStr);
                }
            }

        } else {
            System.out.println("You must be new here... Worry not, LUNA has saved you her finest crater...\n" + EMPTY_TASKS);

                file.getParentFile().mkdirs();
                file.createNewFile();

        }
    }

    /**
     * Returns the TaskList object encapsulated in this SavedTaskHandler object
     * @return TaskList obejct
     *
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Writes the Tasks inside the inputted TaskList into a .txt file
     * @param taskList The tasklist object to be written into a .txt file
     *
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