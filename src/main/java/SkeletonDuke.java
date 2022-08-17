import java.util.*;
class SkeletonDuke {
    private List<Task> list;

    SkeletonDuke() {
        this.list = new ArrayList<Task>();
    }

    void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void add(String s) {
        Task newTask = new Task(s);
        list.add(newTask);
        System.out.println("added: " + s);
    }

    void getList() {
        for(int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).getFullDescription();
            System.out.println(i + ". "+ currentTask);
        }
    }

    void mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToBeModify.getFullDescription());
    }

    void unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.unmarked();
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(taskToBeModify.getFullDescription());
    }

}