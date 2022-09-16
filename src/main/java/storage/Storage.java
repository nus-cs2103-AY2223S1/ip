package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.FredException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.Ui;

/**
 * Storage lets Fred save and load from a data file to keep data between sessions
 */
public class Storage {

    protected String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Save tasks in list into data file on hard drive.
     * @param taskList taskList from the program.
     * @param ui Ui that interacts with the user.
     * @throws FredException
     */
    public void save(ArrayList<Task> taskList, Ui ui) throws FredException {

        try {
            File f = new File(filePath);

            if (!f.exists()) {
                f.createNewFile();
                ui.storeMessage("No data file exists. New data file has been created.\n");
            }
            assert (f.exists());

            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.getSaveFormat());
            }
            fileWriter.close();
            ui.storeMessage("Tasks have been saved!\n");
        } catch (IOException e) {
            throw new FredException("IOException!");
        }
    }

    /**
     * Load tasks into list from data file on hard drive.
     * @throws FredException
     */
    public ArrayList<Task> load() throws FredException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String entry = scanner.nextLine();
                char firstLetter = entry.charAt(0);

                if (firstLetter == 'T') {
                    loadTodo(taskList, entry);
                } else if (firstLetter == 'E') {
                    loadEvent(taskList, entry);
                } else if (firstLetter == 'D') {
                    loadDeadline(taskList, entry);
                } else {
                    assert(!(firstLetter == 'T') && !(firstLetter == 'T') && !(firstLetter == 'T'));
                    throw new FredException("Loading... Data file entry is wrong!");
                }
            }
            scanner.close();
            return taskList;

        } catch (FileNotFoundException e) {
            throw new FredException("Loading Error!");
        }
    }

    /**
     * Load Todo into taskList
     * @param taskList Fred's tasklist
     * @param entry entry in data file
     */
    private void loadTodo(ArrayList<Task> taskList, String entry) {
        String[] entryParts = entry.split(" \\| ");
        String isDoneSymbol = entryParts[1].trim();
        boolean isDone = isDoneSymbol.equals("1");
        String description = entryParts[2].trim();
        Task todo = new ToDo(description, isDone);

        taskList.add(todo);
    }

    /**
     * Load Event into taskList
     * @param taskList Fred's tasklist
     * @param entry entry in data file
     */
    private void loadEvent(ArrayList<Task> taskList, String entry) {
        String[] entryParts = entry.split(" \\| ");
        String isDoneSymbol = entryParts[1].trim();
        boolean isDone = isDoneSymbol.equals("1");
        String description = entryParts[2].trim();
        String dateString = entryParts[3].trim();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        Task event = new Event(description, date, isDone);

        taskList.add(event);
    }

    /**
     * Load Deadline into taskList
     * @param taskList Fred's tasklist
     * @param entry entry in data file
     */
    private void loadDeadline(ArrayList<Task> taskList, String entry) {
        String[] entryParts = entry.split(" \\| ");
        String isDoneSymbol = entryParts[1].trim();
        boolean isDone = isDoneSymbol.equals("1");
        String description = entryParts[2].trim();
        String dateString = entryParts[3].trim();

        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        Task deadline = new Deadline(description, date, isDone);

        taskList.add(deadline);
    }
}
