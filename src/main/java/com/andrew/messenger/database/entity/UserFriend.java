package com.andrew.messenger.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_friends")
public class UserFriend implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_1")
    private User user_1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_2")
    private User user_2;

    /*
    public void setUser(User user_1) {
        this.user_1 = user_1;
        user_1.getUserFriends().add(this);
    }

    public void setFriend( User user_2) {
        this.user_2 = user_2;
        user_2.getUserFriends().add(this);
    }*/
}
