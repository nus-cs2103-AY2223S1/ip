import exceptions.EmptyNameException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String command) {
        try {

            Task task = null;

            String type = Parser.getType(command);
            if(type.equals(TaskTypeEnum.todo.toString())){
                if(command.trim().length() == 4) {
                    throw new EmptyNameException();
                }
                String name = Parser.getTodoName(command);
                task = new Todo(name);

            } else {

                if(type.equals(TaskTypeEnum.deadline.toString())){
                    if(command.trim().length() == 8){
                        throw new EmptyNameException();
                    }
                    task = new Deadline(Parser.getDeadlineName(command), Parser.getDeadlineDate(command));
                } else {
                    if(command.trim().length() == 5){
                        throw new EmptyNameException();
                    }
                    task = new Event(Parser.getEventName(command), Parser.getEventDate(command));
                }
            }

                taskList.add(task);

                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                displayNumberOfItemsInList();
        } catch (EmptyNameException e) {
            System.out.println(e.getMessage());
        }


    }

    public void deleteTask(int index) {
        Task task = taskList.get(index);
        System.out.println("Noted. I've removed this task: \n" + task);
        taskList.remove(index);
        displayNumberOfItemsInList();
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

    public void displayNumberOfItemsInList() {
        System.out.println("Now you have " + taskList.size() +  " tasks in the list.");
    }

    public void readTaskFromLoader(String command) {
        try {
            Task task = null;
            String[] split = command.split("\\|");

            if(split.length < 2) {
                throw new EmptyNameException();
            }
            String type = split[0];
            String marked = split[1];
            if(type.equals("todo")){
                String name = split[2];
                task = new Todo(name);


            } else {
                String name = split[2];
                String info = split[3];
                if(type.equals("deadline")){
                    task = new Deadline(name, info);
                } else {
                    task = new Event(name, info);
                }
            }
            if(marked.equals("1")){
                task.markTaskAsDone();
            }

            taskList.add(task);

        } catch (EmptyNameException e) {
            System.out.println(e.getMessage());
        }

    }

}
