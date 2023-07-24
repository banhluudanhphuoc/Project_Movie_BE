package edu.kits.movie.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 100)
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 255)
    @Column(name = "Passwords")
    private String passwords;

    @Size(max = 255)
    @Column(name = "refresh_token")
    private String refreshToken;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "billing_plan_id", nullable = false)
    private BillingPlan billingPlan;

    @Column(name = "billing_plan_expired_at")
    private LocalDateTime billingPlanExpiredAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_login_id")
    private SocialLogin socialLogin;

    @Size(max = 100)
    @NotNull
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Size(max = 150)
    @Column(name = "address", length = 150)
    private String address;


    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Size(max = 50)
    @Column(name = "avatar", length = 50)
    private String avatar;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "email_verified")
    private Boolean emailVerified;
    @ManyToMany
    @JoinTable(name = "authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "username")
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Rating> ratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<WatchList> watchLists = new LinkedHashSet<>();
}