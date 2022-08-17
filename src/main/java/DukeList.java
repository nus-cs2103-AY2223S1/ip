import java.util.ArrayList;
import java.util.List;

public class DukeList {
    private final List<Task> listItems = new ArrayList<>();

    public DukeList() {

    }

    public String add(Task item) {
        listItems.add(item);
        return "Got it. I've added this task:\n"
                + item
                + "\nNow you have "
                + listItems.size()
                + (listItems.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

    public String done(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as done.");
        }

        Task t = listItems.get(listIndex);
        t.markAsDone();
        return "Nice! I've marked this task as done:\n" + t;
    }

    public String undone(int index) throws DukeException {
        int listIndex = index - 1;
        if (listIndex < 0 || listIndex > listItems.size() - 1) {
            throw new DukeException("Invalid task to mark as undone.");
        }

        Task t = listItems.get(listIndex);
        t.markAsUndone();
        return "OK, I've marked this task as not done yet:\n" + t;
    }

    @Override
    public String toString() {
        StringBuilder listItemsStrBuilder = new StringBuilder();
        listItemsStrBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < listItems.size(); i++) {
            listItemsStrBuilder.append(i + 1).append(". ").append(listItems.get(i));

            if (i != listItems.size() - 1) {
                listItemsStrBuilder.append("\n");
            }
        }
        return listItemsStrBuilder.toString();
    }
}