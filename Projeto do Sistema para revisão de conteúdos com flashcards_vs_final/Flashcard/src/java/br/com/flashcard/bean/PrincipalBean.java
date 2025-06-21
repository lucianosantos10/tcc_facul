/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.flashcard.bean;

import br.com.flashcard.dao.TemaDAO;
import br.com.flashcard.domain.Flashcard;
import br.com.flashcard.domain.Tema;
import br.com.flashcard.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;






@ManagedBean(name = "MBPrincipal") 
@ViewScoped
public class PrincipalBean {
    private Flashcard flashcard; 
   List<String> recebeperguntas;
   List<String> receberesposta;
   List<String> recebeperguntasportema;
   
   
 @PostConstruct
    public void init() {
        flashcard = new Flashcard();
        recebeperguntas = new ArrayList<>();
        receberesposta = new ArrayList<>();
        recebeperguntasportema = new ArrayList<>();
    }
    
    public Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(Flashcard flashcard) {
        this.flashcard = flashcard;
    }

    public List<String> getRecebeperguntas() {
        return recebeperguntas;
    }

    public void setRecebeperguntas(List<String> recebeperguntas) {
        this.recebeperguntas = recebeperguntas;
    }
    
    public List<String> getReceberesposta() {
        return receberesposta;
    }

    public void setReceberesposta(List<String> receberesposta) {
        this.receberesposta = receberesposta;
    }

    public List<String> getRecebeperguntasportema() {
        return recebeperguntasportema;
    }

    public void setRecebeperguntasportema(List<String> recebeperguntasportema) {
        this.recebeperguntasportema = recebeperguntasportema;
    }

    

 public List<String> listaTemas(String query) {
 List<String> temas = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 StringBuilder sql = new StringBuilder();
		sql.append("SELECT tm_nome ");
		sql.append("FROM temas");
 PreparedStatement ps = conexao.prepareStatement(sql.toString());
 ResultSet rs = ps.executeQuery();
 //temas.add("Selecione um tema");
 while(rs.next()) {
 Tema tm = new Tema();
 //tm.setTm_id(rs.getLong("tm_id"));
 tm.setTm_nome(rs.getString("tm_nome"));
 String teste = tm.getTm_nome();
 if (teste.toLowerCase().contains(query.toLowerCase())) {
            //tm.getTm_id().toString() + " " +
            temas.add(tm.getTm_nome());
 }
 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return temas;
 }

 public List<String> listaPerguntas(Flashcard f) {
 List<String> perguntas = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 String sql = ("SELECT fc_pergunta FROM flashcards "
                                  +  "WHERE temas_tm_nome = ? && fc_dificuldade= ?");
 PreparedStatement ps = conexao.prepareStatement(sql);
  ps.setString(1, f.getFc_completa());
  ps.setString(2, f.getFc_dificuldade());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Flashcard fc = new Flashcard();
 fc.setFc_pergunta(rs.getString("fc_pergunta"));
 perguntas.add(fc.getFc_pergunta()); 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return perguntas;
 }
 
 public List<String> perguntaSelecionada() {
       this.recebeperguntas = listaPerguntas(flashcard); 
       return  recebeperguntas;    
 }
 public void buscarPergunta(){
            this.flashcard.setFc_completa_pg("");
            listaPerguntas(flashcard);
}
 
 public List<String> listaPerguntasPorTema(Flashcard f) {
 List<String> perguntasportema = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 String sql = ("SELECT fc_pergunta FROM flashcards "
                                  +  "WHERE temas_tm_nome = ?");
 PreparedStatement ps = conexao.prepareStatement(sql);
  ps.setString(1, f.getFc_completa());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Flashcard fc = new Flashcard();
 fc.setFc_pergunta(rs.getString("fc_pergunta"));
 perguntasportema.add(fc.getFc_pergunta()); 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return perguntasportema;
 }
 
 public List<String> perguntaporTemaSelecionada() {
      // listaPerguntasPorTema(flashcard);
       this.recebeperguntasportema= listaPerguntasPorTema(flashcard); 
       return  recebeperguntasportema;    
 }
 public void buscarPerguntaPorTema(){
            //this.flashcard.setFc_completa_pg("");
            listaPerguntasPorTema(flashcard);
}
 
 public List<String> listaResposta(Flashcard f){
 List<String> respostas = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 String sql = ("SELECT fc_resposta FROM flashcards "
                                  +  "WHERE fc_pergunta= ?");
 PreparedStatement ps = conexao.prepareStatement(sql);
  ps.setString(1, f.getFc_completa_pg());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Flashcard fc = new Flashcard();
 fc.setFc_resposta(rs.getString("fc_resposta"));
 respostas.add(fc.getFc_resposta()); 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return respostas;
 }
 

public List<String> respostaSelecionada() {
       this.receberesposta = listaResposta(flashcard);
       return  receberesposta;    
 }  


  public void buscarResposta(){
            listaResposta(flashcard);
}
  

    
}
