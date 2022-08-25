package Sakura;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Task[] loadData() {
        File database = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                taskList.add(readEntry(entry));
            }
        } catch (FileNotFoundException e) {
            createDatabase();
        }
        return taskList.toArray(new Task[0]);
    }

    private Task readEntry(String entry) {
        String[] data = entry.split("\\|");
        Task dataTask = null;
        if (data[0].equals("T")) {
            dataTask = new Todo(data[2]);
        } else if (data[0].equals("D")) {
            dataTask = new Deadline(data[2], data[3]);
        } else if (data[0].equals("E")) {
            dataTask = new Event(data[2], data[3]);
        } else {
            SakuraException.databaseError();
        }
        if (Integer.parseInt(data[1]) == 1) {
            assert dataTask != null;
            dataTask.markDone();
        }
        return dataTask;
    }

    private void createDatabase() {
        File database = new File(this.filePath);
        File dir = new File(database.getParent());
        boolean wasSuccessful = dir.mkdir();
        if (wasSuccessful) {
            try {
                boolean fileCreated = database.createNewFile();
                if (!fileCreated) {
                    throw new IOException("Unable to create file at specified path. It already exists");
                }
            } catch (IOException e) {
                System.out.println("Error when creating the database!");
            }
        }
    }

    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 1; i <= taskList.getLength(); i++) {
            bw.write(taskList.get(i).stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

}
