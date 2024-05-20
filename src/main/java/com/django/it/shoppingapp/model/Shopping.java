package com.django.it.shoppingapp.model;

import com.django.it.shoppingapp.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name="shoppings")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder @ToString
public class Shopping extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @Column(name = "statut")
    private boolean statut;
    @Column(name = "archived")
    private boolean archived;
    @Column(name = "shared")
    private boolean shared;
    @Column(name = "saver_name")
    private String saverName;
    @Column(name = "local_date")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "shopping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Collection<Task> tasks;

    @ManyToMany
    @JoinTable(name = "shopping_user", joinColumns=@JoinColumn(name="shopId"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Collection<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getShoppings().remove(this);
    }
}
