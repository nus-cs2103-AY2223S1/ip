package dukepro;

import dukepro.exceptions.DukeException;

import java.time.LocalDate;

public abstract class StorableObjects {
    public abstract String fileForm();
    public void markAsDone(){};
    public abstract boolean getMatching(String search);
    public abstract boolean compareDate(LocalDate localDate);
}
