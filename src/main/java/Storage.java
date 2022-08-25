import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

public class Storage {
    private static File file;
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        file = new File("src/main/java/duke.txt");

        FileWriter fw = new FileWriter(file);

        for (Task task: tasks) {
            fw.write(task.toString() + "\n");
        }
        System.out.println("Auto-saved.");
        fw.close();
    }
    public static ArrayList<Task> loadFile() throws IOException {
        file = new File("src/main/java/duke.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/duke.txt"));
        String str;
        ArrayList<Task> loadedList = new ArrayList<>();

        while((str = reader.readLine()) != null) {
            if(str.charAt(1) == 'T') {
                String task = str.substring(2);
                todo input = new todo(task);
                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
            else if(str.charAt(1) == 'D') {
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                deadline input = new deadline(taskName);

                SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat from = new SimpleDateFormat("MMM dd yyyy");
                try {
                    Date date = from.parse(str.substring(openBracket + 5, closeBracket));
                    String result = to.format(date);
                    input.setDate(new formatDate(result));
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format!");
                }

                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
            else if(str.charAt(1) == 'E') {
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                event input = new event(taskName);

                SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat from = new SimpleDateFormat("MMM dd yyyy");
                try {
                    Date date = from.parse(str.substring(openBracket + 5, closeBracket));
                    String result = to.format(date);
                    input.setDay(new formatDate(result));
                } catch (ParseException e) {
                    System.out.println("Invalid Date Format!");
                }

                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
        }
        return loadedList;
    }
}
