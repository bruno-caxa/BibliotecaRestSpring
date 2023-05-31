package curso.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import curso.api.rest.model.BookSell;
import curso.api.rest.model.Order;
import curso.api.rest.model.User;
import curso.api.rest.model.dto.FinalizedOrderDto;
import curso.api.rest.repository.BookSellRepository;
import curso.api.rest.repository.OrderRepository;
import curso.api.rest.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private BookSellRepository bookSellRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Order finalizeOrder(FinalizedOrderDto finalizedOrderDto) {
		Order order = orderRepository.save(finalizedOrderDto.getOrder());
		
		for(BookSell bs : finalizedOrderDto.getBooksSell()) {
			bs.setOrder(order);
		}
		
		bookSellRepository.saveAll(finalizedOrderDto.getBooksSell());
		return order;
	}
	
	public Page<Order> findByIdUser(Long id, int page) {
		User user = userRepository.findById(id).get();
		return orderRepository.findByUser(user, PageRequest.of(page, 3, Sort.by("date")));
	}
	
}
