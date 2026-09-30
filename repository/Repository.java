package repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import model.annotation.Id;

public class Repository<T> {

    private HashMap<String, T> map = new HashMap<String, T>();

    public T save(T entity) {

        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if (value != null) {
                        map.put(value.toString(), entity);
                        return entity;
                    }
                }
            }
            Field[] fields2 = entity.getClass().getSuperclass().getDeclaredFields();
            for (Field field : fields2) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if (value != null) {
                        map.put(value.toString(), entity);
                        return entity;
                    }
                }
            }
        } catch (Exception e) {

        }

        return entity;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T t : items) {
            save(t);
        }
    }

    public T findById(String id) {
        return map.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<T>(map.values());
    }

    public boolean delete(String id) {
        if (map.containsKey(id)) {
            map.remove(id);
            return true;
        }
        return false;
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        double sum = 0;
        for (N n : values) {
            sum += n.doubleValue();
        }
        return sum / values.length;
    }
}
