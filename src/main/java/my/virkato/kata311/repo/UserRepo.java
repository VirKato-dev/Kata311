package my.virkato.kata311.repo;

import my.virkato.kata311.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
