package io.github.omarcosdn.order;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(final OrderService orderService) {
    this.orderService = Objects.requireNonNull(orderService);
  }

  @GetMapping
  public Type getByOrderNumber(@RequestParam("orderNumber") String orderNumber) {
    return orderService.getOrderByPostCode(orderNumber);
  }
}