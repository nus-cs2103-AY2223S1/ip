public class Tasks {
    private String name;
    private boolean marked = false;

    public Tasks(String name) {
        this.name = name;
    }

    public void getName() {
        System.out.println(name);
    }

    public void markT() {
        marked = true;
    }

    public void unmarkT() {
        marked = false;
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + name;
        }

        return "[ ] " + name;
    }

    public static void main(String[] args) {

    }
}

