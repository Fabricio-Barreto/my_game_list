package com.plancto.mygamelist.models.user;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleModel> getRole() {
        return role;
    }

    public void setRole(List<RoleModel> role) {
        this.role = role;
    }

    public List<PhoneModel> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneModel> phone) {
        this.phone = phone;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
