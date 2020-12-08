package de.denis.poc;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "result_set")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(typeClass = ListArrayType.class, name = "list-array")
public class ResultSetEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "dimensions", nullable = false)
    @Type(type = "list-array")
    private List<String> dimensions;
}
