package br.com.flashcard.dao;

import br.com.flashcard.domain.Flashcard;
import br.com.flashcard.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FlashcardDAO {

    public boolean cadastrar(Flashcard f) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO flashcards ");
        sql.append("(fc_dificuldade, fc_pergunta, fc_resposta, temas_tm_nome) ");
        sql.append("VALUES (?, ?, ?, ?)");

        try {
            Connection conexao = ConnectionFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            //mapeamento objeto relacional
            ps.setString(1, f.getFc_dificuldade());
            ps.setString(2, f.getFc_pergunta());
            ps.setString(3, f.getFc_resposta());
            ps.setString(4, f.getFc_completa());
            ps.executeUpdate();
            System.out.println("Cadastro realizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            System.out.println("ERRO ao realizar o cadastro :" + ex);
            return false;

        }

    }
 /*   
    
 public List<String> listaPerguntas() {
 List<String> perguntas= new ArrayList<String>();
 try {
Connection conexao = ConnectionFactory.conectar();
 StringBuilder sql = new StringBuilder();
		sql.append("SELECT fc_pergunta ");
		sql.append("FROM flashcards f");
 PreparedStatement ps = conexao.prepareStatement(sql.toString());
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
 
 public List<String> listaRespostas() {
 List<String> respostas = new ArrayList<String>();
 try {
Connection conexao = ConnectionFactory.conectar();
 StringBuilder sql = new StringBuilder();
		sql.append("SELECT fc_resposta ");
		sql.append("FROM flashcards f");
 PreparedStatement ps = conexao.prepareStatement(sql.toString());
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
    
public List<String> listagemPerguntas(Flashcard f) {
 List<String> perguntas= new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 StringBuilder sql = new StringBuilder();
		sql.append("SELECT fc_pergunta");
		sql.append("FROM flashcards f");
                                        sql.append("WHERE fc_assunto = ?, fc_dificuldade = ?");
 PreparedStatement ps = conexao.prepareStatement(sql.toString());
 ps.setString(1, f.getFc_assunto());
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

public ArrayList<Flashcard> buscaRespostas(Flashcard fp) {
 ArrayList<Flashcard> respostas= new ArrayList<>();
 try {
Connection conexao = ConnectionFactory.conectar();
 StringBuilder sql = new StringBuilder();
		sql.append("SELECT fc_resposta");
		sql.append("FROM flashcards f");
                                        sql.append("WHERE fc_pergunta = ?");
 PreparedStatement ps = conexao.prepareStatement(sql.toString());
 ps.setString(1, fp.getFc_pergunta());
 ResultSet rs = ps.executeQuery();
 
 while(rs.next()) {
 Flashcard fc = new Flashcard();
 fc.setFc_resposta(rs.getString("fc_resposta"));
 
 respostas.add(fc);
 }
 } catch (SQLException ex) {
 Logger.getLogger(TemaDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 return respostas;
 }


    public ArrayList<Flashcard> buscarPerguntaPorTema(Flashcard f){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT t.tm_nome, f.fc_dificuldade, f.fc_pergunta ");
      sql.append("FROM flashcards f ");
      sql.append("WHERE t.tm_nome LIKE ?, f.fc_dificuldade=? ");
      sql.append("INNER JOIN temas t ON t.tm_id = f.temas_tm_id");
      //INEER JOIN= junção de tabela e ON igualar as chaves estrangeiras
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ps.setString(1, "%" +  f.getTema().getTm_nome() + "%");
         ps.setString(2, f.getFc_dificuldade());
         
         ResultSet resultado = ps.executeQuery();
          //ResultSet variavel que guarda o resultado da consulta
         ArrayList<Flashcard> cartoes = new ArrayList<Flashcard>();
         
         while(resultado.next()){
           
           Tema t = new Tema();
           t.setTm_nome(resultado.getString("t.tm_nome"));
                                        //percorre o valor do campo da tabela
           Flashcard fc = new Flashcard();
           fc.setFc_dificuldade(resultado.getString("f.fc_dificuldade"));
           fc.setFc_pergunta(resultado.getString("f.fc_pergunta"));
           fc.setTema(t);
           
           cartoes.add(fc);
           
         }
       
         System.out.println("Busca realizada com sucesso!");
         return cartoes;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
     public ArrayList<Flashcard> buscarRespostaPorTema(Flashcard f){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT f.fc_resposta ");
      sql.append("FROM flashcards f ");
      sql.append("WHERE t.tm_nome LIKE ?, f.fc_dificuldade = ?, f.fc_pergunta = ? ");
      sql.append("INNER JOIN temas t ON t.tm_id = f.temas_tm_id");
      //INEER JOIN= junção de tabela e ON igualar as chaves estrangeiras
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ps.setString(1, "%" +  f.getTema().getTm_nome() + "%");
         ps.setString(2, f.getFc_dificuldade());
         ps.setString(3, f.getFc_pergunta());
         
         ResultSet resultado = ps.executeQuery();
          //ResultSet variavel que guarda o resultado da consulta
         ArrayList<Flashcard> cartoes = new ArrayList<Flashcard>();
         
         while(resultado.next()){
           Flashcard fc = new Flashcard();
           fc.setFc_resposta(resultado.getString("f.fc_resposta"));
           cartoes.add(fc);
           
         }
       
         System.out.println("Busca realizada com sucesso!");
         return cartoes;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
     
    public ArrayList<Flashcard> buscarPergunta(){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT f.fc_pergunta, t.tm_nome ");
      sql.append("FROM flashcards f ");
      sql.append("INNER JOIN temas t ON t.tm_id = f.temas_tm_id");
      //INEER JOIN= junção de tabela e ON igualar as chaves estrangeiras
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ResultSet resultado = ps.executeQuery();
          //ResultSet variavel que guarda o resultado da consulta
         ArrayList<Flashcard> cartoes = new ArrayList<Flashcard>();
         
         while(resultado.next()){
           
           Tema t = new Tema();
           t.setTm_nome(resultado.getString("t.tm_nome"));
                                        //percorre o valor do campo da tabela
           Flashcard f = new Flashcard();
           f.setFc_pergunta(resultado.getString("f.fc_pergunta"));
           f.setTema(t);
           
           cartoes.add(f);
           
         }
       
         System.out.println("Busca realizada com sucesso!");
         return cartoes;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
    public ArrayList<Flashcard> buscarResposta(){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT f.fc_resposta, t.tm_nome ");
      sql.append("FROM flashcards f ");
      sql.append("INNER JOIN temas t ON t.tm_id = f.temas_tm_id");
      //INEER JOIN= junção de tabela
      try{
         Connection conexao = ConnectionFactory.conectar();
         
         PreparedStatement ps = conexao.prepareStatement(sql.toString()); 
         
         ResultSet resultado = ps.executeQuery();
          
         ArrayList<Flashcard> cartoes = new ArrayList<Flashcard>();
         
         while(resultado.next()){
           
           Tema t = new Tema();
           t.setTm_nome(resultado.getString("t.tm_nome"));
           
           Flashcard f = new Flashcard();
           f.setFc_resposta(resultado.getString("f.fc_resposta"));
           f.setTema(t);
           
           cartoes.add(f);
           
         }
       
         System.out.println("Busca realizada com sucesso!");
         return cartoes;
         
      }catch (SQLException ex) {
            System.out.println("ERRO ao realizar a busca :" + ex);
            return null;
           
        }
      
    }
    
    
    public static void main(String[] args) {
        Flashcard fc1 = new Flashcard();
        fc1.setFc_pergunta("O que é Flashcard"); 
        fc1.setFc_resposta("Pequenos cartões");

        Flashcard fc2 = new Flashcard();
        fc2.setFc_pergunta("O que é Java");
        fc2.setFc_resposta("Linguagem de programação");

        FlashcardDAO fcdao = new FlashcardDAO();

        fcdao.cadastrar(fc1);
        fcdao.cadastrar(fc2);

    }
*/

}
