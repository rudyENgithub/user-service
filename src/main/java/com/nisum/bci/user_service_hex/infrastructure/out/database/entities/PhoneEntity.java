package com.nisum.bci.user_service_hex.infrastructure.out.database.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "security" ,name="user_phones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PhoneEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_phones_id")
    private UUID id;

    @Column(name = "phone_number", nullable = false)
    private String number;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime created;
    private LocalDateTime modified;

}