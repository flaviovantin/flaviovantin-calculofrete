package fvantin.service;

import fvantin.dao.FreteDAO;
import fvantin.model.Frete;
import fvantin.model.dto.CustoPorDistancia;

import java.util.List;
import java.util.Optional;

public class FreteService {

    private static FreteDAO freteDAO = new FreteDAO();

    public static List<Frete> obterTabelaFretes() {
        return freteDAO.getAll();
    }

    /**
     * Exemplo:
     * {
     *    "tipoCarga": "carga-frigorificada",
     *    "distancia": 430,
     *    "numeroEixos": 3,
     *    "margemLucro": 30.5,
     *    "totalPedagios": 400.45
     * }
     */
    public static Float calcularFrete(String tipoCarga, Integer distancia, Integer numeroEixos, Float margemLucro, Float totalPedagios) {

        Frete frete = freteDAO.getByTipoCarga(tipoCarga);
        List<CustoPorDistancia> custoPorDistancias = frete.getFretes();
        Optional<CustoPorDistancia> custoPorDistanciaOpt = custoPorDistancias
                .stream()
                .filter(c -> (distancia >= c.getDe() && distancia <= c.getAte()))
                .findFirst();

        if (!custoPorDistanciaOpt.isPresent()) return null;

        // Calculo do Frete
        Float valorParcialFrete = numeroEixos * custoPorDistanciaOpt.get().getCustoPorKm() * distancia;
        Float valorMargemLucro = (margemLucro * valorParcialFrete) / 100;
        return valorParcialFrete + valorMargemLucro + totalPedagios;
    }
}
