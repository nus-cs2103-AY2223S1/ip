package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Storage {

    public Storage() {}

    public Path createSave() throws IOException {
        Path file = null;
        try {
            file = Paths.get(".", "data");
            if (!Files.exists(file)) {
                Files.createDirectory(file);
            }
            file = Paths.get(".", "data", "list.txt");
            if (!Files.exists(file)) {
                Files.createFile(file);
                System.out.println("Nice to meet you, Master. I have started a new list for you.");
            } else {
                System.out.println("Welcome back, Master!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void saveList(TaskList list, Path file) throws IOException {
        try {
            Files.write(file, list.toStringList());
            System.out.println("I have saved your list:\n" + list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadList(Path file) throws IOException {
        TaskList list = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            if (!scanner.hasNext()) {
                return list;
            }
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                boolean isDone = taskString.charAt(4) == 'X';
                switch (taskString.charAt(1)) {
                    case 'T': {
                        ToDo todo = new ToDo(taskString.substring(7, taskString.length()), isDone);
                        list.add(todo);
                        break;
                    }
                    case 'D': {
                        String[] taskDesc = taskString.substring(7, taskString.length() - 1).split(" \\(by: ");
                        Deadline deadline = new Deadline(taskDesc[0], taskDesc[1], isDone);
                        list.add(deadline);
                        break;
                    }
                    case 'E': {
                        String[] taskDesc = taskString.substring(7, taskString.length() - 1).split(" \\(on: ");
                        Event event = new Event(taskDesc[0], taskDesc[1], isDone);
                        list.add(event);
                        break;
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
