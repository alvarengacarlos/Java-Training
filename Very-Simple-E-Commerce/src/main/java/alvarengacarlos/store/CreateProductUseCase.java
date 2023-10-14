package alvarengacarlos.store;

import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.plataform.Store;
import alvarengacarlos.plataform.StoreRepository;
import alvarengacarlos.repository.DatabaseExecutionException;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Set;

public class CreateProductUseCase {
    private ProductRepository productRepository;
    private StoreRepository storeRepository;

    public CreateProductUseCase(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public CreateProductDtoOutput execute(CreateProductDtoInput input) throws ApplicationException {
        try {
            Store store = storeRepository.find(input.storeId());

            Product product = new Product(
                    input.imageUrl(),
                    input.quantity(),
                    input.title(),
                    input.price(),
                    input.shortDescription(),
                    input.brand(),
                    input.categories(),
                    store
            );

            productRepository.save(product);

            return new CreateProductDtoOutput(
                    product.getId(),
                    product.getTitle()
            );

        } catch (DatabaseExecutionException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public record CreateProductDtoInput(URL imageUrl, Integer quantity, String title, BigDecimal price, String shortDescription, String brand, Set<String> categories, Long storeId) {}
    public record CreateProductDtoOutput(Long id, String title) {}
}
