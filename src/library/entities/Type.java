package library.entities;

import java.io.Serializable;

public class Type implements Serializable {
    public static final long serialVersionUID = 5000L;

    private int typeId;
    private String name;

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
}
