package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();
    private static final String filepath = "./data/duke.txt";

    public TaskList() {

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void createFile() {
        new File("./data").mkdir();
        File taskList = new File(filepath);
    }

    public void addToList(String taskName, Ui ui) {
        try{
            //if task is a to-do
            if (taskName.matches("\\btodo\\s.*\\b")){
                Task newTask = new ToDo(taskName.substring(5),false);
                tasks.add(newTask);
                ui.taskAddMsg(newTask,tasks.size());
            }
            //if task is an event
            else if (taskName.matches("\\bevent\\s.*\\s/at\\s.*\\b")){
                String des = taskName.substring(6,taskName.indexOf("/")-1);
                String at = taskName.substring(taskName.indexOf("/")+4,taskName.length());
                Task newTask = new Event(des,false,at);
                tasks.add(newTask);
                ui.taskAddMsg(newTask,tasks.size());
            }
            ////if task is a deadline
            else if (taskName.matches("\\bdeadline\\s.*\\s/by\\s.*\\b")) {
                String des = taskName.substring(9,taskName.indexOf("/")-1);
                String by = taskName.substring(taskName.indexOf("/")+4);
                LocalDate deadline = LocalDate.parse(by);
                Task newTask = new Deadline(des, false, deadline);
                tasks.add(newTask);
                ui.taskAddMsg(newTask,tasks.size());
            }
            else if (taskName.matches("\\btodo\\s+") || taskName.matches("\\btodo\\b")) {
                throw new DukeException("Sorry please provide a task to be done!");
            }
            else {
                throw new DukeException("I'm sorry, I don't know what that means!");
            }
        } catch(Exception e) {
            String msg = e.getMessage();
            Ui.printMsgWithLine(msg);
        }
    }

    public void viewList(Ui ui) {
        ui.viewListMsg(tasks);
    }

    public void taskDone(int num, Ui ui) {
        Task task = tasks.get(num-1);
        tasks.get(num-1).markAsDone();
        ui.taskDoneMsg(task);

    }

    public void taskUndone(int num, Ui ui) {
        Task task = tasks.get(num-1);
        tasks.get(num-1).markAsUndone();
        ui.taskUndoneMsg(task);
    }

    public void deleteTask(int num,Ui ui) {
        Task task = tasks.get(num-1);
        tasks.remove(num-1);
        ui.deleteTaskMsg(task,tasks.size());
    }

    public void addTasksToFile() {
        try {
            FileWriter fw = new FileWriter(filepath);
            for(int i=0; i<tasks.size(); i++) {
                fw.write(tasks.get(i).toStore() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

