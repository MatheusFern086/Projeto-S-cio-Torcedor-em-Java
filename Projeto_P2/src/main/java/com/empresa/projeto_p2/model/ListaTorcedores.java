package com.empresa.projeto_p2.model;
import java.util.ArrayList;
import java.util.List;

public class ListaTorcedores {
    //Atributo de Lista
    private static List<Torcedor> lista;
    
    //Construtor da classe
    private ListaTorcedores(){}
    
    //Cria uma lista de torcedores que irá armazenar todos os torcedores cafdastrados
    public static List<Torcedor> getInstance(){
        if(lista == null){
            lista = new ArrayList<>();
        }
        return lista;
    }
}
