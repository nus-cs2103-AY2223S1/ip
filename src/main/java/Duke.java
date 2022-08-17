import java.util.Scanner;

public class Duke {


    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("what you want");
    }

    public static void goodbye() {
        System.out.println("Sayonara, Adios!");
    }


    public static void main(String[] args) {
        Duke.welcome();
        Scanner sc = new Scanner(System.in);
        Task[] db = new Task[100];


        int i = 0;
        while (true) {
            String str = sc.nextLine();
                if (str.equals("bye")) {
                    Duke.goodbye();
                    break;
                }
                if (str.equals("list")) {
                    System.out.println("Here are your list of tasks!");
                    for (int j = 0; j < i; j++) {
                        int k = j + 1;
                        System.out.println(k + ". " + db[j].toString());
                    }
                } else if (str.startsWith("mark ")) {
                    int index = Integer.parseInt(str.substring(5));
                    if (index <= i) {
                        Task task = db[index - 1];
                        if (!task.isDone()) {
                            task.toggleDoneness();
                            System.out.println("Good job for doing this task!");
                            System.out.println(task);
                        } else {
                            System.out.println("This task has already been marked done.");
                            System.out.println(task);
                        }
                    } else {
                        System.out.println("Index too big, no such task exists.");
                    }

                } else if (str.startsWith("unmark ")) {
                    int index = Integer.parseInt(str.substring(7));
                    if (index <= i) {
                        Task task = db[index - 1];
                        if (task.isDone()) {
                            task.toggleDoneness();
                            System.out.println("Task shall be marked as undone.");
                            System.out.println(task);
                        } else {
                            System.out.println("This task has already been marked undone.");
                            System.out.println(task);
                        }
                    } else {
                        System.out.println("Index too big, no such task exists.");
                    }
                } else if (str.startsWith("todo ")) {
                    db[i] = new Todo(str.substring(5));
                    System.out.println("added: " + str);
                    i++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(db[i - 1]);
                    System.out.println("Now you have " + i + " tasks in the list.");
                } else if (str.startsWith("deadline ")) {
                    String[] split = str.split("/by");
                    db[i] = new Deadline(split[0].substring(9), split[1]);
                    i++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(db[i - 1]);
                    System.out.println("Now you have " + i + " tasks in the list.");
                } else if (str.startsWith("event ")) {
                    String[] split = str.split("/at");
                    db[i] = new Event(split[0].substring(6), split[1]);
                    i++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(db[i - 1]);
                    System.out.println("Now you have " + i + " tasks in the list.");
                } else {
                    db[i] = new Task(str);
                    i++;
                    System.out.println("Got it, I've added this task:");
                    System.out.println(db[i - 1]);
                    System.out.println("Now you have " + i + " tasks in the list.");
                }
            }
        }
    }

