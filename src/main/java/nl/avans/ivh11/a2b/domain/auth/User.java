package nl.avans.ivh11.a2b.domain.auth;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a User within the application
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "{NotEmpty}")
    @Length(min = 4, message = "{Size.registerForm.username}")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "{NotEmpty}")
    @Length(min = 6, message = "{Size.registerForm.password}")
    private String password;

    @Transient
    private String plainPassword;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(targetEntity = Character.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id")
    private Character character;

    public User() {
        this.roles = new HashSet<>();
    }

    /**
     * Add Role
     * @param role
     */
    public void addRole(Role role) {
        this.roles.add(role);
    }
}
