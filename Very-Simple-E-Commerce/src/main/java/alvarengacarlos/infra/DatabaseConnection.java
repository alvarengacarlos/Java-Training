package alvarengacarlos.infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseConnection {
    private EntityManagerFactory managerFactory;

    public EntityManager create() {
        try {
            managerFactory = Persistence.createEntityManagerFactory("alvarengacarlos.ecommerce");
            return managerFactory.createEntityManager();

        } catch (Exception e) {
            if (managerFactory != null) {
                managerFactory.close();
            }

            System.err.println(e.getMessage());
            throw new ItIsNotPossibleConnectToDatabaseException();
        }
    }

    private class ItIsNotPossibleConnectToDatabaseException extends RuntimeException {
        public ItIsNotPossibleConnectToDatabaseException() {
            super("it is not possible connect to database");
        }
    }

    public void destroy() {
        if (managerFactory != null)
            managerFactory.close();
    }
}
