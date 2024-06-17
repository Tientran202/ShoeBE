package org.example.shoe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_id;
    private boolean approved;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Seller() {
    }

    public Seller(Long seller_id, boolean approved, User user) {
        this.seller_id = seller_id;
        this.approved = approved;
        this.user = user;
    }

//    get and set

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
