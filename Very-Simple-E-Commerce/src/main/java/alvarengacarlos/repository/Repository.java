package alvarengacarlos.repository;

public interface Repository<T, S> {
    T find(S id) throws DatabaseExecutionException;
    T save(T newEntity) throws DatabaseExecutionException;
    T update(T entity, S id) throws DatabaseExecutionException;
    S delete(S id) throws DatabaseExecutionException;
}
