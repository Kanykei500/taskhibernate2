package peaksoft.service;

import peaksoft.entity.Project;

import java.util.List;

public interface ProjectService {
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
