package peaksoft.service;

import peaksoft.entity.Project;
import peaksoft.repository.ProjectRepository;
import peaksoft.repository.ProjectRepositoryImpl;

import java.util.List;

public class ProjectServiceImpl implements ProjectService{
    ProjectRepository projectRepository=new ProjectRepositoryImpl();
    @Override
    public String save(Project project) {
        projectRepository.save(project);
        return "Successfully saved";
    }

    @Override
    public List<Project> saveAll(List<Project> projects) {
        return projectRepository.saveAll(projects);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        projectRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public void deleteAll() {
        projectRepository.deleteAll();

    }

    @Override
    public Project update(Long id, Project project) {
        return projectRepository.update(id, project);
    }

    @Override
    public void assignProgrammerToProject(Long programmerId, Long projectId) {
        projectRepository.assignProgrammerToProject(programmerId,projectId);

    }

    @Override
    public int expensiveProject() {
        return projectRepository.expensiveProject();
    }

    @Override
    public int fastWrittenProject() {
        return projectRepository.fastWrittenProject();
    }
}
