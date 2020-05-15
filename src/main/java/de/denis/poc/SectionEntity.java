package de.denis.poc;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "section")
public class SectionEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "dimensions")
    @Type(type = "de.denis.poc.DimensionEntityType")
    // TODO: Change to list
    private DimensionEntity dimensions;

    public SectionEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DimensionEntity getDimensions() {
        return dimensions;
    }

    public void setDimensions(DimensionEntity dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "SectionEntity{" +
                "id=" + id +
                ", dimensions='" + dimensions + '\'' +
                '}';
    }
}
