import exceptions.EmptyNameException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String command) {
        try {

            Task task = null;
            String[] split = command.split(" ", 2);
            if(split.length < 2) {
                throw new EmptyNameException();
            }
            String type = split[0];
            if(type.equals("todo")){
                String name = split[1];
                task = new Todo(name);
                System.out.println(name);

            } else {
                String[] split1 = split[1].split("/");
                String name = split1[0];
                String info = split1[1];
                if(type.equals("deadline")){
                    task = new Deadline(name, info);
                } else {
                    task = new Event(name, info);
                }
            }

            if(task != null){
                this.taskList.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + taskList.size() +  " tasks in the list.");
            }
        } catch (EmptyNameException e) {
            System.out.println(e.getMessage());
        }


    }


    public void setTaskAsDone(int index) {
        Task task = taskList.get(index);
        task.markTaskAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);

    }

    public void setTaskAsUndone(int index) {
        Task task = taskList.get(index);
        task.markTaskAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(task);
    }

    public void displayAllTask(){
        System.out.println("----");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i-1);
            System.out.println(i + ". " + task);
        }
        System.out.println("-----");
    }

}
