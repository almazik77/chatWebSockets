package ru.itis.repositories;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class MessageRepositoryJpaImpl implements MessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Message> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Message.class, id));
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return entityManager.createQuery("select m from Message m", Message.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void update(Message entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<Message> findWithRoomId(String id) {
        return entityManager.createQuery("select m " +
                "from Message m " +
                "where m.roomId = :id", Message.class)
                .setParameter("id", id)
                .getResultList();
    }
}
