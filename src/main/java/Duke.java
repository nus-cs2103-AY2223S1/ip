import java.util.Scanner;

public class Duke {
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void bye() {
        System.out.println("Sayonara, Adios!");
    }

    public static void main(String[] args) {
        Duke.welcome();
        Scanner sc = new Scanner(System.in);
        Task[] db = new Task[100];


        int i = 0;
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equals("bye")) {
                    Duke.bye();
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
                    String sub = str.substring(5).trim();
                    if (!sub.isEmpty()) {
                        db[i] = new Todo(str.substring(5));
                        i++;
                        System.out.println("Got it, I've added this task:");
                        System.out.println(db[i - 1]);
                        System.out.println("Now you have " + i + " tasks in the list.");
                    } else {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (str.equals("todo")){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (str.startsWith("deadline ")) {
                    String sub = str.substring(9).trim();
                    if (str.contains("/by")) {
                        String[] split = sub.split("/by");
                        System.out.println(split.length);
                        if (split.length < 2) {
                            throw new DukeException("Please specify the deadline.");
                        } else {
                            db[i] = new Deadline(split[0], split[1]);
                            i++;
                            System.out.println("Got it, I've added this task:");
                            System.out.println(db[i - 1]);
                            System.out.println("Now you have " + i + " tasks in the list.");
                        }
                    } else {
                        throw new DukeException("Please specify the deadline by using \"/by\".");
                    }
                } else if (str.startsWith("event ")) {
                    String sub = str.substring(6).trim();
                    if (str.contains("/at")) {
                        String[] split = sub.split("/at");
                        db[i] = new Event(split[0], split[1]);
                        i++;
                        System.out.println("Got it, I've added this task:");
                        System.out.println(db[i - 1]);
                        System.out.println("Now you have " + i + " tasks in the list.");
                    } else {
                        throw new DukeException("Please specify the event date by using \"/at\"");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } catch (NumberFormatException e) {
                System.out.println("Invalid index input, please try again.");
            }
        }
    }
}

