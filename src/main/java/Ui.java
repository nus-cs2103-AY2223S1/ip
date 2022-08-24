public class Ui {

    public void greet() {
        System.out.println("Hi, my name is Duke and it's power hour!");
        System.out.println("Commands are CASE SENSITIVE: enter all commands in lowercase.");
        System.out.println("Please input all dates and times in the following format:");
        System.out.println("dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)");
        System.out.println("********************************************");
    }

    public void taskAddedMessage(Task task) {
        System.out.println("Task added: " + task);
    }

    public void taskDeletedMessage(Task task) {
        System.out.println("Task removed: " + task);
    }

    public void taskMarkedMessage(Task task) {
        System.out.println("Task marked as done: " + task);
    }

    public void taskUnmarkedMessage(Task task) {
        System.out.println("Task marked as not done: " + task);
    }
}
