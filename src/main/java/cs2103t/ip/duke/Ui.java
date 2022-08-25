package cs2103t.ip.duke;

public class Ui {

    private final String LINE = "_______________________________\n";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println(LINE +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                LINE
        );
    }

    public void showBye() {
        System.out.println(LINE +
                "Bye. Hope to see you again soon!\n" +
                LINE);
    }

    public void showMark(Tasklist list, int taskNum) {
        System.out.println(LINE +
                "Nice! I've marked this task as done: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + LINE);
    }

    public void showUnmark(Tasklist list, int taskNum) {
        System.out.println(LINE +
                "OK, I've marked this task as not done yet: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + LINE);
    }

    public void showList(Tasklist tasks, int index) {
        String list = LINE + "\n" + "Here are the tasks in your list: \n";
        for (int i = 0; i < index; i++) {
            list += i + 1;
            list += ". ";
            list += tasks.getTasks().get(i).toString();
            list += "\n";
        }
        System.out.println(list + LINE);
    }

    public void showFilteredList(Tasklist tasks, int index) {
        String list = LINE + "\n" + "Here are the matching tasks in your list: \n";
        for (int i = 0; i < index; i++) {
            list += i + 1;
            list += ". ";
            list += tasks.getTasks().get(i).toString();
            list += "\n";
        }
        System.out.println(list + LINE);
    }

    public void showTodo(Todo todo, int index) {
        System.out.println(todo.addString(index));
    }

    public void showDeadline(Deadlines deadlines, int index) {
        System.out.println(deadlines.addString(index));
    }

    public void showEvent(Event event, int index) {
        System.out.println(event.addString(index));
    }

    public void showDelete(Task toDelete, int index) {
        System.out.println(LINE + "Noted. I've removed this task: \n" +
                toDelete.toString() + "\n" +
                "Now you have " + index + " tasks in the list. \n" + LINE);
    }

    public void showLoadingError() {
        System.out.println("Error Loading from Database :( ");
    }
}
