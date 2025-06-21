/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.flashcard.bean;

import br.com.flashcard.dao.RelatorioDAO;
import br.com.flashcard.dao.TemaDAO;
import br.com.flashcard.domain.Flashcard;
import br.com.flashcard.domain.Relatorio;
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

@ManagedBean(name = "MBRelatorio")
@ViewScoped
public class RelatorioBean {
    //private RelatorioDAO rdao = new RelatorioDAO();
    private Relatorio relatorio;
     private Flashcard flashcard;
     List<String> recebeperguntas;
     
     int recebecontadorerro;
     int recebecontadoracerto;
     List<String> receberesultados;  
     //List para perguntas mais erradas
     List<String> receberesultadosmaiserrados;
     int conta;
     int contaA;
     int contaE;
     int ac;
     int err;
     
     int lcontaE=0;
     int lcontaA=0;
    //private List<String> relatorios;
    @PostConstruct
    public void init() {
        ac=1;
        err=1;
        
        relatorio = new Relatorio();
        flashcard = new Flashcard();
        recebeperguntas = new ArrayList<>();
        receberesultados = new ArrayList<>();
        
        receberesultadosmaiserrados = new ArrayList<>();
         
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
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

    public List<String> getReceberesultados() {
        return receberesultados;
    }

    public void setReceberesultados(List<String> receberesultados) {
        this.receberesultados = receberesultados;
    }

    public List<String> getReceberesultadosmaiserrados() {
        return receberesultadosmaiserrados;
    }

    public void setReceberesultadosmaiserrados(List<String> receberesultadosmaiserrados) {
        this.receberesultadosmaiserrados = receberesultadosmaiserrados;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    

    public int getRecebecontadorerro() {
        return recebecontadorerro;
    }

    public void setRecebecontadorerro(int recebecontadorerro) {
        this.recebecontadorerro = recebecontadorerro;
    }

    public int getRecebecontadoracerto() {
        return recebecontadoracerto;
    }

    public void setRecebecontadoracerto(int recebecontadoracerto) {
        this.recebecontadoracerto = recebecontadoracerto;
    }

   

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public int getContaA() {
        return contaA;
    }

    public void setContaA(int contaA) {
        this.contaA = contaA;
    }

    public int getContaE() {
        return contaE;
    }

    public void setContaE(int contaE) {
        this.contaE = contaE;
    }

    public int getLcontaE() {
        return lcontaE;
    }

    public void setLcontaE(int lcontaE) {
        this.lcontaE = lcontaE;
    }

    public int getLcontaA() {
        return lcontaA;
    }

    public void setLcontaA(int lcontaA) {
        this.lcontaA = lcontaA;
    }

   
    

     public void cadastrarAc() {
        
        RelatorioDAO rdao = new RelatorioDAO();
        if (rdao.cadastrarCorreto()) {
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage( "Cadastro realizado com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage("Erro ao realizar o cadastro!"));

        }
    }
     
     public int contadorerro(){
         
        contaE = err++;
        
        this.recebecontadorerro = contaE;
        return  recebecontadorerro;  
     }
     
      public int contadoracerto(){
       
        contaA = ac++;
        
        this.recebecontadoracerto = contaA;
        return  recebecontadoracerto;  
     }
      
     public int limpacontador(){
         
        err=1;
        ac=1;
         
        
        this.recebecontadoracerto = lcontaA;
        this.recebecontadorerro = lcontaE;
        
        return  recebecontadoracerto + recebecontadorerro; 
        
     } 
     
     public void cadastrarErr() {
        
        RelatorioDAO rdao = new RelatorioDAO();
        if (rdao.cadastrarErro(relatorio)) {
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

 // relatorio de quant. de perguntas por tema
  public List<String> listaPerguntas(Flashcard f) {
 List<String> perguntas = new ArrayList<>();
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
            //this.flashcard.setFc_completa_pg("Selecione");
            listaPerguntas(flashcard);
}
 
 /*-----------------------------|||--------------------------------*/
// relatorio de acertos/erros por tema ->> NÃO VOU USAR

  public List<String> listaResultados(Relatorio r){
 List<String> resultados = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 String sql = ("SELECT rt_resultado && flashcards_fc_pergunta FROM relatorios "
                                  +  "WHERE temas_tm_nome= ?");
 PreparedStatement ps = conexao.prepareStatement(sql);
  ps.setString(1, r.getRt_tema());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Relatorio rt = new Relatorio();
 rt.setRt_resultado(rs.getString("rt_resultado"));
 rt.setRt_pergunta(rs.getString("flashcards_fc_pergunta"));
 resultados.add(rt.getRt_resultado()); 
 resultados.add(rt.getRt_pergunta()); 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return resultados;
 }
 


public List<String> resultadoSelecionado() {
       this.receberesultados = listaResultados(relatorio);
       return  receberesultados;    
 }  

  public void buscarResultados(){
            listaResultados(relatorio);
}
  
// relatorio de perguntas mais erradas por tema --> NÃO VOU USAR 
  
  public List<String> listaResultadosMaisErrados(Relatorio r){
 List<String> resultados = new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 String sql = ("SELECT rt_resultado='errou' >=2 && flashcards_fc_pergunta FROM relatorios "
                                  +  "WHERE temas_tm_nome= ?");
 PreparedStatement ps = conexao.prepareStatement(sql);
  ps.setString(1, r.getRt_tema());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Relatorio rt = new Relatorio();
 rt.setRt_resultado(rs.getString("rt_resultado"));
 rt.setRt_pergunta(rs.getString("flashcards_fc_pergunta"));
 resultados.add(rt.getRt_resultado()); 
 resultados.add(rt.getRt_pergunta()); 
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 
 return resultados;
 }
 


public List<String> resultadomaiserradoSelecionado() {
       this.receberesultadosmaiserrados = listaResultadosMaisErrados(relatorio);
       return  receberesultadosmaiserrados;    
 }  

  public void buscarResultadosMaisErrados(){
            listaResultadosMaisErrados(relatorio);
}
    
}
    

