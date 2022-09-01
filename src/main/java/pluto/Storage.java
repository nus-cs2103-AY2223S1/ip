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
                String[] textArr = text.split("\\s+\\|\\s+", 3);
                String command = textArr[0];
                int lastPipe = textArr[2].lastIndexOf("|");
                String date = null;
                String description = null;
                if (lastPipe != -1) {
                    date = textArr[2].substring(lastPipe + 1).strip();
                    description = textArr[2].substring(0, lastPipe).strip();
                }
                switch (command) {
                case "T":
                    Task todo = new Todo(textArr[2]);
                    markTasks(todo, textArr[1]);
                    missions.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(description, Parser.parseDate(date));
                    markTasks(deadline, textArr[1]);
                    missions.add(deadline);
                    break;
                case "E":
                    Task event = new Event(description, Parser.parseDate(date));
                    markTasks(event, textArr[1]);
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
