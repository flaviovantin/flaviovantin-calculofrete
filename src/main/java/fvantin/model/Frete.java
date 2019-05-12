package fvantin.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import fvantin.model.dto.CustoPorDistancia;

import java.util.List;

@DynamoDBTable(tableName = "Frete")
public class Frete {

    @DynamoDBHashKey(attributeName = "tipoCarga")
    private String tipoCarga;

    @DynamoDBAttribute(attributeName = "fretes")
    private List<CustoPorDistancia> fretes;

    public Frete() {

    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public List<CustoPorDistancia> getFretes() {
        return fretes;
    }

    public void setFretes(List<CustoPorDistancia> fretes) {
        this.fretes = fretes;
    }
}
