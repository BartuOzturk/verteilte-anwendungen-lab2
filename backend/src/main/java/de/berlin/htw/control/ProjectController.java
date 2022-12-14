package de.berlin.htw.control;

import de.berlin.htw.entity.dao.ProjectRepository;
import de.berlin.htw.entity.dao.UserRepository;
import de.berlin.htw.entity.dto.ProjectEntity;
import de.berlin.htw.entity.dto.UserEntity;
import de.berlin.htw.lib.dto.UserJson;
import io.quarkus.security.User;
import liquibase.hub.model.Project;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/projects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectController {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    UserRepository userRepository;

    @POST
    @Transactional
    public Response createProject(ProjectEntity project) {
        projectRepository.persist(project);
        return Response.ok(project).status(201).build();
    }

    @POST
    @Transactional
    @Path("/{projectId}/users")
    public Response addUserToProject(@PathParam("projectId") String projectId, UserJson user) {
        ProjectEntity project = projectRepository.get(projectId);
        UserEntity existingUser = userRepository.get(user.getId());

        project.getUsers().add(existingUser);
        existingUser.getProjects().add(project);
        projectRepository.persist(project);
        return Response.ok().build();
    }
}

