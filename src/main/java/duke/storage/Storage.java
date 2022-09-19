package duke.storage;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    private final File DATA_DIRECTORY = new File("/data");
    private final File DATA_FILEPATH = new File("data/duke.txt");

    public Storage() {
        if(!DATA_DIRECTORY.exists()) {
            DATA_DIRECTORY.mkdir();
        }

        try {
            DATA_FILEPATH.createNewFile();
        } catch (IOException e) {
            System.out.println("Couldn't create a new file.");
        }
    }

    public ArrayList<Task> convertToTaskList() {

        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(DATA_FILEPATH);
            while(sc.hasNextLine()) {

                String task = sc.nextLine();
                String[] description = task.split(" \\| ");

                boolean isDone = description[1].equals("1");

                switch (description[0]) {

                case "T":
                    taskList.add(new ToDo(description[2], isDone));
                    break;
                case "D":
                    taskList.add(new Deadline(description[2], isDone, description[3]));
                    break;
                case "E":
                    taskList.add(new Event(description[2], isDone, description[3]));
                    break;
                default:
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("There was an error while opening the file");
        }


        return taskList;

    }

    public void saveTaskList(TaskList taskList) {

        try {
            FileWriter fw = new FileWriter(DATA_FILEPATH);
            fw.write(taskList.taskListFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Your data couldn't be saved");
        }

    }




}
