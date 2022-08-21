import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    
    public TaskList(List<String> taskString) {
        for (String i : taskString) {
            char taskType = i.charAt(0);
            String[] components = i.split(" \\| ");
            Task thisTask = new Task("task not saved");
            switch (taskType) {
                case 'T':
                    thisTask = new ToDo(components[2]);
                    break;
                case 'D':
                    thisTask = new Deadline(components[2],
                            components[3]);
                    break;
                case 'E':
                    thisTask = new Event(components[2],
                            components[3]);
                    break;
            }
            if (components[1].equals("1")) {
                thisTask.setDone();
            }
            this.tasks.add(thisTask);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    void mark(int index) {
        tasks.get(index - 1).mark();
    }

    void unMark(int index) {
        tasks.get(index - 1).unmark();
    }

    void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
    }
    
    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public int length() {
        return tasks.size();
    }
    
    public void clearStorage() {
        this.tasks = new ArrayList<>();
    }
}
