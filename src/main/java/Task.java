public class Task {

    private String task;
    private int id;
    private static int counter = 0;

    public Task(String t) {
        task = t;
        counter ++;
        id = counter;
    }

    public String getTask() {
        return task;
    }

    public int getId() {
        return id;
    }
}
