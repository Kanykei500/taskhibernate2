package peaksoft.service;

import peaksoft.entity.Country;
import peaksoft.enums.EnumCountry;

import java.util.List;

public interface CountryService {
    String save (Country country);
    List<Country> saveAll(List<Country>countries);
    List<Country>getAll();
    Country findById(Long id);
    String deleteById(Long id);
    void deleteAll();
    Country update(Long id,Country country);
    Country longDescription();
    int countProgrammerInCountry(EnumCountry country);
}
