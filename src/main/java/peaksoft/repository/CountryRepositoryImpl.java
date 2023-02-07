package peaksoft.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfiguration;
import peaksoft.entity.Address;
import peaksoft.entity.Country;
import peaksoft.enums.EnumCountry;

import java.util.List;

public class CountryRepositoryImpl implements CountryRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory= DatabaseConfiguration.getEntityManagerFactory();
    @Override
    public String save(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public List<Country> saveAll(List<Country> countries) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Country country : countries) {
            entityManager.persist(country);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return countries;

    }

    @Override
    public List<Country> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> countryList = entityManager.createQuery
                ("select c from Country c", Country.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return countryList ;

    }

    @Override
    public Country findById(Long id) {EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country id1 = entityManager.createQuery("select c  from Country  c where c.id=:id ", Country.class)
                .setParameter("id", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;

    }

    @Override
    public String deleteById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country= entityManager.createQuery("select c from Country c  where c.id=:id",
                        Country.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.remove(country);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully deleted";

    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table countries cascade ", Country.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public Country update(Long id, Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country c = entityManager.createQuery("select c from Country c where c.id=:a", Country.class)
                .setParameter("id", id)
                .getSingleResult();
        c.setCountry(country.getCountry());
        c.setDescription(country.getDescription());

        entityManager.getTransaction().commit();
        entityManager.close();
        return c;

    }

    @Override
    public Country longDescription() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("select * from countries order by length(description) desc limit 1", Country.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public int countProgrammerInCountry(EnumCountry country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> country1 = entityManager.createQuery("select c from Country  c where c.country=:country1", Country.class)
                .setParameter("country1", country).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return country1.size();
    }

    @Override
    public void close() throws Exception {

    }
}
