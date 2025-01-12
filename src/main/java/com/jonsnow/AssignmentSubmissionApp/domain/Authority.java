package com.jonsnow.AssignmentSubmissionApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@Entity
public class Authority  implements GrantedAuthority {

    private static final long serialVersionUID = -6520888182797362903L;


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToOne
    @JsonBackReference(value = "userAuthorityReference")
    private User user;

    public Authority (String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
