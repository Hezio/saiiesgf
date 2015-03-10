package br.com.ies.dao;

import java.util.ArrayList;
import java.util.List;

public class TesteDTO {

    List<AlvoDTO> alvoDTOs = new ArrayList<TesteDTO.AlvoDTO>();

    public void teste() {

        AlvoDTO alvoDTO = new AlvoDTO();
        alvoDTO.setPergunta("pergunta");
        alvoDTO.setResposta("resposta");
        alvoDTOs.add(alvoDTO);




    }

    class AlvoDTO {

        String pergunta;
        String resposta;

        public String getPergunta() {
            return pergunta;
        }

        public void setPergunta(String pergunta) {
            this.pergunta = pergunta;
        }

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
        }
    }
}
