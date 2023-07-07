package edu.kits.movie.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "Passwords")
    private String passwords;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "billing_plan_id", nullable = false)
    private BillingPlan billingPlan;

    @Column(name = "billing_plan_expired_at")
    private Instant billingPlanExpiredAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_login_id")
    private SocialLogin socialLogin;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "avatar", length = 50)
    private String avatar;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToMany
    @JoinTable(name = "authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "username")
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "username")
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "username")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<WatchList> watchLists = new LinkedHashSet<>();

}