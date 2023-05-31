package curso.api.rest.model.dto;

import java.util.List;

import curso.api.rest.model.BookSell;
import curso.api.rest.model.Order;

public class FinalizedOrderDto {
	
	private Order order;
	private List<BookSell> booksSell;
	
	public FinalizedOrderDto() {
		
	}
	
	public FinalizedOrderDto(Order order, List<BookSell> booksSell) {
		super();
		this.order = order;
		this.booksSell = booksSell;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<BookSell> getBooksSell() {
		return booksSell;
	}

	public void setBooksSell(List<BookSell> booksSell) {
		this.booksSell = booksSell;
	}
}
