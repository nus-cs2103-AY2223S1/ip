import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SaveFile {
    private static final String SAVE_PATH = "./data/duke.txt";

    public void writeFile(ArrayList<Task> tasks) {
        File file = new File(SAVE_PATH);

        // Create Directory or File if it does not exist
        try {
            if (!file.exists()) {
                File directory = new File(file.getParent());

                if (!directory.exists()) {
                    directory.mkdir();
                }

                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred: " + e.toString());
        }

        // Write into File
        try {
            String line = "";
            String type;
            String done;
            String desc;
            String dateline;
            String date;
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task task : tasks) {
                type = task.getTaskType();
                desc = task.getDescription();
                done = task.isDone ? "1" : "0";
                dateline = "";
                //date = task.getDate();

                if (type.equals("E") || type.equals("D")) {
                    dateline = task.getDateline();
                }

                line = type + " > " + done + " > " + desc  + (dateline.equals("") ? "" : "> " + dateline);// + " | " + date;
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = "";
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Hmm... Error while saving Task to file");
        }
    }

    public void readFile(ArrayList<Task> tasks) {
        File file = new File(SAVE_PATH);

        if (!file.exists()) {
            System.out.println("Save file does not exist");
            return;
        }

        try {
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] arr = line.split(" > ");
                String type = arr[0];
                switch (type) {
                    case "T" :
                        tasks.add(new Todo(arr[2], arr[1]));
                        break;
                    case "E" :
                        tasks.add(new Events(arr[2], arr[1], arr[3]));
                        break;
                    case "D" :
                        tasks.add(new Deadline(arr[2], arr[1], arr[3]));
                        break;
                }

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
