package curso.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Order;
import curso.api.rest.model.dto.FinalizedOrderDto;
import curso.api.rest.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping()
	public ResponseEntity<Order> finalizeOrder(@RequestBody FinalizedOrderDto finalizedOrderDto) {
		return ResponseEntity.ok().body(orderService.finalizeOrder(finalizedOrderDto));
	}
	
	@GetMapping("/user/{id}/page/{page}")
	public ResponseEntity<Page<Order>> findByIdUserPaginated(@PathVariable Long id, @PathVariable int page) {
		return ResponseEntity.ok().body(orderService.findByIdUser(id, page));
	}

}
