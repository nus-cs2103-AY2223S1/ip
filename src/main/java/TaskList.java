import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> itemList = new ArrayList<>();

    public void addTask(Task toAdd) {
        itemList.add(toAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + toAdd);
        System.out.println("Now you have " + itemList.size() + " tasks in the list.");
    }

    public void deleteTask(String stringDex) throws DukeException {
        int index = Integer.parseInt(stringDex);
        if (index >= itemList.size()) {
            throw new DukeException("Item to be deleted not found");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + itemList.get(index - 1));
            itemList.remove(index - 1);
            System.out.println("Now you have " + itemList.size() + " tasks in the list.");

        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < itemList.size(); i++) {
            int index = i + 1;
            String add = index + "." + itemList.get(i) + "\n";
            sb.append(add);
        }
        return sb.toString();
    }

    public void markTask(int index) {
        Task marked = itemList.get(index);
        marked.setStatusIcon(true);
        marked.updateStatus();
    }

    public void unmarkTask(int index) {
        Task marked = itemList.get(index);
        marked.setStatusIcon(false);
        marked.updateStatus();
    }
}
