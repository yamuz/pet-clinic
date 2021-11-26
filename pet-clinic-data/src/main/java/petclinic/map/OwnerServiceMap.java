package petclinic.map;

import petclinic.map.AbstractMapService;
import petclinic.model.Owner;
import petclinic.services.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long>  implements CrudService<Owner, Long> {

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }


    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}
