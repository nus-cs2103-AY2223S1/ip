import java.util.*;
class SkeletonDuke {
    private List list;

    SkeletonDuke() {
        this.list = new ArrayList<String>();
    }

    void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void add(String s) {
        list.add(s);
        System.out.println("added: " + s);
    }

    void getList() {
        for(int i = 1; i < list.size() + 1; i++) {
            String current = list.get(i - 1).toString();
            System.out.println(i + ". "+ current);
        }
    }

}