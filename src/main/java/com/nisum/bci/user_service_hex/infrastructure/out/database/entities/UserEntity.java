package com.nisum.bci.user_service_hex.infrastructure.out.database.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "security" ,name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String token;

    private Boolean isActive = true;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhoneEntity> phones = new ArrayList<>();


}
