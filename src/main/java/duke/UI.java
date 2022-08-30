package duke;
public class UI {

    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";


    public String greet(){
        String greeting = "Hello! I'm Duke \n" 
                        + "What can I do for you? \n" ;
        
        encapsulateMessage(greeting);
        return greeting;
    }

    public void exit(){
        String bye = "Bye. Hope to see you again soon!";
        encapsulateMessage(bye);
    }

    protected void printList(TaskList taskList) {
        encapsulateMessage(taskList.toString());
    }

    protected void markedMsg(Task item) {
        String itemMessage = "Nice! I've marked this task as done: \n"
        + item.toString();
        encapsulateMessage(itemMessage);
    }

    protected void rmvMsg(Task removed, int taskSize) {
        String rmvMsg = "Noted. I've removed this task: \n"
        + removed.toString()
        + "\n Now you have %d tasks in the list.";
        rmvMsg = String.format(rmvMsg, taskSize);
        echo(rmvMsg);
    }

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
