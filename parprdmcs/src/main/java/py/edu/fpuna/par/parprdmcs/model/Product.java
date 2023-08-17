package py.edu.fpuna.par.parprdmcs.model;

import java.math.BigDecimal;
import py.una.pol.par.commons.entity.BaseEntity;

public class Product extends BaseEntity<Integer> {

    private String description;
    private int categoryId;
    private int quantity;
    private String image;
    private BigDecimal price;
    
    
    public Product() {
        super(0, "");
    }

    public Product(Integer id, String name) {
        super(id, name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
