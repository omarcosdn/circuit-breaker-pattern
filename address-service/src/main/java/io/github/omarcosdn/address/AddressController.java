package io.github.omarcosdn.address;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("addresses")
public class AddressController {

  private final AddressService addressService;

  public AddressController(final AddressService addressService) {
    this.addressService = Objects.requireNonNull(addressService);
  }

  @GetMapping("/{postalCode}")
  public Address getAddressByPostalCode(@PathVariable("postalCode") String postalCode) {
    return addressService.getAddressByPostalCode(postalCode);
  }
}
