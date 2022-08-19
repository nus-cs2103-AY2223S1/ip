import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //Chatbot intro segment
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hewwo! I'm\n" + logo + "\nWhat can I do for you?");

        //Initialising task array for list cmd
        Task[] taskList = new Task[100];
        int numOfInputs = 0;

        //Initialising scanner
        Scanner sc = new Scanner(System.in);
        String userInput;

        //Loop continues until bye cmd is given
        while (numOfInputs < 100) {
            userInput = sc.next();
            Task task;
            int taskNum;

            switch (userInput) {

                case "list":
                    System.out.println("Here are your tasks =ôwô=");

                    for (int i = 0; i < numOfInputs; i++) {
                        task = taskList[i];

                        if (task.isDone()) { //task is done
                            System.out.println((i + 1) + ". " + task);

                        } else { //task is not done
                            System.out.println((i + 1) + ". " + task);
                        }
                    }
                    continue;

                case "mark":
                    taskNum = sc.nextInt();
                    task = taskList[taskNum - 1];
                    task.markAsDone();
                    System.out.println("Good job (=ↀωↀ=) You finished this task! \n" + task);
                    continue;

                case "unmark":
                    taskNum = sc.nextInt();
                    task = taskList[taskNum - 1];
                    task.markAsNotDone();
                    System.out.println("uwu this task has been marked as not done... \n" + task);
                    continue;

                case "todo":
                    userInput = sc.nextLine();
                    Task t = new ToDos(userInput);
                    taskList[numOfInputs] = t;
                    numOfInputs += 1;
                    System.out.println("(=^-ω-^=) " + t + " has been added to your task list!\n");
                    System.out.println("You now have " + numOfInputs + " tasks");
                    continue;

                case "deadline":
                    userInput = sc.nextLine();
                    String[] splitB = userInput.split("/by");
                    String deadline = splitB[1];
                    Task d = new Deadlines(splitB[0], deadline);
                    taskList[numOfInputs] = d;
                    numOfInputs += 1;
                    System.out.println("(=^-ω-^=) " + d + " has been added to your task list!\n");
                    System.out.println("You now have " + numOfInputs + " tasks");
                    numOfInputs += 1;
                    continue;

                case "event":
                    userInput = sc.nextLine();
                    String[] splitA = userInput.split("/at");
                    String time = splitA[1];
                    Task e = new Events(splitA[0], time);
                    taskList[numOfInputs] = e;
                    numOfInputs += 1;
                    System.out.println("(=^-ω-^=) " + e + " has been added to your task list!\n");
                    System.out.println("You now have " + numOfInputs + " tasks");
                    continue;

                case "bye":
                    System.out.println("UwU Come back soon meowmeow misses youuuu!");
                    sc.close();
                    break;

                default:
                    System.out.println("Sowwie meowmeow doesn't understand what you said uwu");
                    continue;
            }
        }
        System.out.println("(；¬＿¬) Meowmeow you have too many tasks");
    }
}
