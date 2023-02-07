package peaksoft.service;

import peaksoft.entity.Address;

import java.util.List;

public interface AddressService {
    String save(Long countryId, Address address);
    List<Address> saveAll(List<Address>addresses,Long countryId);
    List<Address>getAll();
    Address findById(Long id);
    String deleteById(Long id);
    String deleteAll();
    Address update(Long id,Address address);
}
