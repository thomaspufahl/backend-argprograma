package com.thomaspufahl.apiportfolio.Portfolio.Project;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectManager {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> all() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> allByPerson(Person person) {
        return projectRepository.findAllByPerson(person);
    }

    @Override
    public Optional<Project> getById(Integer project_id) {
        return projectRepository.findById(project_id);
    }

    @Override
    public void create(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Optional<Project> editById(Integer project_id, Project project) {
        if (project.getTitle()!=null && project.getTitle().length()>1) {
            getById(project_id).orElseThrow().setTitle(project.getTitle());
        }
        if (project.getDescription()!=null && project.getDescription().length()>1) {
            getById(project_id).orElseThrow().setDescription(project.getDescription());
        }
        if (project.getLink()!=null && project.getLink().length()>1) {
            getById(project_id).orElseThrow().setLink(project.getLink());
        }
        if (project.getFinish()!=null) {
            getById(project_id).orElseThrow().setFinish(project.getFinish());
        }
        if (project.getImg()!=null && project.getImg().length()>1) {
            getById(project_id).orElseThrow().setImg(project.getImg());
        }
        projectRepository.save(getById(project_id).orElseThrow());
        return getById(project_id);
    }

    @Override
    public void delete() {
        projectRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer project_id) {
        projectRepository.deleteById(project_id);
    }
}
