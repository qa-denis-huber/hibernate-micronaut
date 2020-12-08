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

    @Get("/{dim}")
    @Transactional(readOnly = true)
    public List<ResultSetEntity> findById(@QueryValue String dim) {
        // Typed Query
        // Throws NPE
//        String qlString = "SELECT rse FROM ResultSetEntity AS rse WHERE '" + dim + "' MEMBER OF rse.dimensions";
//        return entityManager.createQuery(qlString, ResultSetEntity.class).getResultList();


        // Criteria Query
        // Throws IllegalArgumentException: unknown collection expression type [org.hibernate.query.criteria.internal.path.SingularAttributePath]
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<ResultSetEntity> query = cb.createQuery(ResultSetEntity.class);
//        Root<ResultSetEntity> rse = query.from(ResultSetEntity.class);
//
//        Expression<Collection<String>> dimensions = rse.get("dimensions");
//        Predicate blub = cb.isMember("blub", dimensions);
//
//        query.select(rse).where(blub);
//        return entityManager.createQuery(query).getResultList();

        // Native sql works!
        String sql = "select * from result_set where '" + dim + "' = ANY (dimensions)";
        return entityManager.createNativeQuery(sql, ResultSetEntity.class).getResultList();
    }

    @Post
    @Transactional
    public ResultSetEntity save() {
        Random random = new Random();
        int numDim = random.nextInt(5);
        List<String> dimensions = new ArrayList<>(numDim);
        for (int i = 0; i < numDim; i++) {
            dimensions.add("dim #" + random.nextInt(100));
        }
        dimensions.add("blub");

        ResultSetEntity resultSetEntity = new ResultSetEntity(UUID.randomUUID(), dimensions);
        entityManager.merge(resultSetEntity);

        return resultSetEntity;
    }
}
