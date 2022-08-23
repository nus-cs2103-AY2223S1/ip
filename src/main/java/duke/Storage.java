package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final String fileStrDivider = " | ";
    private final Path dataPath;

    public Storage(Path dataPath) {
        this.dataPath = dataPath;
    }

    public List<Task> read() throws DukeException {
        if (Files.exists(this.dataPath)) {
            // File and dir exists, read from file
            try {
                List<String> data = Files.readAllLines(dataPath);
                List<Task> listItems = new ArrayList<>();
                for (String taskStr : data) {
                    listItems.add(readTask(taskStr));
                }
                return listItems;
            } catch (IOException e) {
                throw new DukeException("An error occurred while reading data.");
            }
        } else {
            // File does not exist
            if (Files.notExists(dataPath.getParent())) {
                // Dir does not exist, create dir
                try {
                    Files.createDirectory(dataPath.getParent());
                } catch (IOException e) {
                    throw new DukeException("An error occurred while creating directory.");
                }
            }

            try {
                // Create file
                Files.createFile(dataPath);
                return new ArrayList<>();
            } catch (IOException e) {
                throw new DukeException("An error occurred while creating data file.");
            }
        }
    }

    private Task readTask(String taskStr) throws DukeException {
        String[] data = taskStr.split("\\|", 3);

        DukeCommand command = DukeCommand.read(data[0].trim());
        int status = Integer.parseInt(data[1].trim());
        String desc = data[2].trim();
        String time = "";
        if (data[2].contains(fileStrDivider)) {
            // String contains more data
            int timeIndex = data[2].lastIndexOf(fileStrDivider);
            desc = desc.substring(0, timeIndex).trim();
            time = data[2].substring(timeIndex + fileStrDivider.length()).trim();
        }

        switch (command) {
        case TODO:
            // Add duke.task as to do
            Todo t = new Todo(desc);
            if (status == 1) {
                t.markAsDone();
            }
            return t;
        case DEADLINE:
            // Add duke.task as deadline
            Deadline d = new Deadline(desc, Parser.strToDateFromStorage(time));
            if (status == 1) {
                d.markAsDone();
            }
            return d;
        case EVENT:
            // Add duke.task as event
            Event e = new Event(desc, Parser.strToDateFromStorage(time));
            if (status == 1) {
                e.markAsDone();
            }
            return e;
        default:
            throw new DukeException("Task cannot be read from data");
        }
    }

    public void write(DukeList list) throws DukeException {
        try {
            Files.write(this.dataPath, list.getListToDataStr(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to data file.");
        }
    }
}
