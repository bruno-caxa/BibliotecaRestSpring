package curso.api.rest.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import curso.api.rest.aws.model.ImageReference;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
	
	@OneToOne(cascade = CascadeType.ALL)
    private ImageReference image;
	private String imageUrl;
    
    @ManyToOne()
    private BookCategory category;
    private String publishingCompany;
    private String author;
    private int totalPages;
    private double unitPrice;
    private String description;
    
    public Book() {
    	
    }

	public Book(Long id, String title, ImageReference image, BookCategory category, String publishingCompany, String author, int totalPages, double unitPrice, String description) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.category = category;
		this.publishingCompany = publishingCompany;
		this.author = author;
		this.totalPages = totalPages;
		this.unitPrice = unitPrice;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ImageReference getImage() {
		return image;
	}

	public void setImage(ImageReference image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
