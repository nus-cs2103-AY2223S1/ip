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
        return horizontalBorder + "added: " + taskString + "\n" + horizontalBorder;
    }

    private String markDoneMessage(int position){
        Task currentTask = this.tasklist.getTask(position);
        return  horizontalBorder + "Nice! I've marked this task as done: \n" + currentTask + "\n" + horizontalBorder;
    }

    private String unmarkDoneMessage(int position){
        Task currentTask = this.tasklist.getTask(position);
        return  horizontalBorder + "OK, I've marked this task as not done yet: \n"  + currentTask + "\n" + horizontalBorder;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String run(){
        System.out.println(welcomeMessage());
        while(true) {
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            String[] commandList = s.strip().split(" ");
            String command = commandList[0];
            if (command.toLowerCase().equals("bye") && commandList.length == 1) {
                System.out.println(byeMessage());
            } else if (command.toLowerCase().equals("list") && commandList.length == 1) {
                System.out.println(listContents());
            } else if ((command.toLowerCase().equals("mark") || command.toLowerCase().equals("unmark")) && commandList.length > 1 && isInteger(commandList[1])) {
                String taskIndex = commandList[1];
                int taskIndexNum = Integer.parseInt(taskIndex);
                if (command.toLowerCase().equals("mark")) {
                    boolean isTaskMarked = this.tasklist.markTaskAtPos(taskIndexNum);
                    if (isTaskMarked){
                        System.out.println(markDoneMessage(taskIndexNum));
                    }
                } else {
                    boolean isTaskUnmarked = this.tasklist.unmarkTaskAtPos(taskIndexNum);
                    if (isTaskUnmarked) {
                        System.out.println(unmarkDoneMessage(taskIndexNum));
                    }
                }
            } else if (!s.strip().equals("")) {
                tasklist.add(new Task(s));
                System.out.println(addTaskMessage(s));
            }
        }

    }

    public static void main(String[] args) {
        Duke sampleDuke = new Duke(new Tasklist(100));
        System.out.println(sampleDuke.run());
    }
}
