/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.flashcard.domain;


/**
 *
 * @author Luciano
 */

public class Tema {
    private Long tm_id;
    
    private String tm_nome;

    public Long getTm_id() {
        return tm_id;
    }

    public void setTm_id(Long tm_id) {
        this.tm_id = tm_id;
    }

    public String getTm_nome() {
        return tm_nome;
    }

    public void setTm_nome(String tm_nome) {
        this.tm_nome = tm_nome;
    }

   
    
}
