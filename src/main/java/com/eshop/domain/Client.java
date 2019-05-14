package com.eshop.domain;

import com.eshop.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

/**
 * Represents the user of the application
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String phone;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "client_addresses",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<ClientAddress> addressList;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }
}