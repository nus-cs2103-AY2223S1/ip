import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);

    public void printList() {
        if (tasks.size() == 0) {
            System.out.println(Duke.start + "your list is empty. start adding some tasks to do now!");
        } else {
            System.out.println(Duke.start + "these are the tasks in your list:");
            int x = 1;
            for (Task task : tasks) {
                System.out.println("  " + x + ". " + task.toString());
                x++;
            }
        }
    }

    public void add(Task t) {
        tasks.add(t);
        System.out.println(Duke.start + "added:\n" + "     " + t);
        if (tasks.size() == 1) {
            System.out.println("  you now have 1 task in the list. type list to see it!");
        } else {
            System.out.println("  now you have " + tasks.size() + " tasks in the list. type list to view them.");
        }
    }

    public void addFromFile(String[] info) {
        Task t;
        if (info[0].equals("T")) {
            t = new ToDo(info[2]);
        } else if (info[0].equals("D")) {
            if (info.length == 4) {
                t = new Deadline(info[2], LocalDate.parse(info[3]));
            } else {
                t = new Deadline(
                        info[2], LocalDate.parse(info[3]),
                        LocalTime.parse(info[4]));
            }
        } else {
            try {
                t = new Event(
                        info[2], info[3], LocalDateTime.parse(info[4]),
                        LocalDateTime.parse(info[5]));
            } catch (DateTimeParseException e) {
                t = new Event(
                        info[2], info[3], LocalDateTime.parse(info[4]),
                        LocalTime.parse(info[5]));
            }
        }
        add(t);
        if (info[1].equals("1")) {
            t.setDone();
        }
    }

    public void delete(int i) {
        if (tasks.size() == 0) {
            System.out.println(
                    Duke.start + "hmm, you do not have any tasks in your list to delete. add some now!"
            );
        } else {
            try {
                Task t = tasks.remove(i);
                System.out.println(Duke.start + "okay! i have deleted the following task from your list:");
                System.out.println("     " + t);
                if (tasks.size() == 0) {
                    System.out.println("  your list is now empty. time to add some more!");
                } else if (tasks.size() == 1) {
                    System.out.println("  you now have 1 task remaining in the list. type list to see it!");
                } else {
                    System.out.println("  now you have " + tasks.size() + " tasks in the list. type list to view them.");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Duke.sadFace + "please enter an integer from 1 - " + tasks.size());
            }
        }
    }

    public void search(LocalDate date) {
        String list = "";
        int x = 1;
        for (Task task : tasks) {
            if (task.happensOn(date)) {
                list = list + "\n  " + x + ". " + task;
                x++;
            }
        }
        if (x == 1) {
            System.out.println(
                    Duke.start + "There are no tasks occurring on " + date + ".");
        } else {
            System.out.println(
                    Duke.start + "These are the tasks occurring on " + date + ":" + list
            );
        }
    }

    public void mark(int t) {
        try {
            if (tasks.size() == 0) {
                System.out.println(
                        Duke.start + "hmm, you do not have any tasks in your list to be marked. add some now!"
                );
            } else {
                Task doneTask = tasks.get(t);
                doneTask.setDone();
                System.out.println(Duke.start + "good job! this task has been marked as done:");
                System.out.println("     " + doneTask);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Duke.sadFace + "please enter an integer from 1 - " + tasks.size());
        }
    }

    public void unmark(int t) {
        try {
            if (tasks.size() == 0) {
                System.out.println(Duke.start + "hmm, you do not have any tasks in your list. add some now!");
            } else {
                Task undoneTask = tasks.get(t);
                undoneTask.setUndone();
                System.out.println(Duke.start + "ok, i've marked this task as not done yet:");
                System.out.println("     " + undoneTask);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Duke.sadFace + "please enter an integer from 1 - " + tasks.size());
        }
    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("src/main/data/list.txt");
            for (Task task : tasks) {
                fw.write(task.fileDescription() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
