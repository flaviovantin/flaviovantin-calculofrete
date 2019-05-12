package fvantin.controller.response;

import java.util.List;

public class CalcularFreteResponse {

    private Float valorFreteCalculado;
    private List<String> erros;

    public CalcularFreteResponse() {

    }

    public CalcularFreteResponse(Float valorFreteCalculado, List<String> erros) {
        this.valorFreteCalculado = valorFreteCalculado;
        this.erros = erros;
    }

    public Float getValorFreteCalculado() {
        return valorFreteCalculado;
    }

    public void setValorFreteCalculado(Float valorFreteCalculado) {
        this.valorFreteCalculado = valorFreteCalculado;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
