package br.com.ies.controle;

import br.com.ies.dao.impl.AlunoDAOimp;
import br.com.ies.dao.impl.FichaPessoaDAOImp;
import br.com.ies.dao.impl.ProfessorDAOimp;
import br.com.ies.enuns.TipoModeloFicha;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class HomeControle implements Serializable{

    private PieChartModel pieAlunos;
    private PieChartModel pieAlunosInstituicao;
    private PieChartModel pieProfessores;
    
    
        
    

    public HomeControle() {
        
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(false);
//        LoginControle loginControle = (LoginControle) session.getAttribute("LoginControle");
//        
//        
//        if(loginControle != null){
//        
//        }
        
        createPieModels();
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
        createPieModel3();
    }

   
    
    private void createPieModel1() {
        try {
            int totalAlunos = new AlunoDAOimp().listaAlunos().size();
            int totalResponderam = new FichaPessoaDAOImp().getCountFichasRespondidas(TipoModeloFicha.ALUNO_PROFESSOR);
            if (totalAlunos > 0) {
                pieAlunos = new PieChartModel();
                pieAlunos.set("Responderam", totalResponderam);
                pieAlunos.set("Não Responderam", totalAlunos - totalResponderam );
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createPieModel2() {
        try {
            int totalProfessores = new ProfessorDAOimp().listaProfessor().size();
            int totalResponderam = new FichaPessoaDAOImp().getCountFichasRespondidas(TipoModeloFicha.PROFESSOR_INSTITUICAO);
            if (totalProfessores > 0) {
                pieProfessores = new PieChartModel();
                pieProfessores.set("Responderam", totalResponderam);
                pieProfessores.set("Não Responderam", totalProfessores - totalResponderam);
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     private void createPieModel3() {
        try {
            int totalAlunos = new AlunoDAOimp().listaAlunos().size();
            int totalResponderam = new FichaPessoaDAOImp().getCountFichasRespondidas(TipoModeloFicha.ALUNO_INSTITUICAO);
            if (totalAlunos > 0) {
                pieAlunosInstituicao = new PieChartModel();
                pieAlunosInstituicao.set("Responderam", totalResponderam);
                pieAlunosInstituicao.set("Não Responderam", totalAlunos - totalResponderam);
            }
        } catch (Exception ex) {
            Logger.getLogger(HomeControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public PieChartModel getPieAlunos() {
        return pieAlunos;
    }

    public void setPieAlunos(PieChartModel pieAlunos) {
        this.pieAlunos = pieAlunos;
    }

    public PieChartModel getPieProfessores() {
        return pieProfessores;
    }

    public void setPieProfessores(PieChartModel pieProfessores) {
        this.pieProfessores = pieProfessores;
    }

    public PieChartModel getPieAlunosInstituicao() {
        return pieAlunosInstituicao;
    }

    public void setPieAlunosInstituicao(PieChartModel pieAlunosInstituicao) {
        this.pieAlunosInstituicao = pieAlunosInstituicao;
    }
    
    
}
