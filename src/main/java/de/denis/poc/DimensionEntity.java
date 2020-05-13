package de.denis.poc;

import java.io.Serializable;

public class DimensionEntity implements Serializable {
    private String abc;
    private String def;

    public DimensionEntity() {
    }

    public DimensionEntity(String abc, String def) {
        this.abc = abc;
        this.def = def;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
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
                "abc='" + abc + '\'' +
                ", def='" + def + '\'' +
                '}';
    }
}
