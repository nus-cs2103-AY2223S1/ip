import java.util.List;
import java.util.Scanner;

public class TaskTodo extends Task{

    public TaskTodo(String name, boolean done) throws TaskNoNameException {
        super(name, done);
    }

    public static String makeTodo(Scanner s, List<Task> taskList) {

        System.out.print("What is the name of your todo: ");
        String name = s.nextLine();

        try {
            Task task = new TaskTodo(name, false);
            taskList.add(task);
            return "I created a Todo " + task + " for you. You have " + taskList.size() + " task(s) now.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "todo";
    }

}