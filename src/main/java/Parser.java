import java.io.IOException;

public class Parser {
    private AddCommand add;
    private DeleteCommand delete;
    private MarkCommand mark;
    private UnMarkCommand unmark;
    private Ui ui;
    private Storage stor;
    private TaskList arrayLL;
    private int type;
    public Parser(Ui ui, Storage stor, TaskList arrayLL) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
    }

    public void checkText(String userText) throws NeoException, IOException {

        if (userText.equals("list") || userText.equals("List")) {
            stor.Retrievedata();
        }
        String arr[];
        arr = userText.split(" ", 2);
        if (arr.length>1 && arr[0].equals("mark")){
            this.mark = new MarkCommand(ui, stor, arrayLL);
            mark.complete(arr[1]);
        }
        if (arr.length>1 && arr[0].equals("unmark")){
            this.unmark = new UnMarkCommand(ui, stor, arrayLL);
            unmark.complete(arr[1]);
        }
        if (arr.length>1 && arr[0].equals("delete")){
            this.delete = new DeleteCommand(ui, stor, arrayLL);
            delete.complete(arr[1]);
        }
        else {
            try {
                if (!userText.equals("list") && !userText.equals("List") && !arr[0].equals("unmark") && !arr[0].equals("mark") && !arr[0].equals("delete")) {
                    if (arr.length > 1 && arr[0].equals("deadline")) {
                        type = 0;
                        String tempi = arr[1];
                        this.add = new AddCommand(ui, stor, arrayLL, type);
                        add.complete(tempi);
                    }
                    if (arr.length > 1 && arr[0].equals("event")) {
                        type = 1;
                        String tempi = arr[1];
                        this.add = new AddCommand(ui, stor, arrayLL, type);
                        add.complete(tempi);

                    }
                    if (arr.length > 1 && arr[0].equals("todo")) {
                        type = 2;
                        String tempi = arr[1];
                        this.add = new AddCommand(ui, stor, arrayLL, type);
                        add.complete(tempi);
                    }
                    if (arr.length == 1 && arr[0].equals("todo")) {
                        throw new NeoException("sorry todo cannot be empty");
                    }
                    if (!arr[0].equals("todo") && !arr[0].equals("event") && !arr[0].equals("deadline")) {
                        throw new NeoException("Sorry I don't know what that means");
                    }
                }
            } catch (NeoException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
