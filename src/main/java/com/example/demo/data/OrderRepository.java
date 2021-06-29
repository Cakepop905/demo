package com.example.demo.data;
import com.example.demo.domain.Order;
import com.example.demo.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Taco, Long> {
    Order save(Order order);
}
