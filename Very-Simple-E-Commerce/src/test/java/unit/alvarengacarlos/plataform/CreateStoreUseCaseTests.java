package unit.alvarengacarlos.plataform;

import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.plataform.CreateStoreUseCase;

import alvarengacarlos.plataform.Store;
import alvarengacarlos.plataform.StoreRepository;
import alvarengacarlos.repository.DatabaseExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("CreateStoreUseCase")
public class CreateStoreUseCaseTests {
    private CreateStoreUseCase createStoreUseCase;
    private StoreRepository storeRepository;

    @BeforeEach
    void beforeEach() {
        storeRepository = mock(StoreRepository.class);
        createStoreUseCase = new CreateStoreUseCase(storeRepository);
    }

    @Test
    @DisplayName("should create a store")
    void shouldCreateAStore() throws DatabaseExecutionException {
        Store store = new Store("Linux Store");
        when(storeRepository.save(any(Store.class))).thenReturn(store);

        assertDoesNotThrow(() -> {
            var input = new CreateStoreUseCase.CreateStoreDtoInput(store.getName());

            var output = createStoreUseCase.execute(input);

            assertEquals(output.name(), store.getName());
        });
    }

    @Test
    @DisplayName("should throw ApplicationException")
    void shouldThrowApplicationException() throws DatabaseExecutionException {
        Store store = new Store("Linux Store");
        when(storeRepository.save(any(Store.class))).thenThrow(DatabaseExecutionException.class);

        assertThrows(ApplicationException.class, () -> {
            var input = new CreateStoreUseCase.CreateStoreDtoInput(store.getName());
            createStoreUseCase.execute(input);
        });
    }
}
