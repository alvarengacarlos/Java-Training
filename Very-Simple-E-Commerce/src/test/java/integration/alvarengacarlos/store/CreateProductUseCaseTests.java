package integration.alvarengacarlos.store;

import alvarengacarlos.store.CreateProductUseCase;
import alvarengacarlos.plataform.CreateStoreUseCase;
import alvarengacarlos.store.ProductRepositoryImpl;

import alvarengacarlos.plataform.StoreRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CreateProductUseCase")
public class CreateProductUseCaseTests {
    private CreateProductUseCase createProductUseCase;
    private CreateStoreUseCase createStoreUseCase;

    @BeforeEach
    void beforeEach() {
        StoreRepositoryImpl storeRepository = new StoreRepositoryImpl();
        createStoreUseCase = new CreateStoreUseCase(storeRepository);

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        createProductUseCase = new CreateProductUseCase(productRepository, storeRepository);
    }

    @Test
    @DisplayName("should create a product")
    void shouldCreateAProduct() {
        assertDoesNotThrow(() -> {
            var createStoreDtoOutput = createStoreUseCase.execute(
                    new CreateStoreUseCase.CreateStoreDtoInput("Linux Store")
            );

            Set<String> productCategories = new HashSet<>();
            productCategories.add("sport");
            var input = new CreateProductUseCase.CreateProductDtoInput(
                    new URL("http://image.com"),
                    10,
                    "Ball",
                    new BigDecimal(14.00D),
                    "World cup 2022 replica ball",
                    "FIFA",
                    productCategories,
                    createStoreDtoOutput.id()
            );

            var output = createProductUseCase.execute(input);

            assertEquals(output.title(), input.title());
        });
    }
}
