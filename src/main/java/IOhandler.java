import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Scanner;
public class IOhandler {

    public static void readFile(List<Task> taskList) throws IOException {
        File dir = new File("data");
        File myFile = new File(dir, "taskList.txt");
        if (!myFile.createNewFile()) {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                int divisor = next.lastIndexOf("|");

                if (next.charAt(0) == 'D') {
                    String description = next.substring(8, divisor - 1);
                    String date = next.substring((divisor + 2));
                    taskList.add(new Deadline(description, date));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }

                }
                if (next.charAt(0) == 'E') {
                    String description = next.substring(8, divisor - 1);
                    String date = next.substring((divisor + 2));
                    taskList.add(new Event(description, date));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }
                }
                if (next.charAt(0) == 'T') {
                    String description = next.substring(8);
                    taskList.add(new ToDo(description));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }
                }
            }
        }
    }
}
