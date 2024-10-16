package br.com.fiap.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    public Cliente(String nome, String email, String cpf, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        setCpf(cpf);
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    private void setCpf(String cpf) {
        validaCpf(cpf);
        this.cpf = cpf;
    }

    private void validaCpf(String cpf) {
        if(cpf.length() != 11) {
            throw new RuntimeException("CPF invalido");
        }
        if(cpf.contains(".") || cpf.contains("-")) {
            cpf = cpf.replace(".", "").replace("-", "");
        }
        for(int i = 0; i < 10; i++){
            if(cpf.equals(String.valueOf(i).repeat(11))){
                throw new RuntimeException("CPF invalido");
            }
        }

        List<Integer> digitos = new ArrayList<>(Arrays.stream(cpf.split("")).map(Integer::parseInt).toList());

        List<Integer> digitosVerificadores = new ArrayList<>(Arrays.asList(digitos.get(9), digitos.get(10)));
        digitos.remove(10   );
        digitos.remove(9);


        List<Integer> digitosAchado = new ArrayList<>();
        int soma = 0;
        for(int i = 1; i <= 9; i++){
            soma += digitos.get(i - 1) * i;
        }
        if(soma % 11 == 10){
            digitosAchado.add(0);
        } else {
            digitosAchado.add(soma % 11);
        }

        digitos.add(digitosAchado.getFirst());

        soma = 0;
        for(int i = 0; i <= 9; i++){
            soma += digitos.get(i) * i;
        }
        if(soma % 11 == 10){
            digitosAchado.add(0);
        } else {
            digitosAchado.add(soma % 11);
        }

        if(!digitosAchado.equals(digitosVerificadores)){
            throw new RuntimeException("CPF invalido");
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}

