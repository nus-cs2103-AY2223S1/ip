public class Ui {


    public void dukeReply(String message) {
        String lineBreak = "-------------------------------------------------";
        String reply = String.format("%s\n%s\n%s", lineBreak, message, lineBreak);
        System.out.println(reply);
    }

    public void greet() {
        dukeReply("Hello! I'm Ee Suan!\nWhat can I do for you?");
    }

    public void exit() {
        dukeReply("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            Task curTask = taskList.get(i);
            message +="\n" + (i + 1) + ". " + curTask;
        }
        dukeReply(message);
    }

    public void echoTask(Task task, TaskList taskList) {
        int size = taskList.size();
        dukeReply("Got it. I've added this task: \n  " + task + "\nNow you have " + size + " task(s) in the list.");
    }

    public void echoDelete(Task task, TaskList taskList) {
        dukeReply("Noted. I've removed this task:\n  " + task +"\nNow you have " + taskList.size() + " task(s) in the list." );
    }
}
