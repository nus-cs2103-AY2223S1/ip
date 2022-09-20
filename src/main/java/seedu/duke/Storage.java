package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {
    private File save;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        // Make save file and parent directories if it does not exist.
        File saveFile = new File(filePath);
        saveFile.getParentFile().mkdirs();

        try {
            saveFile.createNewFile();
        }
        catch(IOException e) {
            e.getStackTrace();
            Ui.saveFileError();
        }

        this.save = saveFile;
    }

    /**
     * Initialises the TaskList storage with save data from the hard disk.
     *
     * @param tempStorage Where tasks are stored in the TaskList.
     */
    public void initialise(ArrayList<Task> tempStorage) {
        assert tempStorage != null : "No storage provided.";
        tempStorage.clear();

        try {
            Scanner s = new Scanner(save);
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] temp = task.split(" : ");
                Task newTask;

                if (temp[0].equals("T")) {
                    newTask = new Todo(temp[2]);

                    assert newTask != null : "Todo not created.";

                } else if (temp[0].equals("D")) {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

                    LocalDateTime dateTime = LocalDateTime.parse(temp[3], inputFormatter);

                    newTask = new Deadline(temp[2], dateTime.format(outputFormatter));
                    assert newTask != null : "Deadline not created.";

                } else if (temp[0].equals("E")) {
                    newTask = new Event(temp[2], temp[3]);
                    assert newTask != null : "Event not created.";

                } else if (temp[0].equals("F")) {
                    newTask = new FixedDurationTask(temp[2], temp[3]);
                    assert newTask != null : "FixedDurationTask not created.";

                } else {
                    throw new DukeException("");
                }

                if (temp[1].equals("1")) {
                    assert newTask != null : "No Task.";
                    newTask.markDone();
                }

                assert tempStorage != null : "No storage provided.";
                tempStorage.add(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Ui.saveFileError();
        } catch (DukeException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds tasks to the save file.
     * @param taskToAdd The task to be added to the save file.
     */
    public void addTask(String taskToAdd) {
        assert taskToAdd != null : "No Task description provided.";

        try {
            // create a FileWriter in append mode
            PrintWriter fw = new PrintWriter(new FileWriter(filePath, true));
            fw.println(taskToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Ui.unableToAdd();
        }
    }

    /**
     * Recreates the save file using the tasks in temporary storage.
     *
     * @param storage The temporary storage.
     */
    public void reload(ArrayList<Task> storage) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();

            Iterator<Task> iter = storage.iterator();
            while (iter.hasNext()) {
                this.addTask(iter.next().toStore());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
