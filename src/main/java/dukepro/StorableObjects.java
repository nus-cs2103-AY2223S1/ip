package dukepro;

import dukepro.exceptions.DukeException;

public abstract class StorableObjects {
    public abstract StorableObjects parseFromFile(String word) throws DukeException;
    public abstract String fileForm();
}
