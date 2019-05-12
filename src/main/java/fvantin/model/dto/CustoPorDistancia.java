package fvantin.model.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class CustoPorDistancia {

    private Integer de;
    private Integer ate;
    private Float custoPorKm;

    public CustoPorDistancia() {

    }

    public CustoPorDistancia(Integer de, Integer ate, Float custoPorKm) {
        this.de = de;
        this.ate = ate;
        this.custoPorKm = custoPorKm;
    }

    public Integer getDe() {
        return de;
    }

    public void setDe(Integer de) {
        this.de = de;
    }

    public Integer getAte() {
        return ate;
    }

    public void setAte(Integer ate) {
        this.ate = ate;
    }

    public Float getCustoPorKm() {
        return custoPorKm;
    }

    public void setCustoPorKm(Float custoPorKm) {
        this.custoPorKm = custoPorKm;
    }
}
