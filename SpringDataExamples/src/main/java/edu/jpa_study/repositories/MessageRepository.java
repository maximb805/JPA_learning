package edu.jpa_study.repositories;

import edu.jpa_study.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
