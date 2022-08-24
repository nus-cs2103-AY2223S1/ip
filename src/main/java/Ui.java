public class Ui {
    private final String LINE_BREAK = "------------------";
    private final String GREETING_MESSAGE = "\n Hello I am LUNA!\n How can I be of help?\n";
    private final String GOODBYE_MESSAGE = "\nLUNA bids farewell\n\n May the Moon shine bright and illuminate your night.";
    private final String UNMARK_MESSAGE =  "LUNA thought you were already done with this?\n";
    private final String MARK_MESSAGE = "LUNA waited many moons for you to finish this one\n";
    private final String DELETE_MESSAGE = "LUNA has deleted that task... Erased forever, lost in the depth of Space...\n";
    private final String TODO_MESSAGE = "LUNA will write this one down on her finest crater...:\n";
    private final String EVENT_MESSAGE = "What event could possibly be more important than the Moon Festival?\n";
    private final String DEADLINE_MESSAGE = "Deadlines... LUNA isn't too good with those...\n";

    public void greetingMessage() {
        System.out.println(this.GREETING_MESSAGE + this.LINE_BREAK);
    }

    public void goodbyeMessage() {
        System.out.println(this.LINE_BREAK + GOODBYE_MESSAGE);
    }

    public void unmarkMessage(String taskName) {
        System.out.println(UNMARK_MESSAGE + taskName + "\n" + LINE_BREAK);
    }

    public void markMessage(String taskName) {
        System.out.println(MARK_MESSAGE + taskName + "\n" + LINE_BREAK);
    }

    public void deleteMessage(String taskName, int len) {
        System.out.println(DELETE_MESSAGE + taskName + "\n" + LINE_BREAK);
    }

    public void todoMessage(ToDos todo, int len) {
        System.out.println(LINE_BREAK + "\n" + TODO_MESSAGE + todo + "\n" + LINE_BREAK);
        lengthMessage(len);
    }

    public void eventMessage(Events event, int len) {
        System.out.println(LINE_BREAK + "\n" + EVENT_MESSAGE + event + "\n" + LINE_BREAK);
        lengthMessage(len);
    }

    public void deadlineMessage(Deadlines deadline,int len) {
        System.out.println(LINE_BREAK + "\n" + DEADLINE_MESSAGE + deadline + "\n" + LINE_BREAK);
        lengthMessage(len);
    }

    public void lengthMessage(int len) {
        System.out.println("You now have " + len + " tasks added\n" + "How many moons before you complete them?" + "\n" + LINE_BREAK);
    }

    public void lineBreak() {
        System.out.println(LINE_BREAK);
    }
}
