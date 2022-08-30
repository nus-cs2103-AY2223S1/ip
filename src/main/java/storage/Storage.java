package storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DorisException;

import parser.Parser;

import tasklist.Task;
import tasklist.TaskList;

public class Storage {

    private final File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DorisException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNextLine()) {
                Task task = Parser.parseSaved(sc.nextLine());
                tasks.add(task);
            }
            Task task = Parser.parseSaved(sc.nextLine());
            tasks.add(task);
        } catch (FileNotFoundException e) {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();
        } finally {
            return tasks;
        }
    }

    public void save(TaskList tasks) throws DorisException {
        try {
            File taskFile = new File(String.valueOf(storageFile));
            PrintWriter taskFileWriter = new PrintWriter(new FileWriter(taskFile));
            for (int i = 0; i < TaskList.size(); i++) {
                taskFileWriter.write(TaskList.get(i).toStringText() + "\n");
            }
            taskFileWriter.close();
        } catch (IOException e) {
            throw new DorisException(e.toString());
        }
    }
}
