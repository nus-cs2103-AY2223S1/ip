package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.helper.DateTimeConverter;

public class TaskCreator {
    /**
     * Main class to create new Task
     */

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }
    private static int SIZEOFPREPOSITION = 4;

    public static Task CreateTask(String in) {
        Task task = null;
        Type type;

        if (!in.startsWith("todo") && !in.startsWith("deadline") && !in.startsWith("event")) {
            return task;
        }

        String[] inArr = in.split(" ", 2);
        if (inArr.length < 2) {
            return new Task("", "[]");
        }

        String info = inArr[1];

        if (in.startsWith("todo")) {
            type = Type.TODO;
        } else if (in.startsWith("deadline")) {
            type = Type.DEADLINE;
        } else {
            type = Type.EVENT;
        }

        int indexOfSplit;
        String description;

        switch (type) {
        case TODO:
            task = new Todo(info);
            break;

        case DEADLINE:
            indexOfSplit = info.indexOf("/by");
            if (indexOfSplit == -1) {
                return new ErrorTask("");
            }
            try {
                description = info.substring(0, indexOfSplit - 1);
                String[] dateTimeDeadline = info.substring(indexOfSplit + SIZEOFPREPOSITION).split(" ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-M-d");
                DateTimeConverter converter = new DateTimeConverter(formatter);
                String date;

                if (converter.isValidDate(dateTimeDeadline[0])) {
                    date = converter.convert(dateTimeDeadline);
                    task = new Deadline(description, date, LocalDate.parse(dateTimeDeadline[0], formatter));
                } else {
                    date = info.substring(indexOfSplit + SIZEOFPREPOSITION);
                    task = new Deadline(description, date, null);
                }

                break;
            }

            catch (StringIndexOutOfBoundsException e) {
                return new Task("","[]");
            }

        case EVENT:
            try {
                indexOfSplit = info.indexOf("/at");
                if (indexOfSplit == -1) {
                    return new ErrorTask("");
                }

                description = info.substring(0, indexOfSplit - 1);
                String dateTimeEvent = info.substring(indexOfSplit + SIZEOFPREPOSITION);

                task = new Event(description, dateTimeEvent);
                break;
            }

            catch (StringIndexOutOfBoundsException e) {
                return new Task("","[]");
            }
        }
        return task;
    }

    public static Task createFromStorage(String line) {
        String[] lineParts = line.split("--");
        String info = "";

        if (lineParts[0].equals("[T]")) {
            info += "todo";
            info += (" " + lineParts[2]);
        } else if (lineParts[0].equals("[D]")) {
            info += "deadline";
            info += (" " + lineParts[2] + " /by " + lineParts[3]);
        } else {
            info += "event";
            info += (" " + lineParts[2] + " /at " + lineParts[3]);
        }

        Task task = CreateTask(info);

        if (!lineParts[1].equals("[ ]")) {
            task.isDone = true;
        }

        return task;
    }
}
