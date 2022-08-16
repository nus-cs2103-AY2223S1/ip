import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy");
        System.out.println("What can I do for you?");
        String input = myObj.nextLine();
        String[] words = input.split(" ");
        String response = words[0];
        Task[] tasks = new Task[100];
        int i=0;
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int a=1; a<=i; a++) {
                    String output = a + "." + tasks[a-1].toString();
                    System.out.println(output);
                }
            } else if (response.equals("mark")) {
                String taskNumber = words[1];
                int number = Integer.parseInt(taskNumber);
                tasks[number-1].markAsDone();
            } else if (response.equals("unmark")) {
                String taskNumber = words[1];
                int number = Integer.parseInt(taskNumber);
                tasks[number-1].markNotDone();
            } else if (response.equals("deadline")){
                int a = 2;
                StringBuilder task = new StringBuilder(words[1]);
                while (!words[a].equals("/by")) {
                    task.append(" ");
                    task.append(words[a]);
                    a++;
                }
                a++;
                StringBuilder deadline = new StringBuilder(words[a]);
                for (int b=a+1; b<words.length; b++) {
                    deadline.append(" ");
                    deadline.append(words[b]);
                }
                tasks[i] = new Deadline(task.toString(), deadline.toString());
                int numOfTasks = i+1;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[i]);
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
                i++;
            }
            input = myObj.nextLine();
            words = input.split(" ");
            response = words[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
