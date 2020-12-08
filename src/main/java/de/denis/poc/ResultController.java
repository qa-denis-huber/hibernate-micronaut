package de.denis.poc;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller("/results")
public class ResultController {

    @PersistenceContext
    private final EntityManager entityManager;

    public ResultController(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Get
    @Transactional(readOnly = true)
    public List<ResultSetEntity> findAll() {
        return null;
    }

    @Get("/{id}")
    @Transactional(readOnly = true)
    public ResultSetEntity findById(@QueryValue String id) {
        return null;
    }

    @Post
    @Transactional
    public ResultSetEntity save() {
        Random random = new Random();
        int numDim = random.nextInt(5) + 1;
        List<String> dimensions = new ArrayList<>(numDim);
        for (int i = 0; i < numDim; i++) {
            dimensions.add("dim #" + random.nextInt(100));
        }

        ResultSetEntity resultSetEntity = new ResultSetEntity(UUID.randomUUID(), dimensions.toArray(new String[0]));
        entityManager.merge(resultSetEntity);

        return resultSetEntity;
    }
}
