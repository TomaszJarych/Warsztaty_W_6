package pl.coderslab.Service;

import java.io.Serializable;
import java.util.Collection;

public interface BaseCrudService<D, I extends Serializable> {

  D findById(I id);

  D saveToDB(D dto);

  D update(D dto);

  void removeFromDB(I id);

  Collection<D> findAll();
}
