package integration.alvarengacarlos.plataform;

import alvarengacarlos.plataform.CreateStoreUseCase;
import alvarengacarlos.plataform.StoreRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CreateStoreUseCase")
public class CreateStoreUseCaseTests {
    private CreateStoreUseCase createStoreUseCase;

    @BeforeEach
    void beforeEach() {
        createStoreUseCase = new CreateStoreUseCase(new StoreRepositoryImpl());
    }

    @Test
    @DisplayName("should create a Store")
    void shouldCreateAStore() {
        assertDoesNotThrow(() -> {
            var input = new CreateStoreUseCase.CreateStoreDtoInput("Linux Store");

            var output = createStoreUseCase.execute(input);

            assertEquals(input.name(), output.name());
        });
    }
}
