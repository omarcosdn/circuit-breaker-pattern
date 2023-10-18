package io.github.omarcosdn.address;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSetup {

  private final AddressRepository addressRepository;

  public DataSetup(final AddressRepository addressRepository) {
    this.addressRepository = Objects.requireNonNull(addressRepository);
  }

  @PostConstruct
  public void setupData() {
    addressRepository.saveAll(
        Arrays.asList(Address.builder().id(1).postalCode("1000001").state("PR").city("Londrina").build(),
                      Address.builder().id(2).postalCode("1100000").state("SC").city("Criciúma").build(),
                      Address.builder().id(3).postalCode("2100001").state("RS").city("Porto Alegre").build()));
  }
}
