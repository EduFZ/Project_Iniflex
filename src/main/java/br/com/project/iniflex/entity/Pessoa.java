package br.com.project.iniflex.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
public class Pessoa {

    public String nome;
    public LocalDate dataNasc;

    public Pessoa() {
    }

    public Pessoa(String nome, LocalDate dataNasc) {
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

}
