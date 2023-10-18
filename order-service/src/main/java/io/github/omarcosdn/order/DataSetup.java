package io.github.omarcosdn.order;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSetup {

  private final OrderRepository orderRepository;

  public DataSetup(final OrderRepository orderRepository) {
    this.orderRepository = Objects.requireNonNull(orderRepository);
  }

  @PostConstruct
  public void setupData() {
    orderRepository.saveAll(Arrays.asList(Order.builder().id(1).orderNumber("0c70c0c2").postalCode("1000001").build(),
                                          Order.builder().id(2).orderNumber("7f8f9f15").postalCode("1100000").build(),
                                          Order.builder().id(3).orderNumber("394627b2").postalCode("2100001").build()));
  }
}