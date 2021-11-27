package petclinic.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();


    T findById(ID id){
       return map.get(id);
    }

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T save(ID id, T obj){
        map.put(id, obj);
        return obj;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T obj){
        map.entrySet().removeIf(entry -> entry.getValue().equals(obj) );
    }
}
