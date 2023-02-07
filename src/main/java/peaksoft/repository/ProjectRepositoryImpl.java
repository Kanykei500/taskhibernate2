package peaksoft.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConfiguration;
import peaksoft.entity.Address;
import peaksoft.entity.Country;
import peaksoft.entity.Programmer;
import peaksoft.entity.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository,AutoCloseable{
    private final EntityManagerFactory entityManagerFactory= DatabaseConfiguration.getEntityManagerFactory();

    @Override
    public String save(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
        entityManager.close();


        return "Successfully saved";
    }

    @Override
    public List<Project> saveAll(List<Project> projects) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Project project : projects) {
            entityManager.persist(project);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return projects;

    }

    @Override
    public List<Project> getAll() {EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Project> projects = entityManager.createQuery
                ("select p from Project a", Project.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return projects ;

    }

    @Override
    public Project findById(Long id) {EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project id1 = entityManager.createQuery("select p  from Project  p where p.id=:id ", Project.class)
                .setParameter("id", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;

    }

    @Override
    public String deleteById(Long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project project= entityManager.createQuery("select p from Project p  where p.id=:id",
                        Project.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.remove(project);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Successfully deleted";

    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("truncate table projects cascade ", Project.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public Project update(Long id, Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Project id1 = entityManager.createQuery("select p from Project p where p.id=:a", Project.class)
                .setParameter("id", id)
                .getSingleResult();
        id1.setDateOfFinish(project.getDateOfFinish());
        id1.setDescription(project.getDescription());
        id1.setPrice(project.getPrice());
        id1.setDateOfStart(project.getDateOfStart());
        id1.setProjectName(project.getProjectName());

        entityManager.getTransaction().commit();
        entityManager.close();
        return id1;

    }

    @Override
    public void assignProgrammerToProject(Long programmerId, Long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Programmer programmer = entityManager.find(Programmer.class, programmerId);
        Project project = entityManager.find(Project.class, projectId);
        List<Programmer> programmerList = new ArrayList<>(Arrays.asList(programmer));
        List<Project> projectList = new ArrayList<>(Arrays.asList(project));
        project.setProgrammers(programmerList);
        programmer.setProjects(projectList);
        entityManager.merge(programmer);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public int expensiveProject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int i = entityManager.createNativeQuery("select * from projects order by price desc limit 1", Project.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return i;
    }

    @Override
    public int fastWrittenProject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int i = entityManager.createNativeQuery("select*, project_name ,date_of_finish-date_of_start as year from projects order by year limit 1 ", Project.class).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return i;
    }

    @Override
    public void close() throws Exception {

    }
}
