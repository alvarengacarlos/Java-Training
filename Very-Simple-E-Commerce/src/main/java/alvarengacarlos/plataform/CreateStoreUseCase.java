package alvarengacarlos.plataform;

import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.repository.DatabaseExecutionException;

public class CreateStoreUseCase {
    private StoreRepository storeRepository;

    public CreateStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public CreateStoreDtoOutput execute(CreateStoreDtoInput input) throws ApplicationException {
        try {
            Store store = new Store(
                    input.name()
            );

            Store createdStore = storeRepository.save(store);

            return new CreateStoreDtoOutput(
                    createdStore.getId(),
                    createdStore.getName()
            );

        } catch (DatabaseExecutionException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public record CreateStoreDtoInput(String name) {
    }

    public record CreateStoreDtoOutput(Long id, String name) {
    }
}
