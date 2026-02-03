package com.andrew.messenger.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"userChats"})
@ToString(exclude = {"userChats"})
@Builder
public class Chat implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String messages;

    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<UserChat> userChats = new ArrayList<>();
}
