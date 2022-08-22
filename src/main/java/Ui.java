import java.util.Scanner;

public class UI {

    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
    }

    public void showGoodbye() { System.out.println("Bye Bye See You Next Time!"); }

    public String takeInput() {
        return sc.nextLine();
    }

    public boolean hasInput() {
        return sc.hasNextLine();
    }

    public void showDividerLine() {
        System.out.println("==============================");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showListOut(TaskList taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("You do not have any tasks in the list");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    public void showDelete(TaskList taskList, Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        if (taskList.size() == 1) {
            System.out.println("Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list");
        }
    }

    public void showAdd(TaskList taskList, Task newTask) {
        System.out.println("Nice! Added this task:");
        System.out.println(newTask);

        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public void showMark(TaskList taskList, int index) {
        System.out.println("Weeeee! I've marked this task as done:");
        System.out.println(taskList.get(index).toString());
    }

    public void showUnmark(TaskList taskList, int index) {
        System.out.println("Aw Mans... I've unmarked this task:");
        System.out.println(taskList.get(index).toString());
    }

}
