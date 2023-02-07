package peaksoft.service;

import peaksoft.entity.Address;
import peaksoft.repository.AddressRepository;
import peaksoft.repository.AddressRepositoryImpl;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository=new AddressRepositoryImpl();
    @Override
    public String save(Long countryId, Address address) {
        addressRepository.save(countryId, address);
        return "Successfully saved";
    }

    @Override
    public List<Address> saveAll(List<Address> addresses,Long countryId) {
        return addressRepository.saveAll(addresses,countryId);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.getAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        addressRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public String deleteAll() {
        addressRepository.deleteAll();
        return "Successfully deleted";
    }

    @Override
    public Address update(Long id, Address address) {
        return addressRepository.update(id, address);
    }
}
