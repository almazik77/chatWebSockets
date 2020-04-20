package ru.itis.repositories;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryJpaImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query =
                entityManager.createQuery("select distinct u from User u where u.email = :email", User.class);

        query.setParameter("email", email);
        User user = query.getSingleResult();
        System.out.println(user);
        return Optional.ofNullable(user);
    }


    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    @Override
    @Transactional
    public void save(User entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(User entity) {
        entityManager.merge(entity);
    }
}
