package peaksoft.repository;

import peaksoft.entity.Country;
import peaksoft.entity.Programmer;
import peaksoft.entity.Project;

import java.util.List;

public interface ProjectRepository {
    String save(Project project);
    List<Project> saveAll(List<Project>projects);
    List<Project>getAll();
    Project findById(Long id);
    String deleteById(Long id);
    void  deleteAll();
    Project update(Long id,Project project);
    void assignProgrammerToProject(Long programmerId,Long projectId);
    int expensiveProject();
    int fastWrittenProject();



}
