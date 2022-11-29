package com.empresa.projeto_p2.model;

public abstract class Torcedor {
    //Atributos do Torcedor
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private String plano;
    private int ingresso;
    
    //Construtor com as informações informadas
    public Torcedor(int id,String nome, String cpf, String email, String celular, String plano, int ingresso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.plano = plano;
        this.ingresso = ingresso;
    }
    
    //Métodos Gets e Sets
    public int getIngresso() {
        return ingresso;
    }

    public void setIngresso(int ingresso) {
        this.ingresso = ingresso;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }
}