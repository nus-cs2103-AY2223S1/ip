import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static LocalDateTime dateParser(String dateTime) {
        String[] splitDateTime = dateTime.trim().split(" ");
        String[] dateArray = splitDateTime[0].split("/");
        String time = splitDateTime[1];

        return LocalDateTime.of(
                Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4))
        );
    }

    public static String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dt.format(formatter);
    }

    public static void listToFile(ArrayList<Task> tasklist) {
        try {
            FileWriter fw = new FileWriter("./data/dukedata.txt");
            for (Task t : tasklist) {
                fw.write(t.taskToFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
