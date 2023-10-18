package io.github.omarcosdn.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Objects;
import java.util.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

  private static final String SERVICE_NAME = "order-service";
  private static final String ADDRESS_SERVICE_URL = "http://localhost:9090/addresses/%s";

  private final OrderRepository orderRepository;
  private final RestTemplate restTemplate;

  public OrderServiceImpl(final OrderRepository orderRepository, final RestTemplate restTemplate) {
    this.orderRepository = Objects.requireNonNull(orderRepository);
    this.restTemplate = Objects.requireNonNull(restTemplate);
  }

  @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "fallbackMethod")
  public Type getOrderByPostCode(final String orderNumber) {
    final var order = orderRepository.findByOrderNumber(orderNumber)
        .orElseThrow(() -> new RuntimeException("Order Not Found: " + orderNumber));

    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final var entity = new HttpEntity<AddressDTO>(null, headers);
    final var response = restTemplate.exchange(ADDRESS_SERVICE_URL.formatted(order.getPostalCode()), HttpMethod.GET,
                                               entity, AddressDTO.class);

    Optional.ofNullable(response.getBody()).ifPresent(addressDTO -> {
      order.setShippingState(addressDTO.getState());
      order.setShippingCity(addressDTO.getCity());
    });
    return order;
  }

  @SuppressWarnings("S1144")
  private Type fallbackMethod(Exception e) {
    return new Failure("Address service is not responding properly. %s".formatted(e.getMessage()));
  }
}