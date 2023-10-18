package io.github.omarcosdn.address;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

  Optional<Address> findByPostalCode(String postalCode);
}