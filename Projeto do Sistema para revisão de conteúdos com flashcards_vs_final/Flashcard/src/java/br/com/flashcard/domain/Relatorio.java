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
public class Relatorio {

    private Long rt_id;

    private String rt_resultado;
    
    private String rt_pergunta;
    
     private String rt_tema;
     
    private String rt_completa_perguntas;
    
    private String rt_completa_resultados;
    
     private String rt_completa_resultados_mais_err;

    //private Long rt_erro;

    private Flashcard flashcard;

    private Tema tema;

    public Long getRt_id() {
        return rt_id;
    }

    public void setRt_id(Long rt_id) {
        this.rt_id = rt_id;
    }

    public String getRt_resultado() {
        return rt_resultado;
    }

    public void setRt_resultado(String rt_resultado) {
        this.rt_resultado = rt_resultado;
    }

    public String getRt_pergunta() {
        return rt_pergunta;
    }

    public void setRt_pergunta(String rt_pergunta) {
        this.rt_pergunta = rt_pergunta;
    }

    public String getRt_tema() {
        return rt_tema;
    }

    public void setRt_tema(String rt_tema) {
        this.rt_tema = rt_tema;
    }

    public String getRt_completa_perguntas() {
        return rt_completa_perguntas;
    }

    public void setRt_completa_perguntas(String rt_completa_perguntas) {
        this.rt_completa_perguntas = rt_completa_perguntas;
    }

    public String getRt_completa_resultados() {
        return rt_completa_resultados;
    }

    public void setRt_completa_resultados(String rt_completa_resultados) {
        this.rt_completa_resultados = rt_completa_resultados;
    }

    public String getRt_completa_resultados_mais_err() {
        return rt_completa_resultados_mais_err;
    }

    public void setRt_completa_resultados_mais_err(String rt_completa_resultados_mais_err) {
        this.rt_completa_resultados_mais_err = rt_completa_resultados_mais_err;
    }
    
    

    public Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(Flashcard flashcard) {
        this.flashcard = flashcard;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

}
