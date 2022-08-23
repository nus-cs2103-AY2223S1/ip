package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File dirFile;
    private File dataFile;

    public Storage(String dirPath, String dataName) {
        this.dirFile = new File(dirPath);
        this.dataFile = new File(dirPath + dataName);
    }

    public void saveFile(TaskList t) {
        try {
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }

            FileWriter fw = new FileWriter(dataFile);

            for (Task task : t.getTasks()) {
                fw.write(task.toSaveData() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("IO Exception occurred, error saving file");

        }
    }

    public void loadFile(TaskList t) {
        try {
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    loadFileHelper(t, s.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception occurred, error loading file");
        }

    }

    public void loadFileHelper(TaskList t, String info) {
        String[] parts = info.split(" \\| ");
        if (parts[0].equals("T")) {
            Task todo = new Todo(parts[2]);
            int a = Integer.parseInt(parts[1]);
            if (a == 1) {
                todo.markDone();
            }
            t.addTask(todo);

        } else if (parts[0].equals("D")) {
            Task deadline = new Deadline(parts[2], parts[3]);
            int b = Integer.parseInt(parts[1]);
            if (b == 1) {
                deadline.markDone();
            }
            t.addTask(deadline);
        } else if (parts[0].equals("E")) {
            Task event = new Event(parts[2], parts[3]);
            int c = Integer.parseInt(parts[1]);
            if (c == 1) {
                event.markDone();
            }
            t.addTask(event);

        }

    }
}
