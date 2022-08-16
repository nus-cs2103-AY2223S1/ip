import java.util.ArrayList;

public class UserInterface {
    static ArrayList<Task> toDoList = new ArrayList<>();

    static void addToDo(String input) {
        Task task = new Task(input);
        toDoList.add(task);
        System.out.println("added: " + task.description);
    }

    static void showList() {
        for (int i = 1; i <= toDoList.size(); i++) {
            Task currTask = toDoList.get(i-1);
            String output = String.format(i +  ".[%s] " + currTask.description, currTask.getStatusIcon());
            System.out.println(output);
        }
    }

    static void markChild(int index) {
        toDoList.get(index).mark();
    }

    static void unmarkChild(int index) {
        toDoList.get(index).unmark();
    }

    static void Bye() {
        System.out.println("Bye Bye!");
    }
}
