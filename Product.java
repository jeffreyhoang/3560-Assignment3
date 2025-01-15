import java.util.ArrayList;
import java.util.List;

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
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany(cascade= {CascadeType.PERSIST})
    @JoinTable(
        name="order_product",
        joinColumns=@JoinColumn(name="product_id"),
        inverseJoinColumns=@JoinColumn(name="order_id")
    )
    private List<Order> orders;

    // constructors
    public Product() {

    }

    public Product(String name) {
        this.name = name;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if(orders == null) {
            orders = new ArrayList<>();
        } 

        orders.add(order);
    }

    @Override
	public String toString() {
		return "Product [name=" + name + "]";
	}
}
