package Duke.Tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> tasks = new ArrayList<>();




    public void addTask(Task task){
        this.tasks.add(task);
    }

    public String showTasks(){
        Task curTask;
        String output = "";
        for(int i = 0; i < tasks.size(); i++) {
            curTask = tasks.get(i);
            output += (i + 1) + "." + curTask.toString() + "\n";
        }
        return output;
    }

    public int getTotalTaskNumber() { return this.tasks.size(); }

    public Task markAsDone(int index) throws IndexOutOfBoundsException {
        Task doneTask = this.tasks.get(index);
        doneTask.setIsDone(true);               // Modify the task as done, this is a pointer
        return doneTask;
    }

    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for(Task t : this.tasks) {
            if (t.searchKeyWord(keyword)) {
                foundTasks.addTask(t);
            }
        }
        return foundTasks;
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException { return this.tasks.remove(index - 1); }

    public String save() {
        String output = "";
        for (Task task : this.tasks) {
            output += task.save();
        }
        return output;
    }


    public String sortAll() {
        this.tasks.sort((t1, t2) -> {
            if (t1.getIsDone() && !t2.getIsDone()) {
                return 1;
            }
            if (!t1.getIsDone() && t2.getIsDone()) {
                return -1;
            }
            if (t1.getDateTime() == null && t2.getDateTime() != null) {
                return 1;
            }
            if (t1.getDateTime() != null && t2.getDateTime() == null) {
                return -1;
            }
            if (t1.getDateTime() != null && t2.getDateTime() != null) {
                return t1.getDateTime().compareTo(t2.getDateTime());
            }
            return t1.getDiscription().compareTo(t2.getDiscription());

        });

        return this.showTasks();
    }

    public String sortDeadline() {
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) { deadlines.add((Deadline) task); }
        }

        deadlines.sort((d1, d2) -> {

            if (d1.getIsDone() && !d2.getIsDone()) {
                return 1;
            }

            if (!d1.getIsDone() && d2.getIsDone()) {
                return -1;
            }

            return d1.getDateTime().compareTo(d2.getDateTime());

        });


        Deadline curDeadline;
        String output = "";
        for(int i = 0; i < deadlines.size(); i++) {
            curDeadline = deadlines.get(i);
            output += (i + 1) + "." + curDeadline.toString() + "\n";
        }
        return output;


    }

    public Task getTaskByIndex(int index) { return this.tasks.get(index); }

    @Override
    public String toString() { return this.tasks.toString(); }


//    public static void main(String[] args) {
//        Deadline d1 = new Deadline("d1", LocalDate.parse("2022-11-11"), false);
//        Deadline d2 = new Deadline("d2", LocalDate.parse("2022-11-11"), LocalTime.parse("11:11"), false);
//        Deadline d3 = new Deadline("d3", LocalDate.parse("2022-11-12"), LocalTime.parse("22:22"), true);
//        Deadline d4 = new Deadline("d4", LocalDate.parse("2022-11-13"), LocalTime.parse("23:59"), false);
//
//        Deadline d5 = new Deadline("d5", LocalDate.parse("2022-11-14"), false);
//
//        System.out.print(d1.save());
//        System.out.print(d4.save());
//        System.out.print(d3.save());
//        System.out.print(d2.save());
//        System.out.print(d5.save());
//
//    }
}
