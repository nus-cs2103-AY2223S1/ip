package neo;
import java.io.IOException;
public class AddCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage stor;
    private int type;

    public AddCommand(Ui ui, Storage stor, TaskList arrayLL, int type) {
        this.ui = ui;
        this.stor = stor;
        this.arrayLL = arrayLL;
        this.type = type;
    }

    @Override
    void complete(String tempi) throws NeoException, IOException {

        if (type ==0) {
            String arri[];
            arri = tempi.split("/by ");
            String temp2 = arri[0];
            String temp3 = arri[1];

            Deadline d = new Deadline(temp2, temp3);
            System.out.println("Added: " + d.toString());
            arrayLL.addTask(d);
            stor.Storedata(d);
        }
        if (type ==1) {
            String arri[];
            arri = tempi.split("/at ");
            String temp2 = arri[0];
            String temp3 = arri[1];

            Event e = new Event(temp2, temp3);
            System.out.println("Added: " + e.toString());
            arrayLL.addTask(e);
            stor.Storedata(e);
        }
        if (type ==2) {
            ToDo td = new ToDo(tempi);
            System.out.println("Added: " + td.toString());
            arrayLL.addTask(td);
            stor.Storedata(td);
        }
    }
}
