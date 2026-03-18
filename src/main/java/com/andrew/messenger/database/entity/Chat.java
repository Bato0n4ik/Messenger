package com.andrew.messenger.database.entity;

import com.andrew.messenger.database.mongo.ChatType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"userChats"})
@ToString(exclude = {"userChats"})
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Chat implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<UserChat> userChats = new ArrayList<>();
}
