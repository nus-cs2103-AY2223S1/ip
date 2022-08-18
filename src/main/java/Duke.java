import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public  Duke(){

    }
    ArrayList<Task> tasks = new ArrayList<>();
    private final static String horLine = "------------------------------------------------------";
    private final static String welcomeMsg = horLine + "\nHello! I'm Duke\n" +
            "What can I do for you?\n" + horLine;

    private void addToList(String taskName) {
        try{
            //if task is a todo
            if (taskName.matches("\\btodo\\s.*\\b")){
                Task newTask = new ToDo(taskName.substring(5));
                tasks.add(newTask);
                System.out.println(horLine + "\n\tGot it. I've added this task:\n" + "\t" + newTask.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list." + "\n" + horLine);

            }
            //if task is an event
            else if (taskName.matches("\\bevent\\s.*\\s/at\\s.*\\b")){
                String des = taskName.substring(6,taskName.indexOf("/")-1);
                String at = taskName.substring(taskName.indexOf("/")+4,taskName.length());
                Task newTask = new Event(des,at);
                tasks.add(newTask);
                System.out.println(horLine + "\n\tGot it. I've added this task:\n" + "\t" + newTask.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list." + "\n" + horLine);

            }
            ////if task is a deadline
            else if (taskName.matches("\\bdeadline\\s.*\\s/by\\s.*\\b")) {
                String des = taskName.substring(9,taskName.indexOf("/")-1);
                String by = taskName.substring(taskName.indexOf("/")+4);
                Task newTask = new Deadline(des,by);
                tasks.add(newTask);
                System.out.println(horLine + "\n\tGot it. I've added this task:\n" + "\t" + newTask.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list." + "\n" + horLine);
            }
            else if (taskName.matches("\\btodo\\s+") || taskName.matches("\\btodo\\b")) {
                throw new DukeException("Sorry please provide a task to be done!");
            }
            else {
                throw new DukeException("I'm sorry, I don't know what that means!");
            }
        } catch(Exception e) {
            System.out.println(horLine + "\n\t" + e.getMessage() + "\n" + horLine);
        }




    }


    private void userInput() {
        System.out.println(welcomeMsg);
        Scanner userCommand = new Scanner(System.in);
        String input = userCommand.nextLine();


        while (!input.equals("bye")) {
            if(input.equals("list")) {
                viewList();
            } else if (input.matches("\\bmark\\s\\d+\\b")) {
                taskDone(Integer.parseInt(input.replaceAll("[^0-9]", "")));
            } else if (input.matches("\\bunmark\\s\\d+\\b")) {
                taskUndone(Integer.parseInt(input.replaceAll("[^0-9]", "")));
            } else if (input.matches("\\bdelete\\s\\d+\\b")) {
                deleteTask(Integer.parseInt(input.replaceAll("[^0-9]", "")));
            } else {
                addToList(input);
            }
            input = userCommand.nextLine();

        }
        System.out.println(horLine + "\n\tBye. Hope to see you again soon!\n" + horLine);

    }

    private void taskDone(int num) {
        tasks.get(num-1).markAsDone();
        System.out.println(horLine + "\n\tNice! I've marked this task as done:\n" +
                "\t\t" + tasks.get(num-1).toString() + "\n" + horLine);

    }

    private void taskUndone(int num) {
        tasks.get(num-1).markAsUndone();
        System.out.println(horLine + "\n\tOK, I've marked this task as not done yet:\n" +
                "\t\t" + tasks.get(num-1).toString() +  "\n" + horLine);
    }

    private void deleteTask(int num) {
        tasks.remove(num-1);
        System.out.println(horLine + "\n\tNoted, I have removed this task: \n" +
                "\t\t" + tasks.get(num-1).toString() + "\n" + "\tNow you have " +
                tasks.size() + " tasks in the list." + "\n" + horLine);
    }

    private void viewList() {
        System.out.println(horLine + "\n");
        System.out.println("\tHere are the tasks in your list:\n");
        for(int i = 0; i<tasks.size(); i++) {
            int num = i+1;
            System.out.println("\t" + num + "." + tasks.get(i).toString());
        }
        System.out.println("\n" + horLine);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.userInput();
    }
}
