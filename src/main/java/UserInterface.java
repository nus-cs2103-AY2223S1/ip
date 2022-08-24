import java.util.ArrayList;

public class UserInterface {
    private static ArrayList<Task> taskList = new ArrayList<>();

    static void copyList(ArrayList<Task> oldArr) {
        for (int i = 1; i <= oldArr.size(); i++) {
            taskList.add(oldArr.get(i-1));
        }
    }

    static void addTodo(String input) {
        TaskHandler.add(input, taskList);
    }

    static void deleteTodo(String input) {
        TaskHandler.delete(input, taskList);
    }

    static void showList() {
        if (taskList.size() == 0) {
            System.out.println(new DukeException("ERROR: empty list.").getMessage());
        }
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i-1);
            System.out.println(i + "." + currTask.toString());
        }
    }

    static void markChild(int index) {
        taskList.get(index).mark();
    }

    static void unmarkChild(int index) {
        taskList.get(index).unmark();
    }

    static void Bye(Storage storage) {
        storage.writeData(taskList);
        System.out.println("Bye Bye!");
    }
}
