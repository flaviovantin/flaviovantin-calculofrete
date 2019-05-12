package fvantin.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalcularFreteRequest {

    private String tipoCarga;
    private String distancia;
    private String numeroEixos;
    private String margemLucro;
    private String totalPedagios;

    public CalcularFreteRequest() {

    }

    public CalcularFreteRequest(String tipoCarga, String distancia, String numeroEixos, String margemLucro, String totalPedagios) {
        this.tipoCarga = tipoCarga;
        this.distancia = distancia;
        this.numeroEixos = numeroEixos;
        this.margemLucro = margemLucro;
        this.totalPedagios = totalPedagios;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getNumeroEixos() {
        return numeroEixos;
    }

    public void setNumeroEixos(String numeroEixos) {
        this.numeroEixos = numeroEixos;
    }

    public String getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(String margemLucro) {
        this.margemLucro = margemLucro;
    }

    public String getTotalPedagios() {
        return totalPedagios;
    }

    public void setTotalPedagios(String totalPedagios) {
        this.totalPedagios = totalPedagios;
    }

}
