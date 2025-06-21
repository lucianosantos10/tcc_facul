package br.com.flashcard.bean;

import br.com.flashcard.dao.TemaDAO;
import br.com.flashcard.domain.Tema;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "MBTema")
@ViewScoped
public class TemaBean {

    private Tema tema;
    
    @PostConstruct
    public void init() {

        tema = new Tema();  
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public void cadastrar() {
        
        TemaDAO tdao = new TemaDAO();
        if (tdao.cadastrar(tema)) {
            //this.tema.setTm_nome(" ");
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage( "Cadastro realizado com sucesso!"));
            
              //tema.getTm_nome().contains(" ");
        } else {
            FacesContext.getCurrentInstance().addMessage
            (null, new FacesMessage("Erro ao realizar o cadastro!"));

        }
    }
    
 

}
