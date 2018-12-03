package com.recipebook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipebook.domain.model.base.BaseObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class UserContact extends BaseObject {
    @Column(name = "email")
    @Email(message = "{validation.email}")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Recipe> recipes = new HashSet<>();
}
