import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void save(ArrayList<Task> taskArrayList) {
        try {
            File file = new File("data/duke.txt");
            File directory = new File("data");

            if (!directory.exists()) {
                directory.mkdir();
            }

            FileWriter myWriter = new FileWriter(file);
            for (Task t: taskArrayList) {
                myWriter.write(t.toStringFileFormat() + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Save issue.");
        }
    }
    public static ArrayList<Task> loadTask() throws IOException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            File taskFile = new File("data/duke.txt");
            Scanner sc = new Scanner(taskFile);

            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskStringInArray = taskString.split(" \\| ");
                String taskType = taskStringInArray[0];
                if (taskStringInArray.length > 1) {
                    boolean isDone = taskStringInArray[1].equals("1");
                    String taskDesription = taskStringInArray[2];
                    Task t;

                    switch (taskType) {
                    case "T":
                        t = new Todo(taskDesription);
                        break;

                    case "D":
                        if (taskStringInArray.length < 3) {
                            throw new DukeException("Deadline required.");
                        }
                        String deadline = taskStringInArray[3];
                        LocalDateTime deadlineDate = LocalDateTime.parse(deadline);
                        t = new Deadline(taskDesription, deadlineDate);
                        break;

                    case "E":
                        if (taskStringInArray.length < 3) {
                            throw new DukeException("Event time required.");
                        }
                        String eventTime = taskStringInArray[3];
                        LocalDateTime eventTimeInDate = LocalDateTime.parse(eventTime);
                        t = new Event(taskDesription, eventTimeInDate);
                        break;
                    default:
                        throw new DukeException("File error");
                    }


                    if (isDone) {
                        t.markAsDone();
                    }

                    taskArrayList.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        } catch (DateTimeParseException e) {
            System.out.println("Check date format.");
        }
        return taskArrayList;
    }

}
