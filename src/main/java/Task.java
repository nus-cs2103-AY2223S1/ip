import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Type taskType;
    protected String moreInfo;
    protected boolean saveTask;

    protected enum Type {
        TODO, DEADLINE, EVENT
    }

    //Todo
    public Task(String description, boolean saveTask) {
        this.description = description;
        this.saveTask = saveTask;
    }

    //Event and Deadline
    public Task(String description, String moreInfo, boolean saveTask) {
        this.description = description;
        this.moreInfo = moreInfo;
        this.saveTask = saveTask;
    }

    public static void makeTask(String description, String moreInfo, Type type, boolean saveTask) {
        switch (type) {
            case TODO:
                ToDo todo = new ToDo(description, saveTask);
                Sally.list.add(todo);
                if (saveTask) {
                    printMakeTask();
                    saveTaskToFile();
                }
                break;
            case DEADLINE:
                Deadline deadline = new Deadline(description, moreInfo, saveTask);
                Sally.list.add(deadline);
                if (saveTask) {
                    printMakeTask();
                    saveTaskToFile();
                }
                break;
            case EVENT:
                Event event = new Event(description, moreInfo, saveTask);
                Sally.list.add(event);
                if (saveTask) {
                    printMakeTask();
                    saveTaskToFile();
                }
                break;
        }
    }

    public static void saveTaskToFile() {
        try {
            Sally.savesFile();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }

    public static void printMakeTask() {
        String newTask = Sally.list.get(Sally.list.size() - 1).toString();
        int totalTasks = Sally.list.size();

        Sally.printBorder();
        System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
        if (totalTasks == 1) {
            System.out.println("Now you have 1 task in the list.\n");
        } else {
            System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
        }
        Sally.printBorder();
    }

    public String getMoreInfo() {
        return this.moreInfo;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
