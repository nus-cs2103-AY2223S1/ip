package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class represents storage object which interacts
 * with the task data stored in the txt file.
 */
public class Storage {

    /**
     * Reads the txt file from Info/data.txt and returns
     * an ArrayList of all the tasks.
     *
     * @return An ArrayList of the tasks read from the file.
     * @throws FileNotFoundException If the txt file does not exist.
     * @throws IOException If the file cannot be found by scanner or I/O error occurred.
     */
    public ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> botArray  = new ArrayList<>();
        File dataDirectory = new File("Info");
        if (!dataDirectory.isDirectory()) {
            dataDirectory.mkdir();
        }
        File dataFile = new File("Info/data.txt");
        dataFile.createNewFile();
        Scanner scanningFile = new Scanner(dataFile);
        while (scanningFile.hasNext()) {
            String currLine = scanningFile.nextLine();
            String[] task = currLine.split(" # ");
            if (task.length == 3) {
                ToDo todo = new ToDo(task[2]);
                boolean isMarked = task[1].equals("T");
                todo.setCompleted(isMarked);
                botArray.add(todo);
            }
            if (task.length == 4) {
                if (task[0].equals("D")) {
                    LocalDate currDate = LocalDate.parse(task[3]);
                    Deadline deadline = new Deadline(task[2],currDate);
                    boolean isMarked = task[1].equals("T");
                    deadline.setCompleted(isMarked);
                    botArray.add(deadline);
                }
                if (task[0].equals("E")) {
                    LocalDate currDate = LocalDate.parse(task[3]);
                    Event event = new Event(task[2],currDate);
                    boolean isMarked = task[1].equals("T");
                    event.setCompleted(isMarked);
                    botArray.add(event);
                }
            }
        }
        scanningFile.close();
        return botArray;
    }

    /**
     * Saves all the tasks to the txt file.
     *
     * @param isDeleted Whether the file should be deleted.
     * @param taskList The list of tasks.
     */
    public void saveToFile(boolean isDeleted, ArrayList<Task> taskList) {
        try {
            File dataFile = new File("Info/data.txt");
            if (isDeleted) {
                dataFile.delete();
                dataFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(dataFile, true);
            for (int i = 0; i < taskList.size(); i++) {
                if (!isDeleted && i != taskList.size() - 1) {
                    continue;
                }
                String currLine = taskList.get(i).stringFormatting();
                fileWriter.write(currLine + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Please check that parent file is created!");
        }
    }

}
