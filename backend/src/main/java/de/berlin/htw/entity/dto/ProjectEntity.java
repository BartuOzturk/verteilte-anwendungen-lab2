package de.berlin.htw.entity.dto;

import io.quarkus.security.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NamedQuery(name = "ProjectEntity.deleteById", query = "delete from ProjectEntity user where user.id = :id")
@Entity
@Table(name = "Project")
public class ProjectEntity extends AbstractEntity {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(
                name = "UUID",
                strategy = "org.hibernate.id.UUIDGenerator"
        )
        @Type(type = "org.hibernate.type.UUIDCharType")
        @Column(name = "ID", nullable = false, length = 36)
        private UUID id;

        private String name;

        @ManyToMany
        @JoinTable(
                name = "USER_PROJECT_JUNCTION",
                joinColumns = @JoinColumn(name = "P_ID"),
                inverseJoinColumns = @JoinColumn(name = "U_ID")
        )
        private List<UserEntity> users;

        // getters and setters for id, name, and users

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<UserEntity> getUsers() {
            return users;
        }

        public void setUsers(List<UserEntity> users) {
            this.users = users;
        }
    }

