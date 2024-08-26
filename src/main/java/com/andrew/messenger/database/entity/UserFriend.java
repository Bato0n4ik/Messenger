package com.andrew.messenger.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_friend")
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "friend_id")
    private  Friend friend;

    public void setUser(User user) {
        this.user = user;
        user.getUserFriends().add(this);
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
        friend.getUserFriends().add(this);
    }
}
