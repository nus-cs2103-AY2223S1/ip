package cs2103t.ip.duke;

public class Ui {

    private String line = "_______________________________\n";

    public void showLine() {
        System.out.println(line);
    }

    public void showWelcome() {
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
    }

    public void showBye() {
        System.out.println(line +
                "Bye. Hope to see you again soon!\n" +
                line);
    }

    public void showMark(Tasklist list, int taskNum) {
        System.out.println(line +
                "Nice! I've marked this task as done: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + line);
    }

    public void showUnmark(Tasklist list, int taskNum) {
        System.out.println(line +
                "OK, I've marked this task as not done yet: \n" +
                list.getTasks().get(taskNum - 1).getStatusIcon() +
                list.getTasks().get(taskNum - 1).description + "\n" + line);
    }

    public void showList(Tasklist tasks, int index) {
        String list = line + "\n" + "Here are the tasks in your list: \n";
        for (int i = 0; i < index; i++) {
            list += i + 1;
            list += ". ";
            list += tasks.getTasks().get(i).toString();
            list += "\n";
        }
        System.out.println(list + line);
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
        System.out.println(line + "Noted. I've removed this task: \n" +
                toDelete.toString() + "\n" +
                "Now you have " + index + " tasks in the list. \n" + line);
    }

    public void showLoadingError() {
        System.out.println("Error Loading from Database :( ");
    }
}
