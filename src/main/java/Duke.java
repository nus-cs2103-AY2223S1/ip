import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //Chatbot intro segment
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hewwo! My name is\n" + logo + "\nWhat can I do for you?");

        //Initialising task array for list cmd
        Task[] taskList = new Task[100];
        int numOfInputs = 0;

        //Initialising scanner
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        //Loop continues until bye cmd is given
        while (!userInput.equals("bye")) {
            userInput = sc.next();

            //When list cmd is given
            if (userInput.equals("list")) {
                System.out.println("Here are your tasks =ôwô=");

                for (int i = 0; i < numOfInputs; i++) {
                    Task task = taskList[i];

                    if (task.isDone()) { //task is done
                        System.out.println((i + 1) + ". " + task);

                    } else { //task is not done
                        System.out.println((i + 1) + ". " + task);
                    }
                }

            } else if (userInput.equals("mark")) { //mark task as done
                int taskNum = sc.nextInt();
                Task completedTask = taskList[taskNum - 1];
                completedTask.markAsDone();
                System.out.println("Good job (=ↀωↀ=) You finished this task! \n" + completedTask);

            } else if (userInput.equals("unmark")) { //mark task as undone
                int taskNum = sc.nextInt();
                Task undoneTask = taskList[taskNum - 1];
                undoneTask.markAsNotDone();
                System.out.println("uwu this task has been marked as not done... \n" + undoneTask);

            } else {
                //Add a task to list
                Task currTask = new Task(userInput);
                taskList[numOfInputs] = currTask;
                numOfInputs += 1;
            }
        }

        //when bye cmd is given
        System.out.println("UwU Come back soon meowmeow misses youuuu!");
        sc.close();
    }
}
