import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private TaskMessager taskMessager;

    public TaskList(String text) {
        tasks = new ArrayList<>();
        taskMessager = new TaskMessager();
        if (text != "") {
            String[] texts = text.split("\n");
            String taskType;
            for (int i = 0; i < texts.length; i++) {
                taskType = texts[i].substring(3,6);
                String[] descriptions;
                switch (taskType) {
                case "[T]":
                    tasks.add(new Todo(texts[i].substring(10), texts[i].charAt(7) == 'X'));
                    break;
                case "[D]":
                    descriptions = texts[i].substring(10).split(" / by ");
                    tasks.add(new Deadline(descriptions[0], descriptions[1], texts[i].charAt(7) == 'X'));
                    break;
                case "[E]":
                    descriptions = texts[i].substring(10).split(" / at ");
                    tasks.add(new Event(descriptions[0], descriptions[1], texts[i].charAt(7) == 'X'));
                    break;
                }

            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskMessager.addMessage(task, tasks.size());
    }

    public void listTask() {
        taskMessager.listMessage(toString());
    }

    private void checkIndexOutOfBound(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! Index is out of range!");
        }
    }

    public void markTask(int index) {
        checkIndexOutOfBound(index);
        Task task = tasks.get(index);
        task.mark();
        taskMessager.markMessage(task);
    }

    public void unmarkTask(int index) {
        checkIndexOutOfBound(index);
        Task task = tasks.get(index);
        task.unmark();
        taskMessager.unmarkMessage(task);
    }

    public void deleteTask(int index) {
        checkIndexOutOfBound(index);
        Task removed = tasks.remove(index);
        taskMessager.deleteMessage(removed, tasks.size());
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    @Override
    public String toString() {
        int size = tasks.size();
        String text = String.format("%d. %s", 1, tasks.get(0));
        for (int i = 1; i < size; i++) {
            text += String.format("\n%d. %s", i+1, tasks.get(i));
        }
        return text;
    }
}
