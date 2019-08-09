package com.gabia.internproject.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Setter
@Getter
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Column(name = "user_no") int user_no;
    private @Column(name = "user_name") String name;
    private @Column(name = "user_phone") String phone;


}
