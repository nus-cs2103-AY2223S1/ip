package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import filedata.FileData;
import task.Task;
import task.TaskList;
import task.NotesList;

public class Storage {
    protected String filePath;
    protected FileData fileData;
    protected TaskList tasksAndNotes;
    protected TaskList tasks;
    protected NotesList notes;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileData = new FileData(filePath);
        this.tasksAndNotes = new TaskList(this.fileData.storeArray());
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        this.fileData.toPrint();
        this.tasks = new TaskList();
        for (int i = 0; i < this.tasksAndNotes.size(); i++) {
            Task currTask = this.tasksAndNotes.get(i);
            char currTaskType = currTask.toString().charAt(1);
            if (currTaskType != 'N') {
                this.tasks.add(currTask);
            }
        }
        return tasks.toArray();
    }

    public ArrayList<Task> loadNotes() throws DukeException {
        this.notes = new NotesList();
        for (int i = 0; i < this.tasksAndNotes.size(); i++) {
            Task currTask = this.tasksAndNotes.get(i);
            char currTaskType = currTask.toString().charAt(1);
            if (currTaskType == 'N') {
                this.notes.add(currTask);
            }
        }
        return notes.toArray();
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void storeData(String textToStore) {
        String file = this.filePath;
        try {
            appendToFile(file, textToStore + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void updateData(TaskList taskList, NotesList notesList) {
        ArrayList<Task> taskListArray = taskList.toArray();
        ArrayList<Task> notesListArray = notesList.toArray();
        fileData.updateData(taskListArray, notesListArray);
    }

    public void save() throws DukeException {
        if (!this.fileData.exists()) {
            throw new DukeException("The file does not exist!");
        } else {
            assert this.fileData.exists();
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    if (i == 0) {
                        writeToFile(this.filePath, tasks.get(i).toString() + System.lineSeparator());
                    } else {
                        storeData(tasks.get(i).toString());
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }

}
