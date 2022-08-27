package duke;

public class Ui {

    String line_divider = "____________________________________________________________";

    public void printIntro() {
        System.out.println(line_divider);
        System.out.println("Wassup la I'm duke.Duke\nWhat you want?\n" + line_divider);
    }
    public void goodByeMessage() {
        System.out.println(line_divider + "\nBye. Zai Jian!\n" + line_divider);
    }

    public void printList(TaskList tl) {
        System.out.println(line_divider);
        if (tl.size() == 0) {
            System.out.println("List is empty la");
            System.out.println(line_divider);
            return;
        }
        System.out.println("Here are your tasks la:");
        for (int j = 0; j < tl.size(); j++) {
            System.out.println(j + 1 + ":" + tl.get(j).toString());
        }
        System.out.println(line_divider);
    }

    public void printMarkedMsg(Task task) {
        System.out.println(line_divider);
        System.out.println("Ok ticked this already");
        System.out.println(task.toString());
        System.out.println(line_divider);
    }

    public void printUnmarkedMsg(Task task) {
        System.out.println(line_divider);
        System.out.println("Ok not done yet ah");
        System.out.println(task.toString());
        System.out.println(line_divider);
    }

    public void printDeleteMsg(String removedTask, int size) {
        System.out.println(line_divider);
        System.out.println("I remove this ah:");
        System.out.println();
        System.out.println("Now " + size + " tasks only");
        System.out.println(line_divider);
    }

    public void printOutOfBoundsMsg() {
        System.out.println(line_divider);
        System.out.println("Out of bounds lah, try again");
        System.out.println(line_divider);
    }

    public void printNoTaskInputMsg() {
        System.out.println("☹ OOPS!!! Why empty");
    }

    public void printTaskAddedMsg(Task task, int size) {
        System.out.println(line_divider);
        System.out.println("Ok I add your task already:");
        System.out.println(task + "\nNow " + size + " tasks already");
        System.out.println(line_divider);
    }

    public void printError() {
        System.out.println(line_divider);
        System.out.println("☹ Walao what do you mean");
        System.out.println(line_divider);
    }

    public void printClearMsg() {
        System.out.println(line_divider);
        System.out.println("CLEAR ALL LIAO");
        System.out.println(line_divider);

    }
}
