import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String horizontalBorder = "_________________________________\n";
    private Tasklist tasklist;

    private Duke(){
        this.tasklist = new Tasklist(10);
    }

    private Duke(Tasklist tasklist){
        this.tasklist = tasklist;
    }

    private static String welcomeMessage(){
        return horizontalBorder + "Hello! I'm Duke \nWhat can I do for you? \n" + horizontalBorder;
    }

    private static String byeMessage(){
        return horizontalBorder + "Bye. Hope to see you again soon! \n" + horizontalBorder;
    }

    private String listContents(){
        return horizontalBorder + this.tasklist + horizontalBorder;
    }

    public String addTaskMessage(String taskString) {
        return horizontalBorder +  "Got it. I've added this task:\n" + taskString + "\n" + "Now you have " + this.tasklist.getCount() + " tasks in the list.\n" + horizontalBorder;
    }

    private String markDoneMessage(int position){
        boolean isTaskMarked = this.tasklist.markTaskAtPos(position);
        if (isTaskMarked) {
            Task currentTask = this.tasklist.getTask(position);
            return horizontalBorder + "Nice! I've marked this task as done: \n" + currentTask + "\n" + horizontalBorder;
        } else {
            return horizontalBorder + "Task does not exist. Try another number between 1 and " + this.tasklist.getCount() + " or initialise a task first.\n" + horizontalBorder ;
        }
    }

    private String unmarkDoneMessage(int position){
        boolean isTaskUnmarked = this.tasklist.unmarkTaskAtPos(position);
        if (isTaskUnmarked) {
            Task currentTask = this.tasklist.getTask(position);
            return horizontalBorder + "OK, I've marked this task as not done yet: \n" + currentTask + "\n" + horizontalBorder;
        } else {
            return horizontalBorder + "Task does not exist. Try another number between 1 and " + this.tasklist.getCount() + " or initialise a task first.\n" + horizontalBorder;
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String makeToDoFromInput(String input){
        String description = input.substring("todo".length()).strip();
        if (!description.equals("")){
            ToDo newToDo = new ToDo(description);
            this.tasklist.add(newToDo);
            return addTaskMessage(newToDo.toString());
        } else {
            return horizontalBorder + "Please use proper todo formatting: todo {task}\n" + horizontalBorder;
        }
    }

    private String makeDeadlineFromInput(String input){
        String[] stringArray = input.substring("deadline".length()).strip().split("/by");
        if (stringArray.length > 1) {
            Deadline newDeadline = new Deadline(stringArray[0].strip(), stringArray[1].strip());
            this.tasklist.add(newDeadline);
            return addTaskMessage(newDeadline.toString());
        } else {
            return horizontalBorder + "Please use proper deadline formatting: deadline {task} /by {time}\n" + horizontalBorder;
        }
    }

    private String makeEventFromInput(String input){
        String[] stringArray = input.substring("event".length()).strip().split("/at");
        if (stringArray.length > 1) {
            Event newEvent = new Event(stringArray[0].strip(), stringArray[1].strip());
            this.tasklist.add(newEvent);
            return addTaskMessage(newEvent.toString());
        } else {
            return horizontalBorder + "Please use proper event formatting: event {task} /at {time}\n" + horizontalBorder;
        }
    }

    public String run(){
        System.out.println(welcomeMessage());
        while(true) {
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            String[] commandList = s.strip().split(" ");
            String command = commandList[0].toLowerCase();
            if (command.equals("bye") && commandList.length == 1) {
                return byeMessage();
            } else if (command.equals("list") && commandList.length == 1) {
                System.out.println(listContents());
            } else if (command.equals("mark") && commandList.length > 1 && isInteger(commandList[1])) {
                int taskIndexNum = Integer.parseInt(commandList[1]);
                System.out.println(markDoneMessage(taskIndexNum));
            } else if (command.equals("unmark") && commandList.length > 1 && isInteger(commandList[1])) {
                int taskIndexNum = Integer.parseInt(commandList[1]);
                System.out.println(unmarkDoneMessage(taskIndexNum));
            } else if (command.equals("deadline")) {
                System.out.println(makeDeadlineFromInput(s));
            } else if (command.equals("event")) {
                System.out.println(makeEventFromInput(s));
            } else if (command.equals("todo")) {
                System.out.println(makeToDoFromInput(s));
            } else if (!s.strip().equals("")) {
                System.out.println(horizontalBorder + "Please use one of these keywords: {deadline, event, todo} followed by \\\"by\\\" and \\\"at\\\" for deadline and event tasks respectively.\n" + horizontalBorder);
            }
        }
    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke(new Tasklist(100));
        System.out.println(sampleDuke.run());
    }
}
