package de.berlin.htw.entity.dao;

import de.berlin.htw.entity.dto.ProjectEntity;
import de.berlin.htw.entity.dto.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ProjectRepository {

        @PersistenceContext
        EntityManager entityManager;

        @Transactional(Transactional.TxType.NEVER)
        public List<ProjectEntity> getAll() {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ProjectEntity> cq = cb.createQuery(ProjectEntity.class);
            Root<ProjectEntity> rootEntry = cq.from(ProjectEntity.class);
            CriteriaQuery<ProjectEntity> all = cq.select(rootEntry);
            TypedQuery<ProjectEntity> allQuery = entityManager.createQuery(all);
            return allQuery.getResultList();
        }

        @Transactional(Transactional.TxType.SUPPORTS)
        public ProjectEntity get(final String projectId) {
            final UUID id = UUID.fromString(projectId);
            return entityManager.find(ProjectEntity.class, id);
        }

        public String add(@Valid final ProjectEntity project) {
            entityManager.persist(project);
            return project.getId();
        }

        public ProjectEntity set(@Valid final ProjectEntity project) {
            return entityManager.merge(project);
        }

        public boolean remove(final String projectId) {
            final UUID id = UUID.fromString(projectId);
            return entityManager.createNamedQuery("ProjectEntity.deleteById")
                    .setParameter("id", id)
                    .executeUpdate() > 0;
        }

        @Transactional(Transactional.TxType.SUPPORTS)
        public EntityManager getEntityManager() {
            return entityManager;
        }

    }

