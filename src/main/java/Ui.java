public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke. How may I assist you?");
    }
    
    public void showPrompt() {
        System.out.print("\n>>> ");
    }
    
    public void showLoadingError() {
        System.out.println("FAILED! Could not find storage file containing your tasks");
        System.out.println("Add a task to generate one!!!");
    }
    
    public void showLoadingSuccess() {
        System.out.println("Successfully loaded all tasks! :)");
    }
    
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
    public void showGoodbye() {
        System.out.println("Goodbye! Hope to see you soon!");
    }
    
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        this.showTask(task);
        this.showNumTasks(tasks);
    }
    
    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Okie, I've deleted this task: ");
        this.showTask(task);
        this.showNumTasks(tasks);
    }
    
    public void showMarkTask(Task task, TaskList tasks) {
        System.out.println("Sure! I've marked this task as done: ");
        this.showTask(task);
        this.showNumTasks(tasks);
    }
    
    public void showUnmarkTask(Task task, TaskList tasks) {
        System.out.println("Sure! I've unmarked this task as done: ");
        this.showTask(task);
        this.showNumTasks(tasks);
    }
    
    public void showNumTasks(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " tasks in the list");
    }
    
    public void showTask(Task task) {
        System.out.println("   " + task);
    }
    
}
