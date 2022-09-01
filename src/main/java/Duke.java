import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

//        Task[] list = new Task[100];

        ArrayList<Task> list = new ArrayList<>();
        ArrayList<String> storage = new ArrayList<>();
        int i = 0;
        boolean loop = true;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("savedList.txt"));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("todo")) {
                    storage.add(line);
                    String task = line.replace("todo", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Todo todo = new Todo(task);
                        todo.markAsDone();
                        list.add(todo);
                    } else {
                        Todo todo = new Todo(task);
                        list.add(todo);
                    }
                    i++;

                } else if (line.contains("deadline")) {
                    storage.add(line);
                    String task = line.replace("deadline", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Deadline deadline = new Deadline(task);
                        deadline.markAsDone();
                        list.add(deadline);
                    } else {
                        Deadline deadline = new Deadline(task);
                        list.add(deadline);
                    }
                    i++;

                } else if (line.contains("event")) {
                    storage.add(line);
                    String task = line.replace("event", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Event event = new Event(task);
                        event.markAsDone();
                        list.add(event);
                    } else {
                        Event event = new Event(task);
                        list.add(event);
                    }
                    i++;
                }
            }
            reader.close();
        } catch (IOException e) {}

        System.out.println("Hello! Duke here, how can I help you?");
        Scanner sc = new Scanner(System.in);

        while (loop) {
            if (sc.hasNext("bye")) {
                loop = false;
                break;
            }

            if (sc.hasNext("list")) {
                if (list.size() == 0) {
                    System.out.println("You have no tasks currently!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < list.size(); j++) {
                        System.out.println(j+1 + ". " + list.get(j).getTask());
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("mark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        if (!storage.get(input - 1).contains(" | X")) {
                            String temp = storage.get(input - 1) + " | X";
                            storage.remove(input - 1);
                            storage.add(input - 1, temp);
                        }
                        list.get(input - 1).markAsDone();
                        System.out.println("Good job! Task " + input + " has been completed:");
                        System.out.println("  " + list.get(input - 1).getTask());
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to mark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("unmark")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        if (storage.get(input - 1).contains(" | X")) {
                            String temp = storage.get(input - 1).replace(" | X", "");
                            storage.remove(input - 1);
                            storage.add(input - 1, temp);
                        }
                        list.get(input - 1).markAsUndone();
                        System.out.println("Got it! Task " + input + " has not yet been completed:");
                        System.out.println("  " + list.get(input - 1).getTask());
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to unmark!");
                    }
                }
                sc.nextLine();
                continue;
            }

            if (sc.hasNext("delete")) {
                sc.next();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    try {
                        Task removedTask = list.get(input - 1);
                        list.remove(input - 1);
                        i--;
                        System.out.println("Got it! Task " + input + " has been deleted from the list:");
                        System.out.println("  " + removedTask.getTask());
                        System.out.println("You have a total of " + i + " tasks in the list.");
                        storage.remove(input - 1);
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("You've given me an invalid task to delete!");
                    }
                }
                sc.nextLine();
                continue;
            }

            String str = sc.next();
            String store = str;

            if (str.contains("todo")) {
                String task = sc.nextLine();
                store = store + task;
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else {
                    storage.add(store);
                    Todo todo = new Todo(task);
                    list.add(todo);
                    i++;
                    System.out.println("I've added this task to the list:");
                    System.out.println("  " + todo.getTask());
                    System.out.println("You have a total of " + i + " tasks in the list.");
                }
            } else if (str.contains("deadline")) {
                String task = sc.nextLine();
                store = store + task;
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else if (!task.contains("/by")) {
                    throw new DukeException("Invalid input for a deadline!");
                } else {
                    Deadline deadline = new Deadline(task);
                    try {
                        String taskDescription = deadline.getTask();
                        storage.add(store);
                        list.add(deadline);
                        i++;
                        System.out.println("I've added this task to the list:");
                        System.out.println("  " + taskDescription);
                        System.out.println("You have a total of " + i + " tasks in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("Invalid date input!");
                    }
                }
            } else if (str.contains("event")) {
                String task = sc.nextLine();
                store = store + task;
                if (task.equals("")) {
                    throw new DukeException("The description of a task cannot be empty!");
                } else if (!task.contains("/at")) {
                    throw new DukeException("Invalid input for an event!");
                } else {
                    Event event = new Event(task);
                    try {
                        String taskDescription = event.getTask();
                        storage.add(store);
                        list.add(event);
                        i++;
                        System.out.println("I've added this task to the list:");
                        System.out.println("  " + taskDescription);
                        System.out.println("You have a total of " + i + " tasks in the list.");
                    } catch (NullPointerException e) {
                        System.out.println("Invalid date input!");
                    }
                }
            } else {
                throw new DukeException("Invalid input!");
            }
        }

        System.out.println("Thanks for having me!\nHave a nice day ahead!");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savedList.txt"));
            for (int j = 0; j < storage.size(); j++) {
                writer.write(storage.get(j) + "\n");
            }
            writer.close();
        } catch (IOException e) {}
    }
}
