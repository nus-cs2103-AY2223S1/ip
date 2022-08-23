import java.util.*;
public class Duke {
    private Task task;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Duke newDuke = new Duke();
        newDuke.chatDuke();


        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }

    public void chatDuke(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<>();
        String str = sc.nextLine();
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon");
        }
        while (!str.equals("bye")){
            if (str.equals("list")) {
                returnArrayStrings(taskArray);
                str = sc.nextLine();
            }

            else if (str.equals("mark")) {
                int num = sc.nextInt();
                Task currTask = taskArray.get(num - 1);
                currTask.mark();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + currTask.getStatusIcon() + "] " + currTask.description);
                str = sc.nextLine();
                str = sc.nextLine();
            }

            else if (str.equals("unmark")) {
                int num = sc.nextInt();
                Task currTask = taskArray.get(num - 1);
                currTask.unMark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[" + currTask.getStatusIcon() + "] " + currTask.description);
                str = sc.nextLine();
                str = sc.nextLine();
            }

            else {
                taskArray.add(new Task(str));
                System.out.println("added: " + str);
                str = sc.nextLine();
            }



        }
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon");
        } else {
            taskArray.add(new Task(str));
            str = sc.nextLine();
        }




    }

    public void returnArrayStrings(ArrayList<Task> tskArray){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tskArray.size(); i++){
            if (tskArray.get(i) != null) {
                System.out.println(Integer.toString(i + 1) + "." + tskArray.get(i));
            } else {
                return;
            }
        }
    }



}

