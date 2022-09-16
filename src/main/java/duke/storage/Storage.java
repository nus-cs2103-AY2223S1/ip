package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Class containing static methods for loading and writing the task list to a storage text file.
 */
public class Storage {

    /**
     * Loads the stored tasks into the taskList for Duke to read.
     *
     * @param taskList taskList for the tasks in the file to be stored.
     * @param path Text Path to the text file where the tasks are stored.
     * @throws FileNotFoundException if there is no text file to be found.
     */
    public static void loadData(TaskList taskList, String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            boolean status = task.charAt(4) == '1';

            if (task.charAt(0) == 'T') {
                String[] split = task.substring(8).split(" \\| ", 2);
                String content = split[0];
                String tag = split[1];
                ToDo toDo = new ToDo(content);
                toDo.loadTag(tag);
                if (status) {
                    toDo.markComplete();
                }
                taskList.addTask(toDo);

            } else if (task.charAt(0) == 'D') {
                String[] split = task.substring(8).split(" \\| ", 3);
                String tag = split[2];
                String content = split[0];
                String[] dateTimeSplit = split[1].split(" ", 2);
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Deadline deadline = new Deadline(content, date, time);
                deadline.loadTag(tag);
                if (status) {
                    deadline.markComplete();
                }
                taskList.addTask(deadline);

            } else if (task.charAt(0) == 'E') {
                String[] split = task.substring(8).split(" \\| ", 3);
                String tag = split[2];
                String content = split[0];
                String[] dateTimeSplit = split[1].split(" ", 2);
                LocalDate date = LocalDate.parse(dateTimeSplit[0]);
                LocalTime time = LocalTime.parse(dateTimeSplit[1]);
                Event event = new Event(content, date, time);
                event.loadTag(tag);
                if (status) {
                    event.markComplete();
                }
                taskList.addTask(event);
            }

        }
        scanner.close();
    }

    /**
     * Writes the taskList into a file to be stored.
     *
     * @param taskList taskList containing the tasks to be stored.
     * @param path Path to the file where the tasks will be stored.
     * @throws IOException if there are errors in opening or writing to the file.
     */
    public static void writeData(TaskList taskList, String path) throws IOException {
        FileWriter clearFw = new FileWriter(path, false);
        clearFw.write("");
        clearFw.close();
        FileWriter fw = new FileWriter(path, true);
        List<String> writeList = taskList.produceWriteList();
        for (int i = 0; i < writeList.size(); i++) {
            fw.write(writeList.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

}
