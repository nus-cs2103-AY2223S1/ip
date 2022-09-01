package duke.tasks;

public abstract class Task {

    private final String name;
    private boolean marked;

    public Task(String name) {
        this.name = name;
    }

    public static Task fromDataString(String dataString) {
        String[] arr = dataString.split("\\|");
        Task ret;
//        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }

        switch(arr[0]) {
            case "[T]":
                ret = new Todo(arr[2]);
                break;
            case "[D]":
                ret = new Deadline(arr[2], arr[3]);
                break;
            case "[E]":
                ret = new Event(arr[2], arr[3]);
                break;
            default:
                ret = new Todo(arr[2]);
        }

        if ("1".equals(arr[1])) {
            ret.mark();
        }

        return ret;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    public boolean isMarked() {
        return marked;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + name;
    }

    public abstract String toDataString();

}
