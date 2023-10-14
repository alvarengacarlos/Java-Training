package alvarengacarlos.customer;

import alvarengacarlos.infra.DatabaseConnection;
import alvarengacarlos.repository.DatabaseExecutionException;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account find(Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();

        try {
            Account account = manager.find(Account.class, id);
            return account;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseExecutionException();

        } finally {
            manager.close();
            databaseConnection.destroy();
        }
    }

    @Override
    public Account save(Account newAccount) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(newAccount);
            transaction.commit();
            return newAccount;

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
    public Account update(Account account, Long id) throws DatabaseExecutionException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        var manager = databaseConnection.create();
        var transaction = manager.getTransaction();

        try {
            transaction.begin();

            Account oldAccount = manager.find(Account.class, id);
            oldAccount.setName(account.getName());
            oldAccount.setEmail(account.getEmail());
            oldAccount.setPassword(account.getPassword());
            manager.persist(oldAccount);

            transaction.commit();
            return oldAccount;

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

            Account account = manager.find(Account.class, id);
            manager.remove(account);

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
