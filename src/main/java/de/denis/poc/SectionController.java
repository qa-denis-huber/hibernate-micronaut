package de.denis.poc;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @Transactional
    public List<SectionEntity> findAll() {
        TypedQuery<SectionEntity> query = entityManager.createQuery("SELECT s FROM SectionEntity as s", SectionEntity.class);
        query.setMaxResults(10);
        return query.getResultList();
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
