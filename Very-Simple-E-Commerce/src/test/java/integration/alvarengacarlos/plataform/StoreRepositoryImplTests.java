package integration.alvarengacarlos.plataform;

import alvarengacarlos.plataform.Store;
import alvarengacarlos.plataform.StoreRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("StoreRepositoryImpl")
public class StoreRepositoryImplTests {
    private StoreRepositoryImpl storeRepository;

    @BeforeEach
    void beforeEach() {
        storeRepository = new StoreRepositoryImpl();
    }

    @Test
    @DisplayName("should find a store by id")
    void shouldFindAStoreById() {
        Store store = new Store("Linux Store");

        assertDoesNotThrow(() -> {
            storeRepository.save(store);

            Store founded = storeRepository.find(store.getId());

            assertEquals(founded.getId(), store.getId());
        });
    }

    @Test
    @DisplayName("should save a store")
    void shouldSaveAStore() {
        Store store = new Store("Linux Store");

        assertDoesNotThrow(() -> {
            Store saved = storeRepository.save(store);

            assertEquals(saved, store);
        });
    }

    @Test
    @DisplayName("should update a store by id")
    void shouldUpdateAStoreById() {
        Store store = new Store("Linux Store");

        Store storeToUpdate = new Store("L3x Store");

        assertDoesNotThrow(() -> {
            storeRepository.save(store);

            Store updated = storeRepository.update(storeToUpdate, store.getId());

            assertEquals(updated.getId(), store.getId());
            assertNotEquals(updated.getName(), store.getName());
        });
    }

    @Test
    @DisplayName("should delete a store by id")
    void shouldDeleteAStoreById() {
        Store store = new Store("Linux Store");

        assertDoesNotThrow(() -> {
            storeRepository.save(store);

            Long id = storeRepository.delete(store.getId());

            assertEquals(id, store.getId());
        });
    }
}
