package library.entities;

import java.io.Serializable;

public class Type implements Serializable {
    public static final long serialVersionUID = 5000L;

    private int typeId;
    private String name;
    private int timesBorrowed;

    public Type(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public void addTimesBorrowed() {
        this.timesBorrowed++;
    }
}
