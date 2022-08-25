package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> itemList;

    public TaskList(ArrayList<Task> itemList) {
        this.itemList = itemList;
    }

    public TaskList() {
        this.itemList = new ArrayList<>();
    }

    public void silentAdd(Task toAdd) {
        itemList.add(toAdd);
    }

    public boolean isEmpty() {
        return itemList.size() == 0;
    }

    public void addTask(Task toAdd) {
        itemList.add(toAdd);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + toAdd);
        System.out.println("Now you have " + itemList.size() + " tasks in the list.");
    }

    public void deleteTask(String stringDex) throws DukeException {
        int index = Integer.parseInt(stringDex);
        if (index > itemList.size()) {
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
        StringBuilder sb = new StringBuilder();
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

    public ArrayList<Task> getItemList() {
        return itemList;
    }

    public void findTask(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getDescription().contains(keyword)) {
                matchingTasks.silentAdd(itemList.get(i));
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No such task found. Please try another phrase");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(matchingTasks);
        }
    }
}
