package peaksoft.service;

import peaksoft.entity.Country;
import peaksoft.enums.EnumCountry;
import peaksoft.repository.CountryRepository;
import peaksoft.repository.CountryRepositoryImpl;

import java.util.List;

public class CountryServiceImpl implements CountryService{
    CountryRepository countryRepository=new CountryRepositoryImpl();

    @Override
    public String save(Country country) {
        countryRepository.save(country);
        return "Successfully saved";
    }

    @Override
    public List<Country> saveAll(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        countryRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();

    }

    @Override
    public Country update(Long id, Country country) {
        return countryRepository.update(id, country);
    }

    @Override
    public Country longDescription() {

        return countryRepository.longDescription();
    }

    @Override
    public int countProgrammerInCountry(EnumCountry country) {
        return countryRepository.countProgrammerInCountry(country);
    }
}
