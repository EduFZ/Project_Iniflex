package br.com.project.iniflex.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
public class Funcionario extends Pessoa{

    public BigDecimal salario;
    public String funcao;

    public Funcionario() {
    }

    public Funcionario(String nome, LocalDate dataNasc, BigDecimal salario, String funcao) {
        super(nome, dataNasc);
        this.salario = salario;
        this.funcao = funcao;
    }
}
