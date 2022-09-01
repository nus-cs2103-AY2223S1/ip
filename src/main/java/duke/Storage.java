package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;
    ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    private void addToListfromFile(String taskname) {
        boolean bool = (taskname.charAt(4) == 'X' ? true : false);
        if (taskname.charAt(1) == 'T'){
            Task newTask = new ToDo(taskname.substring(7),bool);
            tasks.add(newTask);
        } else if (taskname.charAt(1) == 'E') {
            String des = taskname.substring(7,taskname.indexOf("(")-1);
            String at = taskname.substring(taskname.indexOf(":")+2,taskname.indexOf(")"));
            Task newTask = new Event(des, bool, at);
            tasks.add(newTask);
        } else {
            String des = taskname.substring(7,taskname.indexOf("(")-1);
            String by = taskname.substring(taskname.indexOf(":")+2, taskname.indexOf(")"));
            LocalDate deadline = LocalDate.parse(by);
            Task newTask = new Deadline(des, bool, deadline);
            tasks.add(newTask);
        }
    }


    public ArrayList<Task> load(Ui ui) throws DukeException {
            if (Files.exists(Path.of(this.filepath))) {
                File taskList = new File(this.filepath);
                Scanner tasks = null;
                try {
                    tasks = new Scanner(taskList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (tasks.hasNext()) {
                    addToListfromFile(tasks.nextLine());
                }
            } else {
                throw new DukeException("No saved tasks found");
            }

        ui.tasksLoadedMsg(tasks.size());
        return tasks;
    }



}
