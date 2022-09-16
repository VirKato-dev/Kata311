package my.virkato.kata311.dao;

import my.virkato.kata311.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
