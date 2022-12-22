/*
package de.berlin.htw.entity;

import de.berlin.htw.entity.dao.ProjectRepository;
import de.berlin.htw.entity.dao.UserRepository;
import de.berlin.htw.entity.dto.ProjectEntity;
import de.berlin.htw.entity.dto.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.TransactionalException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRepositoryTest {
    static final String ProjectID = "4e6b06cc-bdf2-4788-9e6b-40c17f085618";
    static final String NAME = "MalikKeremTidiany";

    @Inject
    ProjectRepository repository;

    @Inject
    UserTransaction transaction;

    @AfterEach
    void cleanUp() throws Exception {
        if (transaction.getStatus() != Status.STATUS_NO_TRANSACTION) {
            transaction.rollback();
        }
    }

    @Test
    void testTransactionRequired() {
        assertThrows(
                TransactionalException.class,
                () -> repository.persist(new ProjectEntity()));
    }

    @Test
    void testAddAndGet() throws Exception {
        final ProjectEntity entity = new ProjectEntity();
        entity.setName(NAME);
        entity.setId(ProjectID);

        transaction.begin();
        final String projectId = repository.persist(entity);
        assertNotNull(projectId);
        assertEquals(36, projectId.length());
        transaction.commit();
        repository.getEntityManager().clear();

        assertEquals(ProjectID, repository.get(projectId).getId());
        assertEquals(NAME, repository.get(projectId).getName());
    }

    @Test
    void testValidationOnAdd() throws Exception {
        final ProjectEntity entity = new ProjectEntity();
        entity.setName(NAME);
        entity.setId(ProjectID);

        transaction.begin();
        assertThrows(
                ConstraintViolationException.class,
                () -> repository.persist(entity));
        transaction.rollback();
    }

}

*/
