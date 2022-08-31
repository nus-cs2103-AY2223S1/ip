package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the path of the file in the hard disk where the output of the program is stored.
 */
public class Storage {
    /* The path of the output file */
    private String filepath;

    /**
     * Constructor for Storage.
     *
     * @param filePath the path of the output file.
     */
    public Storage(String filePath) {
        this.filepath = filePath;
    }

    /**
     * Loads the output from the output file in hard disk to the program.
     *
     * @return an arraylist of tasks stored.
     * @throws IOException if file cannot be opened or read.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path path = Paths.get("./data/");
            Files.createDirectories(path);
            File file = new File(filepath);
            file.createNewFile();

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                char action = data.charAt(1);
                Task task;

                if (action == 'T') {
                    task = new Todo(data.substring(7));


                } else {
                    int symbol = data.indexOf("(");
                    int dash = data.indexOf("-");
                    int secondDash = data.indexOf("-", dash + 1);
                    int colon = data.indexOf(":", secondDash + 1);
                    Month month = Month.valueOf(data.substring(symbol + 5, dash));
                    int dayOfMonth = Integer.parseInt(data.substring(dash + 1, secondDash));
                    int year = Integer.parseInt(data.substring(secondDash + 1, secondDash + 5));
                    int hour = Integer.parseInt(data.substring(colon - 2, colon));
                    int minute = Integer.parseInt(data.substring(colon + 1, colon + 3));
                    LocalDateTime date = LocalDateTime.of(year, month, dayOfMonth, hour, minute);

                    if (action == 'E') {
                        task = new Event(data.substring(7, symbol), date);
                    } else {
                        task = new Deadline(data.substring(7, symbol), date);
                    }


                }

                char isDone = data.charAt(4);
                tasks.add(task);
                if (isDone == 'X') {
                    task.setDone();

                }


            }
            reader.close();
            return tasks;


        } catch (IOException e) {
            return tasks;

        }

    }

    /**
     * Saves the new changes to the tasks to the output file in the hard disk.
     *
     * @param tasks the list of tasks currently in the program.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./data/Duke.txt");
            String str = "";
            for (int i = 0; i < tasks.size(); i++) {
                str += tasks.get(i).toString();
                str += "\n";
            }
            writer.write(str);
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
