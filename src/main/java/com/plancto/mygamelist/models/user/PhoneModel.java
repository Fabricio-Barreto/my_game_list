package com.plancto.mygamelist.models.user;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PHONE")
@Data
public class PhoneModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phoneId;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private Boolean isMainPhone;

}
