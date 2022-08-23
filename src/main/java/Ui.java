import java.util.Arrays;



public class Ui {
    public static String filePath = "data/list.txt";

    public void mark(String num, TaskList taskList) throws DukeException{
        try {
            Task task = taskList.markDone(num);
            System.out.println("Nice I have marked the following task as done:");
            System.out.println(task);
        } catch (DukeException e) {
            throw e;
        }
    }


    public void unmark(String num, TaskList taskList) throws DukeException{
        try {
            Task task = taskList.markUndone(num);
            System.out.println("Ok, I have marked this task as not done yet:");
            System.out.println(task);
        } catch (DukeException e) {
            throw e;
        }
    }

    public void proccessDeadline(String[] arr, TaskList taskList) throws DukeException {
        try {
            taskList.addDeadline(arr);
        } catch (DukeException e) {
            throw e;
        }
    }

    public void proccessRemove(String[] command, TaskList taskList) throws DukeException {
        try {
            taskList.removeTask(command);
        } catch (DukeException e) {
            throw e;
        }
    }

    public void proccessTodo(String[] arr, TaskList taskList) throws DukeException {
        try {
            taskList.addTodo(arr);
        } catch (DukeException e) {
            throw e;
        }
    }

    public void proccessEvent(String[] arr, TaskList taskList) throws DukeException {
        try {
            taskList.addEvent(arr);
        } catch (DukeException e) {
            throw e;
        }
    }

    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }


}
