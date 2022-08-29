package Duke;
import Duke.Task.Task;
public class Ui {
    public Ui() {

    }
    private void lineBreak() {
        System.out.println("\n");
    }
    public void showGreeting() {
        System.out.println("Howwwwwdyyyyyy");
        this.lineBreak();
        String image = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣿⣿⣷⣶⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠁⣰⣿⣿⣿⡿⠿⠻⠿⣿⣿⣿⣿⣧⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠏⠀⣴⣿⣿⣿⠉⠀⠀⠀⠀⠀⠈⢻⣿⣿⣇⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⢀⣠⣼⣿⣿⡏⠀⢠⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡀⠀\n⠀"
                      +"⠀⠀⠀⣰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⢰⣿⣿⡿⣿⣿⣿⡇⠀⠘⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⢀⣸⣿⣿⣿⠁⠀\n⠀"
                      +"⠀⠀⣿⣿⣿⠁⣿⣿⣿⡇⠀⠀⠻⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⠃⠀⠀\n⠀"
                      +"⠀⢰⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀\n⠀"
                      +"⠀⢸⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠛⠉⢉⣿⣿⠀⠀⠀⠀⠀\n⠀"
                      +"⠀⢸⣿⣿⣇⠀⣿⣿⣿⠀⠀⠀⠀⠀⢀⣤⣤⣤⡀⠀⠀⢸⣿⣿⣿⣷⣦⠀⠀\n⠀"
                      +"⠀⠀⢻⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⡀⠀⠉⠉⠻⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⠀⠛⠿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠈⠹⣿⣿⣇⣀⠀⣠⣾⣿⣿⡇⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣦⣤⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⠿⠋⠉⠛⠋⠉⠉⠁⠀⠀⠀\n⠀"
                      +"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁";
        System.out.println(image);
        this.lineBreak();
        System.out.println("What can i do for you? :)");
        this.lineBreak();
    }

    public void showBye() {
        System.out.println("You dont want me anymore... sigh");
        this.lineBreak();
    }

    public void showAllTask(TaskList taskList) {
        System.out.println("Look at me being useful\n");
        System.out.println(taskList);
        this.lineBreak();
    }

    public void showAddTask(TaskList taskList, Task task) {
        System.out.println("Danm you have another task");
        this.lineBreak();
        System.out.println(task.formatTask());
        this.lineBreak();
        System.out.println(String.format("oh god you have %s tasks now", taskList.length()));
        this.lineBreak();
    }

    public void showRemoveTask(TaskList taskList, Task task) {
        System.out.println("Thats right work is just a concept");
        this.lineBreak();
        System.out.println(task.formatTask());
        this.lineBreak();
        System.out.println(String.format("oh god you have %s tasks now", taskList.length()));
        this.lineBreak();
    }

    public void showMarkTask(Task task) {
        System.out.println("Noice you are done w a task. EFFISHUN!!");
        this.lineBreak();
        System.out.println(task.formatTask());
        this.lineBreak();
    }

    public void showUnmarkTask(Task task) {
        System.out.println("IMPOSTERRRRRRRR!!! I WILL UNMARK IT ");
        this.lineBreak();
        System.out.println(task.formatTask());
        this.lineBreak();
    }
}
