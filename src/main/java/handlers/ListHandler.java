package handlers;

import entities.Task;
import exceptions.DukeException;
import utils.Utils;

import java.util.List;


public class ListHandler implements IHandler{
    public ListHandler() {
    }

    @Override
    public void handle(List<Task> list) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        int n = list.size();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(String.format("%d. %s", i + 1, list.get(i)));
            if (i != n - 1) {
                stringBuilder.append("\n");
            }
        }
        Utils.customPrint(stringBuilder.toString());
    }
}
