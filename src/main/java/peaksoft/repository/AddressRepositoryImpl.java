package peaksoft.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfiguration;
import peaksoft.entity.Address;
import peaksoft.entity.Country;

import java.util.List;

public class AddressRepositoryImpl implements AddressRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory= DatabaseConfiguration.getEntityManagerFactory();

    @Override
    public String save(Long countryId,Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, countryId);
        address.setCountry(country);
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully saved";
    }

    @Override
    public List<Address> saveAll(List<Address> addresses,Long countryId) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Address a : addresses) {
            entityManager.persist(a);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return addresses;
    }

    @Override
    public List<Address> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Address> selectAFromAddressA = entityManager.createQuery
                ("select a from Address a", Address.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return selectAFromAddressA ;
    }

    @Override
    public Address findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address id1 = entityManager.createQuery("select a  from Address  a where a.id=:id ", Address.class)
                .setParameter("id", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public String deleteById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address= entityManager.createQuery("select a from Address a  where a.id=:id",
                        Address.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.remove(address);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully deleted";

    }

    @Override
    public String deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table addresses cascade ", Address.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully deleted";
    }

    @Override
    public Address update(Long id, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address id1 = entityManager.createQuery("select a from Address a where a.id=:a", Address.class)
                .setParameter("id", id)
                .getSingleResult();
        id1.setCountry(address.getCountry());
        id1.setStreet(address.getStreet());
        id1.setRegionName(address.getRegionName());
        id1.setHomeNumber(address.getHomeNumber());
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;
    }

    @Override
    public void close() throws Exception {

    }
}
