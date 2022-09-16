package my.virkato.kata311.service;

import my.virkato.kata311.dao.UserRepo;
import my.virkato.kata311.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements MyService<User> {
    private final UserRepo dao;

    public UserService(UserRepo dao) {
        this.dao = dao;
    }

    @Override
    public void create(User user) {
        dao.save(user);
    }

    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(User user) {
        dao.delete(user);
    }

    @Override
    public void update(long id, User user) {
        user.setId(id);
        dao.save(user);
    }

    @Override
    public User show(long id) {
        return dao.findById(id).orElse(new User());
    }

    @Override
    public Iterable<User> getList() {
        return dao.findAll();
    }
}
