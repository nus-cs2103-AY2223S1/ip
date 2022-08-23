package yilia;

import yilia.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        ArrayList<Task> tasks = new ArrayList<>();
        while (line != null) {
            String[] info = line.split(" / ");
            switch (info[0]) {
                case "T":
                    tasks.add(new Todo(info[2], Parser.parseStringToBoolean(info[1])));
                    break;
                case "D":
                    tasks.add(new Deadline(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                    break;
                case "E":
                    tasks.add(new Event(info[2], Parser.parseStringToBoolean(info[1]), info[3]));
                    break;
            }
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 1; i <= tasks.size(); i++) {
                writer.write(tasks.get(i).parse());
                writer.write('\n');
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}