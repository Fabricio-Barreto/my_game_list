package com.plancto.mygamelist.dtos;

import com.plancto.mygamelist.models.user.LocationModel;
import com.plancto.mygamelist.models.user.PhoneModel;
import com.plancto.mygamelist.models.user.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
    private UUID userId;
    private String email;
    private String password;
    private List<RoleModel> role;
    private List<PhoneModel> phone;
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
