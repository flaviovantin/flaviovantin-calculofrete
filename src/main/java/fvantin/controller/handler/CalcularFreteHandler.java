package fvantin.controller.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.util.NumberUtils;
import fvantin.controller.request.CalcularFreteRequest;
import fvantin.controller.response.CalcularFreteResponse;
import fvantin.service.FreteService;
import fvantin.utils.FloatUtils;

import java.util.ArrayList;
import java.util.List;

public class CalcularFreteHandler implements RequestHandler<CalcularFreteRequest, CalcularFreteResponse> {

    private static final String VALORES_INVALIDOS_NAO_INFORMADOS = "Valores com formatos inválidos ou não informados.";
    private static final String ERRO_CALCULAR_FRETE_VERIFIQUE_FORMATOS_QUANTIDADES = "Erro ao calcular o frete. Verifique os formatos e quantidades dos valores informados.";

    private static final String CARGA_GERAL = "carga-geral";
    private static final String CARGA_GRANEL = "carga-granel";
    private static final String CARGA_NEOGRANEL = "carga-neogranel";
    private static final String CARGA_FRIGORIFICADA = "carga-frigorificada";
    private static final String CARGA_PERIGOSA = "carga-perigosa";

    private static final String TIPO_DE_CARGA_INVALIDO = "Tipo de Carga inválido";
    private static final String DISTANCIA_INVALIDA = "Distância inválida (considerar número inteiro de 1 a 3000 Km)";
    private static final String NUMERO_EIXOS_INVALIDO = "Número de Eixos inválido (considerar número inteiro maior ou igual a 2)";
    private static final String MARGEM_DE_LUCRO_INVALIDA = "Margem de Lucro inválida (considerar valor maior ou igual a zero e ponto para decimais)";
    private static final String CUSTO_PEDAGIO_NAO_INFORMADO = "Custo total com pedagio nao informado. Considerando valor R$ 0,00";
    private static final String CUSTO_PEDAGIO_INVALIDO = "Custo com Pedágio inválido (considerar valor maior ou igual a zero e ponto para decimais). Valor não mandatório.";

    private LambdaLogger logger = null;
    private List<String> erros = null;

    @Override
    public CalcularFreteResponse handleRequest(CalcularFreteRequest request, Context context) {

        logger = context.getLogger();
        erros = new ArrayList<>();

        String tipoCarga = validarTipoCarga(request.getTipoCarga());
        Integer distancia = validarDistancia(request.getDistancia());
        Integer numeroEixos = validarNumeroEixos(request.getNumeroEixos());
        Float margemLucro = validarMargemLucro(request.getMargemLucro());
        Float totalPedagios = validarCustoPedagio(request.getTotalPedagios());

        CalcularFreteResponse calcularFreteResponse = new CalcularFreteResponse();
        if (erros.size() == 0) {

            Float totalFrete = FreteService.calcularFrete(tipoCarga, distancia, numeroEixos, margemLucro, totalPedagios);
            if (totalFrete != null) {
                calcularFreteResponse.setValorFreteCalculado(totalFrete);
                calcularFreteResponse.setErros(erros);
                logger.log("Valor do frete calculado: R$ " + totalFrete);
            } else {
                calcularFreteResponse.setValorFreteCalculado(0.00F);
                erros.add(ERRO_CALCULAR_FRETE_VERIFIQUE_FORMATOS_QUANTIDADES);
                calcularFreteResponse.setErros(erros);
                logger.log("Erro ao calcular o frete. Verifique os valores da Tabela de Frete cadastrada no DynamoDB (Frete)");
            }

        } else {
            calcularFreteResponse.setValorFreteCalculado(0.00F);
            calcularFreteResponse.setErros(erros);
            logger.log(VALORES_INVALIDOS_NAO_INFORMADOS);
        }

        return calcularFreteResponse;
    }

    private String validarTipoCarga(String tipoCarga) {
        if (tipoCarga != null &&
                (tipoCarga.equalsIgnoreCase(CARGA_GERAL) ||
                tipoCarga.equalsIgnoreCase(CARGA_GRANEL) ||
                tipoCarga.equalsIgnoreCase(CARGA_NEOGRANEL) ||
                tipoCarga.equalsIgnoreCase(CARGA_FRIGORIFICADA) ||
                tipoCarga.equalsIgnoreCase(CARGA_PERIGOSA))) {
            return tipoCarga;
        }
        erros.add(TIPO_DE_CARGA_INVALIDO);
        return null;
    }

    private Integer validarDistancia(String distancia) {
        if (distancia != null && NumberUtils.tryParseInt(distancia) != null) {
            Integer dist = Integer.parseInt(distancia);
            if (dist > 0 && dist <= 3000) return dist;
        }
        erros.add(DISTANCIA_INVALIDA);
        return null;
    }

    private Integer validarNumeroEixos(String numeroEixos) {
        if (numeroEixos != null && NumberUtils.tryParseInt(numeroEixos) != null) {
            Integer num = Integer.parseInt(numeroEixos);
            if (num >= 2) return num;
        }
        erros.add(NUMERO_EIXOS_INVALIDO);
        return null;
    }

    private Float validarMargemLucro(String margemLucro) {
        if (margemLucro != null && FloatUtils.tryParseFloat(margemLucro) != null) {
            Float margem = Float.parseFloat(margemLucro);
            if (margem >= 0F) return margem;
        }
        erros.add(MARGEM_DE_LUCRO_INVALIDA);
        return null;
    }

    private Float validarCustoPedagio(String totalPedagios) {
        if (totalPedagios == null) {
            logger.log(CUSTO_PEDAGIO_NAO_INFORMADO);
            return 0F;
        }
        if (FloatUtils.tryParseFloat(totalPedagios) != null) {
            Float pedagio = Float.parseFloat(totalPedagios);
            if (pedagio >= 0F) return pedagio;
        }
        erros.add(CUSTO_PEDAGIO_INVALIDO);
        return null;
    }
}
