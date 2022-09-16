package my.virkato.kata311.service;

public interface MyService<T> {
    void create(T t);
    T show(long id);
    Iterable<T> getList();
    void update(long id, T t);
    void delete(long id);
    void delete(T t);
}
