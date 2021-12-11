package petclinic.services.map;

import petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID> {

    protected Map<Long, T> map = new HashMap<>();


    T findById(ID id){
       return map.get(id);
    }

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T save( T obj) {
        if (obj != null) {
            if (obj.getId() == null) {
                obj.setId(getNextID());
            }
            map.put(obj.getId(), obj);
        } else
            throw new RuntimeException("Object can not be null");

        return obj;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T obj){
        map.entrySet().removeIf(entry -> entry.getValue().equals(obj) );
    }

    public Long getNextID (){
        Long value = null;
        try{
            value = Collections.max(map.keySet()) + 1;
        } catch (Exception exception) {
            value = 1L;
        }

        return value;
    }
}
