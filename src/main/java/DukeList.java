import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DukeList {
    private final String fileStrDivider = " | ";
    private final List<Task> listItems = new ArrayList<>();

    public DukeList() {

    }

    public void read() throws DukeException {
        Path dataPath = Paths.get( "data", "duke.txt");
        if (Files.exists(dataPath)) {
            // File and dir exists, read from file
            try {
                List<String> data = Files.readAllLines(dataPath);
                for (String taskStr : data) {
                    readTaskAndAdd(taskStr);
                }
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
            } catch (IOException e) {
                throw new DukeException("An error occurred while creating data file.");
            }
        }
    }

    private void readTaskAndAdd(String taskStr) {
        String[] data = taskStr.split("\\|", 3);

        DukeCommand command = DukeCommand.read(data[0].trim());
        int status = Integer.parseInt(data[1].trim());
        String desc = data[2].trim();
        String time = "";
        if (data[2].contains(this.fileStrDivider)) {
            // String contains more data
            int timeIndex = data[2].lastIndexOf(this.fileStrDivider);
            desc = desc.substring(0, timeIndex).trim();
            time = data[2].substring(timeIndex + this.fileStrDivider.length()).trim();
        }

        switch (command) {
        case TODO:
            // Add task as to do
            Todo t = new Todo(desc);
            if (status == 1) {
                t.markAsDone();
            }
            listItems.add(t);
            break;
        case DEADLINE:
            // Add task as deadline
            Deadline d = new Deadline(desc, time);
            if (status == 1) {
                d.markAsDone();
            }
            listItems.add(d);
            break;
        case EVENT:
            // Add task as event
            Event e = new Event(desc, time);
            if (status == 1) {
                e.markAsDone();
            }
            listItems.add(e);
            break;
        }
    }

    private String taskToString(Task t) {
        String taskType = t.getTaskType().toString().toLowerCase();
        String description = t.description;
        String isDone = t.isDone ? "1" : "0";
        String otherData = t.getOtherData();

        String taskStr = taskType + this.fileStrDivider + isDone + this.fileStrDivider + description;

        if (otherData.isEmpty()) {
            return taskStr;
        } else {
            return taskStr + this.fileStrDivider + otherData;
        }
    }

    public void write() throws DukeException {
        List<String> strListItems = listItems.stream().map(this::taskToString).collect(Collectors.toList());

        Path dataPath = Paths.get("data", "duke.txt");
        try {
            Files.write(dataPath, strListItems, Charset.defaultCharset());
        } catch (IOException e) {
            throw new DukeException("An error occurred while writing to data file.");
        }
    }

    public String add(Task item) {
        listItems.add(item);
        return "Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + listItems.size()
                + (listItems.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    public String done(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as done.");
        }

        Task t = listItems.get(listIndex);
        t.markAsDone();
        return "Nice! I've marked this task as done:\n" + t;
    }

    public String undone(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as undone.");
        }

        Task t = listItems.get(listIndex);
        t.markAsUndone();
        return "OK, I've marked this task as not done yet:\n" + t;
    }

    public String delete(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to delete.");
        }

        Task t = listItems.get(listIndex);
        listItems.remove(listIndex);
        return "Noted. I've removed this task:\n"
                + t
                + "\nNow you have "
                + listItems.size()
                + (listItems.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    @Override
    public String toString() {
        StringBuilder listItemsStrBuilder = new StringBuilder();
        listItemsStrBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < listItems.size(); i++) {
            listItemsStrBuilder.append(i + 1).append(". ").append(listItems.get(i));

            if (i != listItems.size() - 1) {
                listItemsStrBuilder.append("\n");
            }
        }
        return listItemsStrBuilder.toString();
    }
}