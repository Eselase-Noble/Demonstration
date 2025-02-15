package org.nobleson.demonstration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nobleson.demonstration.enums.TokenType;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    public Long id;

    @Column(unique=true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    public AppUser user;


}
