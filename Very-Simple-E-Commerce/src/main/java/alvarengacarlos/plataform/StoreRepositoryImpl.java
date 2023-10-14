package alvarengacarlos.plataform;

import alvarengacarlos.infra.DatabaseConnection;
import alvarengacarlos.repository.DatabaseExecutionException;

public class StoreRepositoryImpl implements StoreRepository {
    @Override
    public Store find(Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();

        try {
            Store store = manager.find(Store.class, id);
            return store;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public Store save(Store newStore) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(newStore);
            transaction.commit();
            return newStore;

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
    public Store update(Store store, Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();

            Store oldStore = manager.find(Store.class, id);
            oldStore.setName(store.getName());

            transaction.commit();
            return oldStore;

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

            Store store = manager.find(Store.class, id);
            manager.remove(store);

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
}
