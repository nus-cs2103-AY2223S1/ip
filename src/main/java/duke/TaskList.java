package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = tasks.size();
    }

    public void mark(int index) {
        tasks.get(index - 1).mark();
    }

    public void unmark(int index) {
        tasks.get(index - 1).unmark();
    }

    public void add(String str, Duke.Type type) {
        if (type.equals(Duke.Type.TODO)) {
            try {
                tasks.add(new Todo(str));
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
            } catch (MissingDescriptionException err) {
                System.out.println(err.toString());
            }
        } else if (type.equals(Duke.Type.DEADLINE)) {
            try {
                String[] input = str.split("/by ");
                String name = input[0].replace("deadline", "");
                LocalDate date = LocalDate.parse(input[1]);
                tasks.add(new Deadline(name, date));
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
            } catch (MissingDescriptionException err) {
                System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The end date of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println("input date in YYYY-MM-DD format!");
            }
        } else if (type.equals(Duke.Type.EVENT)) {
            try{
                String[] input = str.split("/at ");
                String name = input[0].replace("event", "");
                String[] end = input[1].split(" ");
                String date = end[0];
                LocalDate d = LocalDate.parse(date);
                String time = end[1];
                LocalTime t = LocalTime.parse(time);
                LocalDateTime dt = LocalDateTime.of(d, t);
                tasks.add(new Event(name, dt));
                count++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
            } catch (MissingDescriptionException err) {
                System.out.println("OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The time of an event cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println("Input date in YYYY-MM-DD and time in HH:MM format");
            }
        }
    }

    public void delete(String command) {
        try {
            int index = Integer.valueOf(command.split(" ")[1]);
            Task t = tasks.remove(index - 1);
            count--;
            String str =  String.format("Noted. I've removed this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.",
                    t.toString(),
                    count);
            System.out.println(str);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! No such task exists.");
        }
    }

    public String getString(int index) {
        return tasks.get(index - 1).toString();
    }

    public String toData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toData();
            data += "\n";
        }
        return data;
    }

    /**
     * searches and prints out matching tasks
     * @param keyword word to search in tasks
     */
    public void find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                matches.add(task);
            }
        }
        TaskList match = new TaskList(matches);
        String print = String.format("Here are the matching tasks in your list:\n%s", match.toString());
        System.out.println(print);
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < count; i++) {
            String index = String.format("%d.", i+1);
            String item = index + tasks.get(i).toString();
            text += item;
            text += "\n";
        }
        return text;
    }
}
