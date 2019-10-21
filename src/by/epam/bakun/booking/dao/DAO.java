package by.epam.bakun.booking.dao;

import java.util.List;

public interface DAO<Entity, Key> {

    void create(Entity model);

    Entity getById(Key key);

    List<Entity> getAll();

    void update(Entity model);

    void delete(Key key);
}


