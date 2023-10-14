package alvarengacarlos.store;

import alvarengacarlos.infra.DatabaseConnection;
import alvarengacarlos.repository.DatabaseExecutionException;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product find(Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();

        try {
            Product product = manager.find(Product.class, id);
            return product;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public Product save(Product newProduct) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(newProduct);
            transaction.commit();
            return newProduct;

        } catch (Exception e) {
            transaction.rollback();
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public Product update(Product product, Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();

            Product oldProduct = manager.find(Product.class, id);
            oldProduct.setImageUrl(product.getImageUrl());
            oldProduct.setQuantity(product.getQuantity());
            oldProduct.setTitle(product.getTitle());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setShortDescription(product.getShortDescription());
            oldProduct.setBrand(product.getBrand());
            oldProduct.setCategories(product.getCategories());
            manager.persist(oldProduct);

            transaction.commit();
            return oldProduct;

        } catch (Exception e) {
            transaction.rollback();
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public Long delete(Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();

            Product product = manager.find(Product.class, id);
            manager.remove(product);

            transaction.commit();
            return id;

        } catch (Exception e) {
            transaction.rollback();
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public List<Product> findAll(Integer limit) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();

        try {
            var typedQuery = manager.createQuery("SELECT p FROM products p", Product.class);
            return typedQuery.setMaxResults(limit)
                    .getResultList();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }
}
