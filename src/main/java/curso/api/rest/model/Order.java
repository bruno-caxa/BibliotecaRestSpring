package curso.api.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;
    
    private Date date;
    
    private Double totalValue;
    
    @OneToMany(mappedBy = "order")
    private List<BookSell> booksSell;
    
    public Order() {
    	
    }

	public Order(Long id, User user, Date date, Double totalValue) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.totalValue = totalValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<BookSell> getBooksSell() {
		return booksSell;
	}

	public void setBooksSell(List<BookSell> booksSell) {
		this.booksSell = booksSell;
	}
    
}
