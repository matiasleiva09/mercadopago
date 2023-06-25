package com.sires.mp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="token_np")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenMP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_time")
    private Date dateTime;
    @Column(name="access_token")
    private String accessToken;
    private Long expire;
    @Column(name="refresh_token")
    private String refreshToken;
}
