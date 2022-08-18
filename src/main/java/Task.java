public class Task {
    private String status;
    private String name;
    public Task(String name) {
        status = "[ ]";
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public  String getStatus() {
        return status;
    }
    public void mark(int index) {
        System.out.println(
                Duke.line + "\n" +
                        "Nice! I've marked this task as done:" + "\n" +
                        "[X] " + Duke.list[index-1].getName() + "\n" + Duke.line
        );
        Duke.list[index - 1].status = "[X]";
    }

    public void unmark(int index) {
        System.out.println(
                Duke.line + "\n" +
                        "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + Duke.list[index-1].getName() + "\n" + Duke.line
        );
        Duke.list[index - 1].status = "[ ]";
    }


    public void list() {
        System.out.println(
                Duke.line);

        for (int i = 0, j = 1; i < Duke.count; i++, j++) {

            System.out.println(j + ". " + Duke.list[i].status + Duke.list[i].name );
        }
        System.out.println(
                Duke.line + "\n"
        );

    }
}
