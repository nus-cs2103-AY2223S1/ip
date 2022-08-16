import java.util.ArrayList;

public class Records {
    private final ArrayList<Task> records;

    Records() {
        this.records = new ArrayList<Task>();
    }

    Records(ArrayList<Task> records) {
        this.records = records;
    }

    Records add(Task newTask) {
        ArrayList<Task> newList = this.records;
        newList.add(newTask);
        return new Records(newList);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 1; i <= this.records.size(); i++) {
            output += i + ". " + this.records.get(i - 1).toString()
                    + "\n";
        }
        return output.substring(0, output.length() - 1);
    }
}
