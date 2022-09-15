package duke;
public class UI {

    /*
     * Initial Greet Method for Duke.
     * @return String with greet message
     */
    public String greet(){
        String greeting = "Hello! I'm chatNUS! \n" 
                        + "What can I do for you? \n" ;
        
        return greeting;
    }

    /*
     * UI for exit message for the bot.
     */
    public String exit(){
        String bye = "Bye. Hope to see you again soon!";
        return bye;
    }

    /*
     * UI For printing the list of tasks.
     * @param taskList containing list of tasks
     */
    protected String printList(TaskList taskList) {
        return taskList.toString();
    }

    /*
     * UI to update user once message has been marked.
     * @param item the task to be added
     */
    protected String markedMsg(Task item) {
        String itemMessage = "Nice! I've marked this task as done: \n"
        + item.toString();
        return itemMessage;
    }

    /*
     * UI to update user once message has been removed.
     * @param removed the task to be removed
     * @param taskSize the amount of tasks remaining
     */
    protected String rmvMsg(Task removed, int taskSize) {
        String rmvMsg = "Noted. I've removed this task: \n"
        + removed.toString()
        + "\n Now you have %d tasks in the list.";
        rmvMsg = String.format(rmvMsg, taskSize);
        return rmvMsg;
    }

    /*
     * UI to update user once message has been unmarked.
     * @param item the Task to be unmarked
     */
    protected String unmarkedMsg(Task item) {
        String itemMessage = "OK, I've marked this task as not done yet: \n"
        + item.toString();
       return itemMessage;
    }

    /*
     * UI to update user once Task has been added to List.
     * @param listSize number of tasks after task addition
     * @param newTask the Task to be added
     */
    protected String addToListMsg(int listSize, Task newTask) {
        String addedMsg = "Got it. I've added this task: \n"
                                + newTask.toString()
                                + "\n Now you have %d tasks in the list.";
        addedMsg = String.format(addedMsg, listSize);
        return addedMsg;
    }
}
