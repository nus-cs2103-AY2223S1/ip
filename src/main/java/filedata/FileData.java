package filedata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Notes;

public class FileData {
    protected String fileName;
    protected File file;
    protected ArrayList<Task> tasks;


    public FileData(String fileName) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Desktop", fileName);
        this.fileName = String.valueOf(path);
        this.file = new File(String.valueOf(path));
        this.tasks = this.storeArray();
    }

    public boolean exists() {
        return this.file.exists();
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

    public void updateData(ArrayList<Task> tasks, ArrayList<Task> notes) {
        try {
            assert !tasks.isEmpty();
            assert !notes.isEmpty();
            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0) {
                    writeToFile(this.fileName, tasks.get(i).toString() + System.lineSeparator());
                }

                if (i > 0) {
                    storeData(tasks.get(i).toString());
                }
            }

            for (int j = 0; j < notes.size(); j++) {
                if (tasks.isEmpty()) {
                    writeToFile(this.fileName, notes.get(j).toString() + System.lineSeparator());
                } else {
                    storeData(notes.get(j).toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> allTasks() {
        return this.tasks;
    }

    public ArrayList<Task> storeArray() {
        ArrayList<Task> tasksAndNotes = new ArrayList<>(100);
        try { 
            if (!this.file.exists()) {
                throw new FileNotFoundException();
            } else {
                assert this.file.exists();
                Scanner s = new Scanner(this.file);
                while (s.hasNext()) {
                    String currTask = s.nextLine();
                    char currType = currTask.charAt(1);
                    if (currType == 'T') {
                        tasksAndNotes.add(new Todo(currTask.substring(7)));
                    } else if (currType == 'D') {
                        int bracketIndex = currTask.indexOf("(");
                        String taskDate = currTask.substring(bracketIndex + 4);
                        tasksAndNotes.add(new Deadline(currTask.substring(7, bracketIndex - 1), taskDate));
                    } else if (currType == 'E') {
                        int bracketIndex = currTask.indexOf("(");
                        String taskDate = currTask.substring(bracketIndex + 4);
                        tasksAndNotes.add(new Event(currTask.substring(7, bracketIndex - 1), taskDate));
                    } else {
                        tasksAndNotes.add(new Notes(currTask.substring(7)));
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println();
        }
        return tasksAndNotes;
    }

    public void storeData(String textToStore) {
        String file = this.fileName;
        try {
            appendToFile(file, textToStore + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void toPrint() {
        try {
            if (!this.file.exists()) {
                throw new FileNotFoundException();
            } else {
                assert this.file.exists();
                Scanner d = new Scanner(this.file);
                while (d.hasNext()) {
                    System.out.println(d.nextLine());
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println();
        }
    }


    public static void main(String[] args) {
        File f = new File("data/duke.txt");
        File f1 = new File("data/tempDuke.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("full path: " + f1.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

}
