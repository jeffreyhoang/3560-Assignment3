import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private Date date;

    @Column(name="customer_name")
    private String customerName;

    @ManyToMany(cascade= {CascadeType.PERSIST})
    @JoinTable(
        name="order_product",
        joinColumns=@JoinColumn(name="order_id"),
        inverseJoinColumns=@JoinColumn(name="product_id")
    )
    private List<Product> products;

    // constructors
    public Order() {

    }

    public Order(Date date, String customerName) {
        this.date = date;
        this.customerName = customerName;
    }

    // getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if(products == null) {
            products = new ArrayList<>();
        } 

        products.add(product);
    }

    @Override
	public String toString() {
		return "Order [date=" + date + ", customerName=" + customerName + "]";
	}
}
