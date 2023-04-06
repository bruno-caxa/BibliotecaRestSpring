package curso.api.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import curso.api.rest.model.Order;
import curso.api.rest.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("select o from Order o where o.user = ?1")
	public Page<Order> findByUser(User user, PageRequest pageRequest);

}	
