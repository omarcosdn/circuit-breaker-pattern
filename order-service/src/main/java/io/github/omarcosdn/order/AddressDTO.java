package io.github.omarcosdn.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

  Integer id;
  String postalCode;
  String state;
  String city;
}