/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.flashcard.bean;

import br.com.flashcard.dao.FlashcardDAO;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "MBFlashcard")
@ViewScoped
public class FlashcardBean {
    private Flashcard flashcard;
    
    @PostConstruct
    public void init() {
        flashcard = new Flashcard();

    }

    public Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(Flashcard flashcard) {
        this.flashcard = flashcard;
    }


    public void cadastrar() {
        
        FlashcardDAO fdao = new FlashcardDAO();
        if (fdao.cadastrar(flashcard)) {
            //após cadastrar ele limpa meus campos ou seja
            //atribui nenhum " " valor neles. 
            //this.flashcard.setFc_completa(" ");
            //this.flashcard.setFc_pergunta(" ");
            //this.flashcard.setFc_resposta(" ");
            //this.flashcard.setFc_dificuldade(" ");
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage( "Cadastro realizado com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage("Erro ao realizar o cadastro!"));

        }
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
 //temas.add("Selecione o código de um tema");
 while(rs.next()) {
 Tema tm = new Tema();
 //tm.setTm_id(rs.getLong("tm_id"));
 tm.setTm_nome(rs.getString("tm_nome"));
 String teste = tm.getTm_nome();
 if (teste.toLowerCase().contains(query.toLowerCase())) {
           //temas.add(tm.getTm_nome() + ":");
          //tm.getTm_id().toString() + 
           temas.add(tm.getTm_nome()); 
            
 }
 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return temas;
 }
    
    
/*
public List<String> completeText(String query) {
       this.assuntos = tdao.listaTemas(query); 
       return  assuntos;    
    
}
    */
   
}
