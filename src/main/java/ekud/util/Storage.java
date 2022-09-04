package ekud.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ekud.exception.EkudException;
import ekud.notes.Note;
import ekud.task.Task;
import ekud.task.Event;
import ekud.task.ToDo;
import ekud.task.Deadline;

public class Storage {
    private File taskfile;
    private File notefile;

    private void createDirectoryIfNotExists() {
        String folderPath = "./data";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private void createTaskFileIfNotExists() {
        String filePath = "./data/tasks.txt";
        File tasks = new File(filePath);
        if (!tasks.exists()) {
            try {
                tasks.createNewFile();
            } catch (IOException exception) {
                System.out.printf("Failed to create file: %s\n", exception.toString());
            }
        }
        this.taskfile = tasks;
    }

    private void createNoteFileIfNotExists() {
        String filePath = "./data/notes.txt";
        File notes = new File(filePath);
        if (!notes.exists()) {
            try {
                notes.createNewFile();
            } catch (IOException exception) {
                System.out.printf("Failed to create file: %s\n", exception.toString());
            }
        }
        this.notefile = notes;
    }

    private Task decodeStringToTask(String string) throws EkudException {
        String[] parts = string.split("\\|");
        Task task = null;
        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "E":
            task = new Event(parts[2], parts[3]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        default:
            throw new EkudException("Invalid txt file - all lines should begin with T,E or D");
        }
        assert task != null;
        if (parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Writes tasks to local txt file.
     * 
     * @param tasks Task list containing tasks to be written.
     */
    public void writeTasksToFile(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.taskfile);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(String.format("%s\n", tasks.get(i).getFileFormat()));
            }
            writer.close();
        } catch (IOException exception) {
            System.out.printf("Error occured when writing to file: %s\n", exception.toString());
        }
    }

    /**
     * Writes notes to local txt file.
     *
     * @param notes Note list containing notes to be written.
     */
    public void writeNotesToFile(List<Note> notes) {
        try {
            FileWriter writer = new FileWriter(this.notefile);
            for (int i = 0; i < notes.size(); i++) {
                writer.write(notes.get(i).toString());
            }
            writer.close();
        } catch (IOException exception) {
            System.out.printf("Error occured when writing to file: %s\n", exception.toString());
        }
    }

    /**
     * Gets task list from local txt file.
     * 
     * @return List of tasks contained in local txt file.
     * @throws EkudException Error that occurred.
     */
    public List<Task> getTasksFromFile() throws EkudException {
        List<Task> tasks = new ArrayList<Task>();
        try {
            Scanner reader = new Scanner(this.taskfile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                tasks.add(decodeStringToTask(line));
            }
            reader.close();
        } catch (IOException exception) {
            throw new EkudException(String.format("Error occured when writing to file: %s\n", exception.toString()));
        } catch (EkudException exception) {
            throw new EkudException(
                    "There was an error in the date format on the file - Please don't edit the file yourself.");
        }
        return tasks;
    }

    /**
     * Gets note list from local txt file.
     *
     * @return List of notes contained in local txt file.
     * @throws EkudException Error that occurred.
     */
    public List<Note> getNotesFromFile() throws EkudException {
        List<Note> notes = new ArrayList<Note>();
        try {
            Scanner reader = new Scanner(this.notefile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                notes.add(new Note(line));
            }
            reader.close();
        } catch (IOException exception) {
            throw new EkudException(String.format("Error occured when writing to file: %s\n", exception.toString()));
        }
        return notes;
    }

    /**
     * Constructor that instantiates an instance of Storage.
     */
    public Storage() {
        this.createDirectoryIfNotExists();
        this.createTaskFileIfNotExists();
        this.createNoteFileIfNotExists();
    }
}
