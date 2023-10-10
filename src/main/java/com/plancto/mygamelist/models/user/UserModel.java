package com.plancto.mygamelist.models.user;

import com.plancto.mygamelist.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Data
public class UserModel implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "TB_USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneModel> phone; // tel cannot be null.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private LocationModel location;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role.contains(RoleName.ROLE_ADMIN)) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
}
