package com.andrew.messenger.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"userChats", "userFriends"})
@ToString(exclude = {"userChats", "userFriends"})
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserFriend> userFriends = new ArrayList<>();
}
