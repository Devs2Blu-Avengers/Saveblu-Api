package br.com.savebluapi.models;

import br.com.savebluapi.enums.Category;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
@Entity
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Boolean urgent;

    @Column(nullable = false)
    private Boolean status;

    @Column
    private Boolean valid;
}
