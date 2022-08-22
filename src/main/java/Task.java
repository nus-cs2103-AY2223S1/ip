import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    public static ArrayList<Task> tasks;
    private static String sep = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tasks.add(this);
        updateTasks();
    }

    public void print(Task task) {
        if (Task.tasks.size() == 1) {
            System.out.println(sep + "\nLuna has added:\n" + task.toString() + "\nThere is currently " + Task.tasks.size() + " task in your list 🌻\n" + sep);
        } else {
            System.out.println(sep + "\nLuna has added:\n" + task.toString() + "\nThere are currently " + Task.tasks.size() + " tasks in your list 🌻\n" + sep);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "✧" : " "); // mark done task with X
    }

    public void setStatusIcon(boolean status) {
        this.isDone = status;
    }

    public void unmark() {
        this.setStatusIcon(false);
        System.out.println(sep + "\nMarked as uncompleted 🌩\n  " + this.toString() + "\n" + sep);
        updateTasks();
    }

    public void mark() {
        this.setStatusIcon(true);
        System.out.println(sep + "\nMarked as completed 🌈️\n  " + this.toString() + "\n" + sep);
        updateTasks();
    }

    public static void delete(int num) {
        Task removed = tasks.remove(num - 1);
        if (Task.tasks.size() == 1) {
            System.out.println(sep + "\nLuna has removed:\n" + removed + "\nThere is currently ️" + Task.tasks.size() + " task️ in your list  🌻\n" + sep);
        } else {
            System.out.println(sep + "\nLuna has removed:\n" + removed + "\nThere are currently ️" + Task.tasks.size() + " tasks in your list 🌻\n" + sep);
        }
        updateTasks();
    }

    public static void list() {
        System.out.println(sep + "\n☀️ Stuff you have to do! ☀️\n");
        for(int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            } else {
                System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
            }
        }
        System.out.println(sep);
    }

    public static void updateTasks() {
        try {
            FileWriter writer = new FileWriter("./data/Luna.txt");
            String content = "  Luna finds the following items saved in your list 🍃";
            for (int  i = 0; i < tasks.size(); i++) {
                content += "\n      " + tasks.get(i).toString();
            }
            writer.write(content);
            writer.close();
        } catch (IOException e) {
//            System.out.println("⚡️Luna has encountered an error while updating tasks⚡️" +
//                                "\n️Please exit and try again ️⛈");
        }

    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
