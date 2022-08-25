import java.io.*;
import java.util.ArrayList;

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
                int index = str.indexOf("/");
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                deadline input = new deadline(taskName);
                input.setDate(str.substring(openBracket + 5, closeBracket));
                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
            else if(str.charAt(1) == 'E') {
                int index = str.indexOf("/");
                int openBracket = str.indexOf('(');
                int closeBracket = str.indexOf(')');
                String taskName = str.substring(7, openBracket - 1);
                event input = new event(taskName);
                input.setDay(str.substring(openBracket + 5, closeBracket));
                if (str.charAt(4) == 'X') {
                    input.markAsDone();
                }
                loadedList.add(input);
            }
        }
        return loadedList;
    }
}
