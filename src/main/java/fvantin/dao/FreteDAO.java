package fvantin.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import fvantin.model.Frete;
import fvantin.model.dto.CustoPorDistancia;

import java.util.List;

public class FreteDAO {

    static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();

    public void save(Frete frete) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        mapper.save(frete);
    }

    public List<Frete> getAll() {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        List<Frete> fretes = mapper.scan(Frete.class, new DynamoDBScanExpression());
        return fretes;
    }

    public Frete getByTipoCarga(String tipoCarga) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        return mapper.load(Frete.class, tipoCarga);
    }

    public void delete(Frete frete) {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        mapper.delete(frete);
    }
}
