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
@EqualsAndHashCode(exclude = {"userChats", "userFriends", "userFriendsTwo"})
@ToString(exclude = {"userChats", "userFriends", "userFriendsTwo"})
@Builder
@Table(name = "users")
public class User implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String firstname;

    private String lastname;

    @JoinColumn(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private String password;

    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserChat> userChats = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user_1",  cascade = CascadeType.ALL)
    private List<UserFriend> userFriends = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user_2",  cascade = CascadeType.ALL)
    private List<UserFriend> userFriendsTwo = new ArrayList<>();
}
