package arc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a Storage that saves tasks
 */
public class Storage {
    private File tasksFile;
    private File notesFile;

    /**
     * Constructor for Storage
     * @param relativePathForTasks Relative path of txt datafile
     * @param relativePathForNotes Relative path of txt datafile
     */
    public Storage(String relativePathForTasks, String relativePathForNotes) {
        this.tasksFile = new File(relativePathForTasks);
        this.notesFile = new File(relativePathForNotes);
    }

    /**
     * Loads tasks from the datafile to TaskList
     * @return An ArrayList containing tasks read from the datafile
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readTaskFile(tasks);
        } catch (FileNotFoundException e) {
            createFile(tasksFile);
        }

        return tasks;
    }

    /**
     * Loads notes from the datafile to NoteList
     * @return An ArrayList containing notes read from the datafile
     */
    public ArrayList<Note> loadNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        try {
            readNoteFile(notes);
        } catch (FileNotFoundException e) {
            createFile(notesFile);
        }

        return notes;
    }

    private void createFile(File dataFile) {
        dataFile.getParentFile().mkdir();
        try {
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("File not successfully created");
        }
    }

    private void readTaskFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(this.tasksFile);

        while (sc.hasNextLine()) {
            String[] currTask = sc.nextLine().split("\\|");
            String taskType = currTask[0];
            String isDone = currTask[1];
            String title = currTask[2];

            switch (taskType) {
            case "T":
                tasks.add(new Todo(title, isDoneToBoolean(isDone)));
                break;
            case "D":
                String deadline = currTask[3];
                LocalDate ldt = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tasks.add(new Deadline(title, isDoneToBoolean(isDone), ldt));
                break;
            case "E":
                String time = currTask[3];
                tasks.add(new Event(title, isDoneToBoolean(isDone), time));
                break;
            default:
                System.out.println("Undefined task type: " + taskType);
            }
        }

        sc.close();
    }

    private boolean isDoneToBoolean(String isDone) {
        return isDone.equals("1");
    }

    private void readNoteFile(ArrayList<Note> notes) throws FileNotFoundException {
        Scanner sc = new Scanner(this.notesFile);

        while (sc.hasNextLine()) {
            String[] currTask = sc.nextLine().split("\\|");
            String title = currTask[0];
            String description = currTask.length > 1 ? currTask[1] : "";
            notes.add(new Note(title, description));
        }

        sc.close();
    }

    /**
     * Saves the tasks in TaskList on the datafile
     * @param taskList The TaskList whose data is to be saved
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void save(TaskList taskList) throws DukeException {
        ArrayList<String> newData = new ArrayList<>();

        for (int i = 0; i < taskList.numTasks(); i++) {
            newData.add(taskList.getTask(i).toFileFormat());
        }

        try {
            FileWriter fw = new FileWriter(this.tasksFile);
            fw.write(String.join("\n", newData));

            fw.close();
        } catch (IOException e) {
            throw new DukeException("File not saved properly");
        }
    }

    /**
     * Saves the notes in NoteList on the datafile
     * @param notes The NoteList whose data is to be saved
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void save(NoteList notes) throws DukeException {
        ArrayList<String> newData = new ArrayList<>();

        for (int i = 0; i < notes.numNotes(); i++) {
            newData.add(notes.getNote(i).toFileFormat());
        }

        try {
            FileWriter fw = new FileWriter(this.notesFile);
            fw.write(String.join("\n", newData));

            fw.close();
        } catch (IOException e) {
            throw new DukeException("File not saved properly");
        }
    }
}
