import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath;
    private File file;
    private FileWriter fw;
    private static ArrayList<Task> inputList;


    public Storage (String filePath) {
        this.filePath = filePath;
        this.inputList = new ArrayList<>();
        this.file = new File(filePath);
    }

    public static void writeToFile(ArrayList<Task> textToAdd) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File does not exist.");
        }

        FileWriter fw = new FileWriter(filePath);
        String output = "";
        for (Task item : textToAdd) {
            output = output + (textToAdd.indexOf(item) + 1) + "." + item  + "\n";
        }
        fw.write(output);
        fw.close();
    }

    public ArrayList<Task> load() throws DukeException{


        try {
            Scanner scanner = new Scanner(file);
            if (file.length() == 0) {
                throw new DukeException ("File is Empty!");
            } else {
                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine();
                    String type = String.valueOf(task.charAt(1));

                    if (type.equals("T")) {
                        String currTask = task.substring(9);
                        Task td = new Todo(" " + currTask);
                        inputList.add(td);
                    } else if (type.equals("E")) {
                        int splitter = task.indexOf(" (at: ");
                        String currTask = task.substring(9, splitter);
                        String time = task.substring(splitter + 6, task.indexOf(")"));
                        Task ev = new Event(" " + currTask, time);
                        inputList.add(ev);
                    } else if (type.equals("D")) {
                        int splitter = task.indexOf(" (by: ");
                        String currTask = task.substring(9, splitter);
                        String time = task.substring(splitter + 6, task.indexOf(")"));
                        Task dl = new Deadline(" " + currTask, time);
                        inputList.add(dl);
                    } else {
                        break;
                    }

                }
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
        } catch (DukeException e) {
            File file = new File(filePath);
            throw new DukeException ("File is empty!");
        }

        return inputList;

    }
}
