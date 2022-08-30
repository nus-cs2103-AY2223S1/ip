package duke;

import exceptions.EmptyNameException;

import java.util.ArrayList;

/**
 * Represents the state of the tasklist in the program.
 * Contains methods that handles the logic for adding, deleting and modifying tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class
     * Initializes an empty arrayList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Takes in a command and handle the logic regarding the command
     *
     * @param command User input
     * @return String response
     */
    public String addTask(String command) {
        try {

            Task task = null;

            String type = Parser.getType(command);
             if (type.equals(TaskTypeEnum.todo.toString())) {
                if (command.trim().length() == 4) {
                    throw new EmptyNameException();
                }
                String name = Parser.getTodoName(command);
                task = new Todo(name);

            } else {

                if (type.equals(TaskTypeEnum.deadline.toString())) {
                    if (command.trim().length() == 8) {
                        throw new EmptyNameException();
                    }
                    task = new Deadline(Parser.getDeadlineName(command), Parser.getDeadlineDate(command));
                } else {
                    if (command.trim().length() == 5) {
                        throw new EmptyNameException();
                    }
                    task = new Event(Parser.getEventName(command), Parser.getEventDate(command));
                }
            }

            taskList.add(task);

            String ret = "";

            ret += "Got it. I've added this task: \n";
            ret += task;
            return ret + "\n" + displayNumberOfItemsInList();

        } catch (EmptyNameException e) {
            return e.getMessage();
        }


    }


    /**
     * Takes in the index of the tasks and removes it from the array list.
     *
     * @param index position of the task in the array list
     */
    public String deleteTask(int index) {
        Task task = taskList.get(index);
        System.out.println("Noted. I've removed this task: \n" + task);
        taskList.remove(index);
        return "Noted. I've removed this task: \n" + task + "\n" + displayNumberOfItemsInList();

    }

    /**
     * Takes in the index of the tasks and mark the task as done.
     *
     * @param index position of the task in the array list
     * @return String response
     */
    public String setTaskAsDone(int index) {
        Task task = taskList.get(index);
        task.markTaskAsDone();
        String ret = "Nice! I've marked this task as done: \n";
        ret += task;
        return ret;
    }

    /**
     * Takes in the index of the tasks and mark the task as undone.
     *
     * @param index position of the task in the array list
     * @return String response
     */
    public String setTaskAsUndone(int index) {
        Task task = taskList.get(index);
        task.markTaskAsUndone();
        String ret = "OK, I've marked this task as not done yet:\n";
        ret += task;
        return ret;
    }

    /**
     * Displays all tasks in the command line
     */
    public String displayAllTask() {
        if(taskList.size() == 0 ) {
            return "You do not have any tasks currently";
        }

        StringBuilder ret = new StringBuilder();
            ret.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.get(i - 1);
            ret.append(i).append(". ").append(task).append("\n");
        }

        return ret.toString();
    }

    /**
     * Display number of items in currently in the array list
     * @return number of item in the list
     */
    public String displayNumberOfItemsInList() {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Read the tasks from the saved file and prepopulate the arraylist.
     *
     * @param command Line from the saved file
     */
    public void readTaskFromLoader(String command) {
        try {
            Task task = null;
            String[] split = command.split("\\|");

            if (split.length < 2) {
                throw new EmptyNameException();
            }
            String type = split[0];
            String marked = split[1];
            if (type.equals("todo")) {
                String name = split[2];
                task = new Todo(name);


            } else {
                String name = split[2];
                String info = split[3];
                if (type.equals("deadline")) {
                    task = new Deadline(name, info);
                } else {
                    task = new Event(name, info);
                }
            }
            if (marked.equals("1")) {
                task.markTaskAsDone();
            }

            taskList.add(task);

        } catch (EmptyNameException e) {
            System.out.println(e.getMessage());
        }

    }

    public String findTask(String name){
        StringBuilder ret = new StringBuilder();
        ret.append("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            if(task.getTaskName().contains(name)){
                ret.append(i).append(". ").append(task);
            }
        }
        return ret.toString();

    }

}
