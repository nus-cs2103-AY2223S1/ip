package puke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String fileName;

    public static File savedTasks = new File("pukeData.txt");

    public Storage(String path) {
        this.fileName = "pukeData.txt";

        try {
            File file = new File("pukeData.txt");
            file.createNewFile(); // Creates a new file if the file doesn't exist, does nothing otherwise
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> temp = new ArrayList<>();
            Scanner s = new Scanner(savedTasks);
            while (s.hasNextLine()) {
                String taskInString = s.nextLine();
                //Duke.d.tasklist.numTasks++;
                String[] taskInArray = taskInString.split(" \\| ");

                if (taskInArray[0].equals("T")) {
                    Task task = new ToDo(taskInArray[2]);
                    temp.add(task);
                //                  Duke.d.tasklist.tasks.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                } else if (taskInArray[0].equals("D")) {
                    Task task = new Deadline(taskInArray[2], LocalDate.parse(taskInArray[3]));
                    temp.add(task);
                //    Duke.d.tasklist.tasks.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                } else if (taskInArray[0].equals("E")) {
                    Task task = new Event(taskInArray[2], LocalDate.parse(taskInArray[3]));
                    temp.add(task);
                //    Duke.d.tasklist.tasks.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                }
            }
            return temp;
        } catch (FileNotFoundException e) {
            System.out.println("Aiya cant find the file la");
            return new ArrayList<>();
        }
    }

    public static void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(Duke.d.storage.savedTasks.getPath());
            for (Task task: tasks) {
                writer.write(task.saveFormat() + System.lineSeparator());
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Aiya, cant save it la");
        };
    }


}
