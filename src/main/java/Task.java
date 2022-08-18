public class Task {
    public String name;
    static public Integer totalNumber = 0;
    public Integer index;

    public Task(String name) {
        this.name = name;
        totalNumber++;
        this.index = totalNumber;
    }

    public static Task addTask(String name) {
        Task newTask = new Task(name);
        System.out.println("     added: " + newTask.name);
        return newTask;
    }

    @Override
    public String toString() {
        return "     " + this.index + ". " + this.name;
    }
}
