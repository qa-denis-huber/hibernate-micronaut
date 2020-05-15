package de.denis.poc;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller("/sections")
public class SectionController {

    @PersistenceContext
    private final EntityManager entityManager;

    public SectionController(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Get
    @Transactional(readOnly = true)
    public List<SectionEntity> findAll() {
        TypedQuery<SectionEntity> query = entityManager.createQuery("SELECT s FROM SectionEntity as s", SectionEntity.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @Get("/{id}")
    @Transactional(readOnly = true)
    public SectionEntity findByAbc(@QueryValue String id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM section s WHERE s.dimensions->>'id' = '" + id + "'", SectionEntity.class);
        return (SectionEntity) query.getResultList().get(0);
    }

    @Post
    @Transactional
    public SectionEntity save() {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setDimensions(new DimensionEntity("1", "20"));
        entityManager.persist(sectionEntity);
        return sectionEntity;
    }
}
