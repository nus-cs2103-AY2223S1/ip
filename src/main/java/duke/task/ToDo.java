package duke.task;

public class ToDo extends Task {

    private ToDo(String taskText) {

        super(taskText.trim());
    }

    public static ToDo of(String taskText, String source) {
        if (source.contentEquals("FILE")) {
            String[] detailArr = taskText.replace('|', '/').split("/", 2);
            ToDo toDo = new ToDo(detailArr[1]);
            if (detailArr[0].contains("X")) {
                toDo.done();
            }
            return toDo;
        } else {
            return new ToDo(taskText);
        }

    }

    @Override
    public String toString(){

        return "T" + super.toString();
    }
}
