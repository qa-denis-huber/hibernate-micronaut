package de.denis.poc;

import java.io.Serializable;

public class DimensionEntity implements Serializable {
    private String id;
    private String def;

    public DimensionEntity() {
    }

    public DimensionEntity(String id, String def) {
        this.id = id;
        this.def = def;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return "DimensionEntity{" +
                "abc='" + id + '\'' +
                ", def='" + def + '\'' +
                '}';
    }
}
