package alvarengacarlos.store;

import alvarengacarlos.repository.DatabaseExecutionException;
import alvarengacarlos.repository.Repository;

import java.util.List;

public interface ProductRepository extends Repository<Product, Long> {
    List<Product> findAll(Integer limit) throws DatabaseExecutionException;
}
