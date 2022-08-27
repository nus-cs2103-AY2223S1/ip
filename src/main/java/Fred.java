import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Fred {
    public static void save(ArrayList<Task> storage) throws IOException {
        File f = new File("data/fred.txt");
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("Fred: No data file exists. New data file has been created.");
        }

        FileWriter fw = new FileWriter("data/fred.txt");
        for (int i = 0; i < storage.size(); i++) {
            Task t = storage.get(i);
            fw.write(t.getSaveFormat());
        }
        fw.close();
        System.out.println("Fred: Tasks have been saved!");
    }

    public static void load(ArrayList<Task> storage) throws FileNotFoundException, FredException {
        File f = new File("data/fred.txt");
        Scanner s = new Scanner(f);
        Scanner scanner = s.useDelimiter("\\s\\|\\s");
        while (s.hasNext()) {
            String start = s.next();
            if (start.equals("T")) {
                String isDoneSymbol = s.next();
                s.skip("\\s\\|\\s");
                String description = s.nextLine();
                Task t = new ToDo(description, (isDoneSymbol.equals("1")));
                storage.add(t);
            } else if (start.equals("E")) {
                String isDoneSymbol = s.next();
                String description = s.next();
                s.skip("\\s\\|\\s");
                String at = s.nextLine();
                Task t = new Event(description, at, (isDoneSymbol.equals("1")));
                storage.add(t);
            } else if (start.equals("D")) {
                String isDoneSymbol = s.next();
                String description = s.next();
                s.skip("\\s\\|\\s");
                String by = s.nextLine();
                Task t = new Deadline(description, by, (isDoneSymbol.equals("1")));
                storage.add(t);
            } else {
                throw new FredException("Loading... Data file entry is wrong!");
            }
        }
        s.close();
        System.out.println("Fred: Data file has been loaded!");
    }

    public static void list(ArrayList<Task> arrayList) {
        int counter = 1;
        for (Task t : arrayList) {
            System.out.println("Fred: " + counter++ + "." + t.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> storage = new ArrayList<>();

        try {
            Fred.load(storage);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (FredException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Fred: Hello! I'm Fred!");
        System.out.println("Fred: What can I do for you?");

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Fred: Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    if (storage.size() == 0) {
                        throw new FredException("There are no items in your list!");
                    }
                    System.out.println("Fred: Here are the tasks in your list:");
                    Fred.list(storage);
                } else if (input.startsWith("mark")) {
                    if (input.trim().equals("mark")) {
                        throw new FredException("The input of mark cannot be empty!");
                    }
                    int index = Integer.parseInt(input.substring(5));
                    if (index > storage.size()) {
                        throw new FredException("Your list has only " + storage.size() + " items!");
                    } else if (index <= 0) {
                        throw new FredException("Input an integer that is greater than 0!");
                    }
                    storage.get(index - 1).setStatus(true);
                    System.out.println("Fred: Nice! I've marked this task as done:");
                    System.out.println("Fred: " + storage.get(index - 1).toString());
                } else if (input.startsWith("unmark")) {
                    if (input.trim().equals("unmark")) {
                        throw new FredException("The input of unmark cannot be empty!");
                    }
                    int index = Integer.parseInt(input.substring(7));
                    if (index > storage.size()) {
                        throw new FredException("Your list has only " + storage.size() + " items!");
                    } else if (index <= 0) {
                        throw new FredException("Input an integer that is greater than 0!");
                    }
                    storage.get(index - 1).setStatus(false);
                    System.out.println("Fred: OK, I've marked this task as not done yet:");
                    System.out.println("Fred: " + storage.get(index - 1).toString());
                } else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new FredException("The description of a todo cannot be empty.");
                    }
                    storage.add(new ToDo(input.substring(5)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("event")) {
                    if (input.trim().equals("event")) {
                        throw new FredException("The description of a event cannot be empty.");
                    }
                    int split = input.indexOf("/at");
                    if (split == -1) {
                        throw new FredException("No date/time provided! Usage: \"TASK /at DATE/TIME\"");
                    }
                    storage.add(new Event(input.substring(6, split - 1), input.substring(split + 4)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("deadline")) {
                    if (input.trim().equals("deadline")) {
                        throw new FredException("The description of a deadline cannot be empty.");
                    }
                    int split = input.indexOf("/by");
                    if (split == -1) {
                        throw new FredException("No date/time provided! Usage: \"TASK /by DATE/TIME\"");
                    }
                    storage.add(new Deadline(input.substring(9, split - 1), input.substring(split + 4)));
                    System.out.println("Fred: Got it. I've added this task:");
                    System.out.println("Fred: " + storage.get(storage.size() - 1).toString());
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.startsWith("delete")) {
                    if (input.trim().equals("delete")) {
                        throw new FredException("The input of delete cannot be empty!");
                    }
                    int index = Integer.parseInt(input.substring(7));
                    if (index > storage.size()) {
                        throw new FredException("Your list has only " + storage.size() + " items!");
                    } else if (index <= 0) {
                        throw new FredException("Input an integer that is greater than 0!");
                    }
                    String nameDeleted = storage.get(index - 1).toString();
                    storage.remove(index - 1);
                    System.out.println("Fred: Noted. I've removed this task:");
                    System.out.println("Fred: " + nameDeleted);
                    System.out.println("Fred: Now you have " + storage.size() + " tasks in your list.");
                } else if (input.equals("save")) {
                    Fred.save(storage);
                } else {
                    throw new FredException("I'm sorry, but I don't know what that means :(");
                }
            } catch (NumberFormatException e) {
                System.out.println("Fred: Must enter an integer!");
            } catch (IOException e) {
                System.out.println(e);
            } catch (FredException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
