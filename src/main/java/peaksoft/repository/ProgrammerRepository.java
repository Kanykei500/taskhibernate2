package peaksoft.repository;

import peaksoft.entity.Programmer;
import peaksoft.enums.EnumCountry;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

public interface ProgrammerRepository {
    String save(Long addressId,Programmer programmer);
    List<Programmer>saveAll(List<Programmer>programmers,Long addressId);
    String addConstraint();

    String deleteAll();
    Programmer update(Long id,Programmer programmer);
    Programmer findByCountry(EnumCountry country);
  int findYoungest();
   int findEldest();


}
