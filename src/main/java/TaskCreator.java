import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

            description = info.substring(0,indexOfSplit);
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

        case EVENT:
            indexOfSplit = info.indexOf("/at");
            if (indexOfSplit == -1) {
                return new ErrorTask("");
            }

            description = info.substring(0,indexOfSplit);
            String dateTimeEvent = info.substring(indexOfSplit + SIZEOFPREPOSITION);

            task = new Event(description, dateTimeEvent);
            break;
        }
        return task;
    }
}
