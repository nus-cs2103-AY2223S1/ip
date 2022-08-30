package pluto;

import pluto.task.Deadline;
import pluto.task.Event;
import pluto.task.Task;
import pluto.task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String path;

    public Storage(String path) throws PlutoException {
        try {
            this.file = new File(path);
            this.path = path;
            file.createNewFile();
        } catch (IOException e) {
            throw new PlutoException("\tOOPS!!! " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws PlutoException {
        ArrayList<Task> missions = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String text = sc.nextLine().strip();
                String[] textArr = text.split(" | ", 7);
                String command = textArr[0];
                switch (command) {
                    case "T":
                        Task todo = new Todo(textArr[4]);
                        markTasks(todo, textArr[2]);
                        missions.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(textArr[4], Parser.parseDate(textArr[6]));
                        markTasks(deadline, textArr[2]);
                        missions.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(textArr[4], Parser.parseDate(textArr[6]));
                        markTasks(event, textArr[2]);
                        missions.add(event);
                        break;
                }
            }
        } catch (IOException e) {
            throw new PlutoException("\tOOPS!!! " + e.getMessage());
        }
        return missions;
    }

    public void appendToFile(String str) throws IOException {
        FileWriter fwriter = new FileWriter(this.path, true);
        fwriter.write(str);
        fwriter.close();
    }

    private void markTasks(Task t, String status) {
        if (Integer.parseInt(status) == 1) {
            t.markAsDone();
        }
    }

    public void rewriteFile(TaskList tasks) throws IOException, PlutoException {
        FileWriter fwriter = new FileWriter(this.path);
        for (int i = 0; i < tasks.nTasks(); i++) {
            fwriter.write(tasks.getTask(i).toFile() + "\n");
        }
        fwriter.close();
    }


}
