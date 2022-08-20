import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

        //Chatbot intro segment
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hewwo! I'm\n" + logo + "\nWhat can I do for you?");

        //Initialising task array for list cmd
        ArrayList<Task> taskList = new ArrayList<>(100);
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
                System.out.println("Here are your tasks =0w0=");

                for (int i = 0; i < numOfInputs; i++) {
                    task = taskList.get(i);

                    if (task.isDone()) { //task is done
                        System.out.println((i + 1) + ". " + task);

                    } else { //task is not done
                        System.out.println((i + 1) + ". " + task);
                    }
                }
                continue;

            case "mark":
                try {
                    taskNum = sc.nextInt();
                    if (taskNum <= numOfInputs) {
                        task = taskList.get(taskNum - 1);
                        task.markAsDone();
                        System.out.println("Good job (=OwO=) You finished this task! \n" + task);
                        continue;
                    } else {
                        throw new DukeException("Meowmeow there isn't a task with that number uwu");
                    }
                } catch (DukeException e) {
                    System.out.println(e.message);
                    continue;
                }

            case "unmark":
                try {
                    taskNum = sc.nextInt();
                    if (taskNum <= numOfInputs) {
                        task = taskList.get(taskNum - 1);
                        task.markAsNotDone();

                        System.out.println("uwu this task has been marked as not done... \n" + task);
                        continue;
                    } else {
                        throw new DukeException("Meowmeow there isn't a task with that number uwu");
                    }

                } catch (DukeException e){
                    System.out.println(e.message);
                    continue;
                }

            case "todo":
                try {
                    userInput = sc.nextLine();
                    if (userInput == null || userInput.equals("")) {
                        throw new DukeException("Meowmeow needs a name for the task you want to add (=^0w0^=)");
                    } else {
                        Task t = new ToDos(userInput);
                        taskList.add(t);
                        numOfInputs += 1;

                        System.out.println("(=^-w-^=) " + t + " has been added to your task list!\n");
                        System.out.println("You now have " + numOfInputs + " tasks >w<");
                        continue;
                    }
                } catch (DukeException e){
                    System.out.println(e.message);
                }
                userInput = sc.nextLine();
                Task t = new ToDos(" " + userInput);
                taskList.add(t);
                numOfInputs += 1;

                System.out.println("(=^-w-^=) " + t + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
                continue;

            case "deadline":
                userInput = sc.nextLine();
                String[] splitB = userInput.split("/by");
                String deadline = splitB[1];
                Task d = new Deadlines(splitB[0], deadline);
                taskList.add(d);
                numOfInputs += 1;

                System.out.println("(=^-w-^=) " + d + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
                continue;

            case "event":
                userInput = sc.nextLine();
                String[] splitA = userInput.split("/at");
                String time = splitA[1];
                Task e = new Events(splitA[0], time);
                taskList.add(e);
                numOfInputs += 1;

                System.out.println("(=^-w-^=) " + e + " has been added to your task list!\n");
                System.out.println("You now have " + numOfInputs + " tasks >w<");
                continue;

            case "delete":
                try {
                    taskNum = sc.nextInt() - 1;
                    if (taskNum >= numOfInputs) {
                        throw new DukeException("Meowmeow can't throw away a task that doesn't exist =owo=");
                    } else {
                        System.out.println("Meowmeow has thrown this task into the void!! (=^>w<^=) \n" + taskList.get(taskNum));
                        taskList.remove(taskNum);
                        numOfInputs -= 1;
                        System.out.println("You have " + numOfInputs + " tasks left now Owo");
                        continue;
                    }
                } catch (DukeException x) {
                    System.out.println(x.message);
                    continue;
                }

            case "bye":
                System.out.println("UwU Byebyeeee! Come back soon... Meowmeow misses you already =^._.^= ");
                sc.close();
                break;

            default:
                System.out.println("Sowwie meowmeow doesn't understand what you said uwu");
                continue;
            }
        }
        System.out.println("(uwu) Meowmeow you have too many tasks");
    }
}
