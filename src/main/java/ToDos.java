public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String testTest, int x) {
        super(testTest);
    }

    public String getToDoDescirption() {return description;}

    public static String getItem() {
        return "[T]";
    }


}
