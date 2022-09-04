import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDecoder {
    static TaskList decodeFile(File dataFile) throws FileNotFoundException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Scanner fileReader = new Scanner(dataFile);
        decodeLine(taskList, fileReader);
        return taskList;
    }

    private static void decodeLine(TaskList taskList, Scanner fileReader) {
        while (fileReader.hasNextLine()) {
            fileReader.useDelimiter(" \\| ");
            UserCommandList command = UserCommandList.valueOf(fileReader.next().toUpperCase());
            Task task;
            switch (command) {
            case TODO:
                String toDoDescription = fileReader.next();
                task = new ToDo(toDoDescription);
                taskList.add(task);
                break;
            case DEADLINE:
                String deadlineDescription = fileReader.next();
                String deadlineBy = fileReader.next();
                task = new Deadline(deadlineDescription, deadlineBy);
                taskList.add(task);
                break;
            case EVENT:
                String eventDescription = fileReader.next();
                String eventAt = fileReader.next();
                task = new Event(eventDescription, eventAt);
                taskList.add(task);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
            }
            String isDone = fileReader.reset().skip(" \\| ").nextLine();
            if (isDone.equals("true")) {
                task.markAsDoneWithoutMessage();
            }
        }
    }
}
