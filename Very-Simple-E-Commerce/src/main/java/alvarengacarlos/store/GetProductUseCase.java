package alvarengacarlos.store;

import alvarengacarlos.exeption.ApplicationException;
import alvarengacarlos.repository.DatabaseExecutionException;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class GetProductUseCase {
    private ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<GetProductDtoOutput> execute() throws ApplicationException {
        try {
            List<Product> products = productRepository.findAll(30);

            return products.stream().map(product ->
                    new GetProductDtoOutput(
                            product.getId(),
                            product.getImageUrl(),
                            product.getQuantity(),
                            product.getTitle(),
                            product.getPrice(),
                            product.getShortDescription(),
                            product.getBrand(),
                            product.getCategories()
                    )
            ).toList();

        } catch (DatabaseExecutionException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public record GetProductDtoOutput(Long id, URL imageURL, Integer quantity, String title, BigDecimal price, String shortDescription, String brand, Set<String> categories) {}
}
