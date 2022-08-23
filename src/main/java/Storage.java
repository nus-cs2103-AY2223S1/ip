import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {

    public static void saveFile(ArrayList<Task> arr) {
        String fileName = "tasks.txt";
        FileWriter fileWriter = null;

        try {
            File file = new File(fileName);
            fileWriter = new FileWriter(file);
            if (!arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    Task tsk = arr.get(i);
                    String type = tsk.toString().substring(1,2);
                    if (type.equals("T")) {
                        fileWriter.write(String.format("%s ~ %s ~ %s\n", type, tsk.getStatusIcon(), tsk.getDescription()));
                    } else {
                        fileWriter.write(String.format("%s ~ %s ~ %s ~ %s\n", type, tsk.getStatusIcon(), tsk.getDescription(), tsk.getDate()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static ArrayList<Task> loadFile() throws DukeException {
        BufferedReader reader = null;
        ArrayList<Task> arr = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("tasks.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] parse = line.split(" ~ ");
                Task tsk = null;
                if (parse[0].equals("T")) {
                    tsk = new Todo(parse[2]);
                } else if (parse[0].equals("E")) {
                    tsk = new Event(parse[2], parse[3]);
                } else if (parse[0].equals("D")) {
                    tsk = new Deadline(parse[2], parse[3]);
                } else {
                    throw new DukeException("Invalid File");
                }
                if (parse[1].equals("X")) {
                    tsk.markAsDone();
                }
                arr.add(tsk);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    return arr;
                }
            } else {
                return arr;
            }
        }
    }
}

