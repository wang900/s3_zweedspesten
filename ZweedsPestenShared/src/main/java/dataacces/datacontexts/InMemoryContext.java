package dataacces.datacontexts;

import dataacces.Entity;
import dataacces.datacontexts.interfaces.IDataContext;

import java.util.ArrayList;
import java.util.List;

public class InMemoryContext implements IDataContext {

    private static List<Entity> entities = new ArrayList<>();

    @Override
    public <T> T getSingle(long id, Class<T> returnType, String simpleType) {
        for (Entity e : entities) {
            if (e.getEntityId() == id)
                return (T) e;
        }
        return null;
    }

    @Override
    public <T> List<T> getAll(Class<T> returnType, String simpleType) {
        List<T> items = new ArrayList<>();
        for (Entity e : entities) {
            String name = e.getClass().getSimpleName();
            if (name.equals(simpleType))
                items.add((T) e);
        }
        return items;
    }

    @Override
    public <T> void add(Entity obj, Class<T> returnType) {
        entities.add(obj);
    }

    @Override
    public <T> void update(Entity obj, Class<T> returnType, String simpleType) {
        remove(obj, returnType, simpleType);
        add(obj, returnType);
    }

    @Override
    public <T> void remove(Entity obj, Class<T> returnType, String simpleType) {
        for (Entity e : entities) {
            if (e.getEntityId() == obj.getEntityId())
                entities.remove(e);
        }
    }
}
