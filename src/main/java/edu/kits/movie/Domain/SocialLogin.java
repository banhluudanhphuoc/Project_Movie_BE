package edu.kits.movie.Domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "social_login")
public class SocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_login_id", nullable = false)
    private Integer id;

    @Column(name = "provider", length = 50)
    private String provider;

    @OneToOne(mappedBy = "socialLogin")
    private Account account;

}