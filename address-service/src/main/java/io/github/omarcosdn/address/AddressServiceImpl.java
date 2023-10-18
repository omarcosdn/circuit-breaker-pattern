package io.github.omarcosdn.address;

import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;

  public AddressServiceImpl(final AddressRepository addressRepository) {
    this.addressRepository = Objects.requireNonNull(addressRepository);
  }

  public Address getAddressByPostalCode(final String postalCode) {
    return addressRepository.findByPostalCode(postalCode)
        .orElseThrow(() -> new RuntimeException("Address Not Found: " + postalCode));
  }
}