package ru.itis.repositories;

import ru.itis.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findWithRoomId(String id);
}
