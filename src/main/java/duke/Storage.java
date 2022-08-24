package duke;

import java.io.*;
import java.util.ArrayList;


public class Storage {

    private String filePath;
    private ArrayList<Task> arr;

    //constructor
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //methods
    /**
     * Writer method responsible for writing list to a txt file
     *
     * @param object of type ArrayList<Task> which content is written to txt
     */
    public void writer(ArrayList<Task> arr) {
        String temp = "";

        for (Task item: arr) {
            temp += (item.toString() + "\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(temp);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * reads content inside txt file, enabling retrieval of saved state
     *
     * @return object of type String which is a String of saved information to be processed
     */
    public String reader() {
        String s = "";
        int counter_mark = 1;
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((s = reader.readLine()) != null) {
                builder.append(s).append("%");
                if (s.contains("X")) {
                    builder.append("mark ").append(counter_mark).append("%");
                }
                counter_mark++;
            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
