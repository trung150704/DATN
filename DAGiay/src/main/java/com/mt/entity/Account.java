package com.mt.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    String id;
    String username;
    String password;
    String fullname;
    boolean role;
    String email;
    String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date created_at = new Date();

    @OneToMany(mappedBy = "account")
    List<Order> orders;

    @OneToMany(mappedBy = "account")
    List<FavoriteProduct> favoriteProducts;

    // Getters and Setters
}
