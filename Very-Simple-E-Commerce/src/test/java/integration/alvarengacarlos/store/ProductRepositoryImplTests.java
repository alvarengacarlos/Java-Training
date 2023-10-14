package integration.alvarengacarlos.store;

import alvarengacarlos.store.Product;
import alvarengacarlos.store.ProductRepositoryImpl;

import alvarengacarlos.plataform.Store;
import alvarengacarlos.plataform.StoreRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ProductRepositoryImpl")
public class ProductRepositoryImplTests {
    private ProductRepositoryImpl productRepository;
    private StoreRepositoryImpl storeRepository;

    @BeforeEach
    void beforeEach() {
        productRepository = new ProductRepositoryImpl();
        storeRepository = new StoreRepositoryImpl();
    }

    @Test
    @DisplayName("should find a product by id")
    void shouldFindAProductById() throws MalformedURLException {
        Store store = new Store("Linux Store");
        Set<String> productCategories = new HashSet<>();
        Product product = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                productCategories,
                store
        );

        assertDoesNotThrow(() -> {
            storeRepository.save(store);
            productRepository.save(product);

            Product found = productRepository.find(product.getId());

            assertEquals(found.getId(), product.getId());
        });
    }

    @Test
    @DisplayName("should save a product")
    void shouldSaveAProduct() throws MalformedURLException {
        Store store = new Store("Linux Store");
        Set<String> productCategories = new HashSet<>();
        Product product = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                productCategories,
                store
        );

        assertDoesNotThrow(() -> {
            storeRepository.save(store);
            Product saved = productRepository.save(product);

            assertEquals(saved, product);
        });
    }

    @Test
    @DisplayName("should update a product by id")
    void shouldUpdateAProductById() throws MalformedURLException {
        Store store = new Store("Linux Store");
        Product product = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                new HashSet<>(),
                store
        );

        HashSet<String> categories = new HashSet<>();
        categories.add("sport");
        Product productToUpdate = new Product(
                new URL("http://figure.com"),
                6,
                "Bicycle",
                new BigDecimal(200.00D),
                "Bicycle fast speed",
                "COLLY",
                categories,
                store
        );

        assertDoesNotThrow(() -> {
            storeRepository.save(store);
            productRepository.save(product);

            Product updated = productRepository.update(productToUpdate, product.getId());

            assertEquals(updated.getId(), product.getId());
            assertEquals(updated.getStore().getId(), product.getStore().getId());
            assertNotEquals(updated.getImageUrl(), product.getImageUrl());
            assertNotEquals(updated.getQuantity(), product.getQuantity());
            assertNotEquals(updated.getTitle(), product.getTitle());
            assertNotEquals(updated.getPrice(), product.getPrice());
            assertNotEquals(updated.getShortDescription(), product.getShortDescription());
            assertNotEquals(updated.getBrand(), product.getBrand());
            assertNotEquals(updated.getCategories(), product.getCategories());
        });
    }

    @Test
    @DisplayName("should delete a product by id")
    void shouldDeleteAProductById() throws MalformedURLException {
        Store store = new Store("Linux Store");
        Set<String> productCategories = new HashSet<>();
        Product product = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                productCategories,
                store
        );

        assertDoesNotThrow(() -> {
            storeRepository.save(store);
            productRepository.save(product);

            Long id = productRepository.delete(product.getId());

            assertEquals(id, product.getId());
        });
    }

    @Test
    @DisplayName("should find all products with a limit")
    void shouldFindAllProductsWithALimit() throws MalformedURLException {
        Store store = new Store("Linux Store");
        Set<String> productCategories = new HashSet<>();
        Product product0 = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                productCategories,
                store
        );
        Product product1 = new Product(
                new URL("http://image.com"),
                10,
                "Ball",
                new BigDecimal(14.00D),
                "World cup 2022 replica ball",
                "FIFA",
                productCategories,
                store
        );

        assertDoesNotThrow(() -> {
            storeRepository.save(store);
            productRepository.save(product0);
            productRepository.save(product1);

            List<Product> products = productRepository.findAll(1);

            assertEquals(products.size(), 1);
        });
    }
}
