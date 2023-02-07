package peaksoft.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.config.DatabaseConfiguration;
import peaksoft.entity.Address;
import peaksoft.entity.Country;
import peaksoft.entity.Programmer;
import peaksoft.enums.EnumCountry;

import java.time.LocalDate;
import java.util.List;

public class ProgrammerRepositoryImpl implements ProgrammerRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory= DatabaseConfiguration.getEntityManagerFactory();

    @Override
    public String save(Long addressId,Programmer programmer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, addressId);
        programmer.setLocation(address);
        entityManager.persist(programmer);
        entityManager.getTransaction().commit();
        entityManager.close();
        return programmer+"Successfully saved";
    }

    @Override
    public List<Programmer> saveAll(List<Programmer> programmers,Long addressId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Programmer programmer : programmers) {
            entityManager.persist(programmer);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return programmers;

    }

    @Override
    public String addConstraint() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("alter table programmers add unique(email)", Programmer.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        return "Successfully added";
    }






    @Override
    public String deleteAll() { EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table programmers cascade ", Programmer.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully deleted";

    }

    @Override
    public Programmer update(Long id, Programmer programmer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer id1 = entityManager.createQuery("select p from Programmer p where p.id=:a", Programmer.class)
                .setParameter("id", id)
                .getSingleResult();
        id1.setLocation(programmer.getLocation());
        id1.setEmail(programmer.getEmail());
        id1.setDateOfBirth(programmer.getDateOfBirth());
        id1.setFullName(programmer.getFullName());
        id1.setStatus(programmer.getStatus());
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;

    }

    @Override
    public Programmer findByCountry(EnumCountry country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer country1 = entityManager.createQuery(" select p from Programmer p join Country c on p.id = c.id  where c.country=:country1", Programmer.class)
                .setParameter("country1", country)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return country1;
    }

    @Override
    public int findYoungest() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int i = entityManager.createNativeQuery("select * from programmers order by date_of_birth limit 1", Programmer.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return i;
    }

    @Override
    public int findEldest() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int i = entityManager.createNativeQuery("select * from programmers order by date_of_birth desc limit 1", Programmer.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return i;
    }

    @Override
    public void close() throws Exception {

    }
}
