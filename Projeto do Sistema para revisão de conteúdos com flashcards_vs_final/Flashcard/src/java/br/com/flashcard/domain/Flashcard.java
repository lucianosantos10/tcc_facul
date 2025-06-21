/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.flashcard.domain;
import java.util.ArrayList;

/**
 *
 * @author Luciano
 */
public class Flashcard {

    private ArrayList ndificuldade;
   
    private Long fc_id;
    
    private String fc_dificuldade;
    
    private String fc_pergunta;

    private String fc_resposta;
    
    private String fc_completa;
    
    private String fc_completa_pg;
    
    private ArrayList fc_completa_pg_por_tema;
    
     private String fc_completa_rp;

    private Tema tema ;
    //composição
    // tema objeto do tipo Tema(classe que referencia)

    public Long getFc_id() {
        return fc_id;
    }

    public void setFc_id(Long fc_id) {
        this.fc_id = fc_id;
    }

    public String getFc_dificuldade() {
        return fc_dificuldade;
    }

    public void setFc_dificuldade(String fc_dificuldade) {
        this.fc_dificuldade = fc_dificuldade;
    }
    

    public String getFc_pergunta() {
        return fc_pergunta;
    }

    public void setFc_pergunta(String fc_pergunta) {
        this.fc_pergunta = fc_pergunta;
    }

    public String getFc_resposta() {
        return fc_resposta;
    }

    public void setFc_resposta(String fc_resposta) {
        this.fc_resposta = fc_resposta;
    }

    public ArrayList getNdificuldade() {
        return ndificuldade;
    }

    public void setNdificuldade(ArrayList ndificuldade) {
        this.ndificuldade = ndificuldade;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getFc_completa() {
        return fc_completa;
    }

    public void setFc_completa(String fc_completa) {
        this.fc_completa = fc_completa;
    }

    public String getFc_completa_pg() {
        return fc_completa_pg;
    }

    public void setFc_completa_pg(String fc_completa_pg) {
        this.fc_completa_pg = fc_completa_pg;
    }

    public ArrayList getFc_completa_pg_por_tema() {
        return fc_completa_pg_por_tema;
    }

    public void setFc_completa_pg_por_tema(ArrayList fc_completa_pg_por_tema) {
        this.fc_completa_pg_por_tema = fc_completa_pg_por_tema;
    }

   
    
    

    public String getFc_completa_rp() {
        return fc_completa_rp;
    }

    public void setFc_completa_rp(String fc_completa_rp) {
        this.fc_completa_rp = fc_completa_rp;
    }

    public  ArrayList nivelDificuldade(){
        ndificuldade = new ArrayList<>();
        //ndificuldade.add("Selecione");
        ndificuldade.add("Fácil");
        ndificuldade.add("Média");
        ndificuldade.add("Difícil");
        return ndificuldade;
    }
   
     
   
}
