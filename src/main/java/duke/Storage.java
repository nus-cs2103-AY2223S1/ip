package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage of data from the user specified relative file path.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private boolean isMarked(String s) {
        return s.equals("X");
    }

    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }


    /**
     * Loads tasks from the data file if the file path exists.
     *
     * @return List of tasks that was saved previously.
     * @throws IOException If there is an issue with accessing the file.
     * @throws DukeException If Duke is unable to process the contents of the file as they are invalid data.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File file = new File(filePath);
        boolean hasFile = file.exists();

        if (!hasFile) {
            this.createFile(file);
            String errorMessage = "There was an error loading your file. Starting a new list...\n";
            throw new DukeException(errorMessage);
        } else {
            System.out.println("Saved tasks retrieved!\n");
            ArrayList<Task> tasks = new ArrayList<>(100);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                Scanner temp = new Scanner(text);
                String filter = "\\[|\\]\\s*|by:\\s*|at:\\s*|\\s*\\(|\\s*\\)";
                temp.useDelimiter(filter);

                String taskType = temp.next();
                temp.next();
                String markStatus = temp.next();
                String description = temp.next();
                boolean isTodo = taskType.equals("T");
                boolean isDeadline = taskType.equals("D");
                boolean isEvent = taskType.equals("E");

                DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter newDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
                String date;
                String formattedDate;

                switch (taskType) {

                case "D":
                    assert (isDeadline);
                    temp.next();
                    date = temp.next();
                    formattedDate = inputDateFormat.format(newDateFormat.parse(date));
                    Deadline deadline = new Deadline(description, formattedDate);
                    if (isMarked(markStatus)) {
                        deadline.mark();
                    }
                    tasks.add(deadline);
                    break;

                case "E":
                    assert (isEvent);
                    temp.next();
                    date = temp.next();
                    formattedDate = inputDateFormat.format(newDateFormat.parse(date));
                    Event event = new Event(description, formattedDate);
                    if (isMarked(markStatus)) {
                        event.mark();
                    }
                    tasks.add(event);
                    break;

                case "T":
                    assert (isTodo);
                    Todo todo = new Todo(description);
                    if (isMarked(markStatus)) {
                        todo.mark();
                    }
                    tasks.add(todo);
                    break;

                default:
                    throw new DukeException("Invalid or no input read.");
                }
            }
            return tasks;
        }
    }

    /**
     * Saves the list of tasks from the current run of the Duke program.
     *
     * @param taskArrayList List of tasks to be saved.
     * @throws IOException If there is an issue with writing into the file.
     */
    public void save(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskArrayList) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

}
