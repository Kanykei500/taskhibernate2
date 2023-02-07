package peaksoft.service;

import peaksoft.entity.Programmer;
import peaksoft.enums.EnumCountry;
import peaksoft.repository.ProgrammerRepository;
import peaksoft.repository.ProgrammerRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class ProgrammerServiceImpl implements ProgrammerService{
    ProgrammerRepository programmerRepository=new ProgrammerRepositoryImpl();

    @Override
    public String save(Long addressId, Programmer programmer) {
        programmerRepository.save(addressId, programmer);
        return "Successfully saved";
    }

    @Override
    public List<Programmer> saveAll(List<Programmer> programmers,Long addressId) {
        return programmerRepository.saveAll(programmers,addressId);
    }

    @Override
    public String addConstraint() {
        programmerRepository.addConstraint();

        return "Successfully added";
    }




    @Override
    public String deleteAll() {
        programmerRepository.deleteAll();
        return "Successfully deleted";
    }

    @Override
    public Programmer update(Long id, Programmer programmer) {
        return programmerRepository.update(id, programmer);
    }

    @Override
    public Programmer findByCountry(EnumCountry country) {
        return programmerRepository.findByCountry(country);
    }

    @Override
    public int findYoungest() {

        return programmerRepository.findYoungest();
    }

    @Override
    public int findEldest() {
        return programmerRepository.findEldest();
    }
}
