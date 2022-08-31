package duke;
public class UI {

    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";

    /*
     * Initial Greet Method for Duke
     * @return String with greet message
     */
    public String greet(){
        String greeting = "Hello! I'm Duke \n" 
                        + "What can I do for you? \n" ;
        
        encapsulateMessage(greeting);
        return greeting;
    }

    /*
     * UI for exit message for the bot
     */
    public void exit(){
        String bye = "Bye. Hope to see you again soon!";
        encapsulateMessage(bye);
    }

    /*
     * UI For printing the list of tasks
     * @param taskList containing list of tasks
     */
    protected void printList(TaskList taskList) {
        encapsulateMessage(taskList.toString());
    }

    /*
     * UI to update user once message has been marked
     * @param item the task to be added
     */
    protected void markedMsg(Task item) {
        String itemMessage = "Nice! I've marked this task as done: \n"
        + item.toString();
        encapsulateMessage(itemMessage);
    }

    /*
     * UI to update user once message has been removed
     * @param removed the task to be removed
     * @param taskSize the amount of tasks remaining
     */
    protected void rmvMsg(Task removed, int taskSize) {
        String rmvMsg = "Noted. I've removed this task: \n"
        + removed.toString()
        + "\n Now you have %d tasks in the list.";
        rmvMsg = String.format(rmvMsg, taskSize);
        echo(rmvMsg);
    }

    /*
     * UI to update user once message has been unmarked
     * @param item the Task to be unmarked
     */
    protected void unmarkedMsg(Task item) {
        String itemMessage = "OK, I've marked this task as not done yet: \n"
        + item.toString();
        encapsulateMessage(itemMessage);
    }

    private static void encapsulateMessage(String message){
        String[] messages = message.split("\n");
        drawLine();
        for (String msg : messages){
            System.out.println(indentation + " " + msg);
        }
        drawLine();
    }

    /*
     * UI to update user once Task has been added to List
     * @param listSize number of tasks after task addition
     * @param newTask the Task to be added
     */
    protected void addToListMsg(int listSize, Task newTask) {
        String addedMsg = "Got it. I've added this task: \n"
                                + newTask.toString()
                                + "\n Now you have %d tasks in the list.";
        addedMsg = String.format(addedMsg, listSize);
        echo(addedMsg);
    }

    public void echo(String command){
        encapsulateMessage(command);
    }

    private static void drawLine(){
        System.out.println(indentation + horizontalLine + "\n");
    }


}
