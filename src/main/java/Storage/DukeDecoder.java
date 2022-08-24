package Storage;

import TaskList.Deadline;
import TaskList.Event;
import TaskList.Task;
import TaskList.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeDecoder {
    public static ArrayList<Task> loadDataFromList() {
        ArrayList<Task> workList = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("src/main/List.txt"));
            while (s.hasNextLine()){
                String[] task = s.nextLine().split("\\|");
                String typeOfTask = task[0];
                switch (typeOfTask) {
                    case "T":
                        workList.add(new ToDo(task[2], task[1].equals("1")));
                        break;
                    case "D":
                        workList.add(new Deadline(task[2], task[1].equals("1"), task[3]));
                        break;
                    case "E":
                        workList.add(new Event(task[2], task[1].equals("1"), task[3]));
                        break;
                }
            }
            s.close();
            return workList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return new ArrayList<Task>();
    }
}
