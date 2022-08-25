import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {

    private File f;
    private static final String path = "src/main/data/userTasks.txt";

    public Storage() {
        f = new File(path);
    }

    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String sentence = sc.nextLine();
                String[] splitted = sentence.split("\\|");
                Task t;
                if (splitted[1] != null) {
                    if (splitted[1].startsWith("todo")) {
                        t = addTodo(splitted[1]);
                    } else if (splitted[1].startsWith("deadline")) {
                        t = addDeadline(splitted[1]);
                    } else if (splitted[1].startsWith("event")) {
                        t = addEvent(splitted[1]);
                    } else {
                        t = new Task("dummy");
                    }
                } else {
                    t = new Task("");
                }
                if (splitted[0].equals("M")) {
                    t.markAsDone();
                } else if (splitted[0].equals("N")){
                    t.markAsNotDone();
                }

                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {

        }
         return tasks;
    }

    public void writeToFile(ArrayList<? extends Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).toStringForFile();
            str += s + System.lineSeparator();
        }
        try (BufferedWriter bf = Files.newBufferedWriter(Path.of(path),
                StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
            fw.append(str);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task addTodo(String msg) throws DukeException {

        String description = "";
        String[] splitted = msg.split("\\s+");

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            description += splitted[i] + " ";
        }

        Task task = new Todo(description.trim());
        return task;
    }

    public Task addDeadline(String msg) throws DukeException {
        String description = "";
        String by = "";

        String[] splitted = msg.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].equals("/by")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                by = by + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("deadline", "/by");
        }

        Task task = new Deadline(description.trim(), by.trim());
        return task;
    }

    public Task addEvent(String msg) throws DukeException {
        String description = "";
        String at = "";

        String[] splitted = msg.split("\\s+");

        boolean isSplitterFound = false;

        if (splitted.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        for (int i = 1; i < splitted.length; i++) {
            if (splitted[i].equals("/at")) {
                isSplitterFound = true;
            } else if (isSplitterFound) {
                at = at + splitted[i] + " ";
            } else {
                description = description + splitted[i] + " ";
            }
        }

        if (!isSplitterFound) {
            throw new DukeInvalidFormatException("event", "/at");
        }

        Task task = new Event(description.trim(), at.trim());
        return task;
    }
}