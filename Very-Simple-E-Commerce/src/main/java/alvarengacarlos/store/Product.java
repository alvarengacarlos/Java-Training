package alvarengacarlos.store;

import alvarengacarlos.plataform.Store;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Set;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private URL imageUrl;
    @Basic(optional = false)
    private Integer quantity;
    @Basic(optional = false)
    private String title;
    @Basic(optional = false)
    private BigDecimal price;
    @Basic(optional = false)
    private String shortDescription;
    @Basic(optional = false)
    private String brand;
    @Basic(optional = false)
    private Set<String> categories;
    @ManyToOne(optional = false)
    private Store store;

    public Product() {}

    public Product(URL imageUrl, Integer quantity, String title, BigDecimal price, String shortDescription, String brand, Set<String> categories, Store store) {
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.title = title;
        this.price = price;
        this.shortDescription = shortDescription;
        this.brand = brand;
        this.categories = categories;
        this.store = store;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;
        return id.equals(product.getId());
    }

    public Long getId() {
        return id;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}



