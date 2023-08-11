package by.leverx.repository;

import by.leverx.model.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends CrudRepository<SubscriberEntity, Long> {

}
