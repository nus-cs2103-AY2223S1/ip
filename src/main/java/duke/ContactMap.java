package duke;

import java.util.ArrayList;
import java.util.TreeMap;

import duke.Contact;
import duke.task.Task;

public class ContactMap {
    private TreeMap<String,Contact> contactTreeMap = null;

    public ContactMap(TreeMap<String,Contact> contactTreeMap) throws DukeException {
        try {
            this.contactTreeMap = contactTreeMap;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public ContactMap() {
        this.contactTreeMap = new TreeMap<>();
    }

    public TreeMap<String,Contact> getContactMap() {
        return this.contactTreeMap;
    }

    public void deleteContact(String name) throws DukeException {
        Contact returned_value = this.contactTreeMap.remove(name);
        if (returned_value == null) {
            throw new DukeException("No contact is deleted \n As name not found in contacts!");
        }
    }

    public int totalSize() {
        return this.contactTreeMap.size();
    }

    public void addContact(Contact contact) throws DukeException {
        String name = contact.getName();
        if (this.contactTreeMap.containsKey(name)) {
            throw new DukeException("Duplicate name detected! \n Please choose another name");
        } else {
            this.contactTreeMap.put(name,contact);
        }
    }

    public Contact findContact(String name) throws DukeException {
        if (this.contactTreeMap.containsKey(name)) {
            return this.contactTreeMap.get(name);
        } else {
            throw new DukeException("Contact not found");
        }
    }


}
