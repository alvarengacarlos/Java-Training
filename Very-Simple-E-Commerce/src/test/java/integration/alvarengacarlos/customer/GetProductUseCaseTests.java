package integration.alvarengacarlos.customer;

import alvarengacarlos.customer.GetProductUseCase;
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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GetProductUseCase")
public class GetProductUseCaseTests {
    private GetProductUseCase getProductUseCase;
    private CreateProductUseCase createProductUseCase;
    private CreateStoreUseCase createStoreUseCase;

    @BeforeEach
    void beforeEach() {
        StoreRepositoryImpl storeRepository = new StoreRepositoryImpl();
        createStoreUseCase = new CreateStoreUseCase(storeRepository);

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        getProductUseCase = new GetProductUseCase(productRepository);
        createProductUseCase = new CreateProductUseCase(productRepository, storeRepository);
    }

    @Test
    @DisplayName("should find 20 products")
    void shouldFind20Products() {
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
            for (Integer i=0; i<20; i++)
                createProductUseCase.execute(input);

            List<GetProductUseCase.GetProductDtoOutput> output = getProductUseCase.execute();

            assertEquals(output.size(), 20);
        });
    }
}
