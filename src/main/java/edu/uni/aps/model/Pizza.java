
package edu.uni.aps.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pizzas")
public class Pizza {

    public Pizza(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Getter public long id;
    @Column(name = "sabor_pizza")
    @Setter public String sabor;
    @Column(name = "desc_pizza")
    @Setter public String descricao;
    @Column(name = "prec_unit", nullable = false)
    @Setter public double valor;
}