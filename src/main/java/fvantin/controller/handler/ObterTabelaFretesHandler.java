package fvantin.controller.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import fvantin.controller.response.ApiGatewayProxyResponse;
import fvantin.model.Frete;
import fvantin.service.FreteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObterTabelaFretesHandler implements RequestHandler<Void, ApiGatewayProxyResponse> {

    @Override
    public ApiGatewayProxyResponse handleRequest(Void request, Context context) {

        List<Frete> fretes = FreteService.obterTabelaFretes();

        Gson gson = new Gson();
        ApiGatewayProxyResponse apiGatewayProxyResponse = new ApiGatewayProxyResponse();
        apiGatewayProxyResponse.setBody(gson.toJson(fretes));
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
        headers.put("Access-Control-Allow-Credentials", "true");
        headers.put("Content-Type", "application/json");
        apiGatewayProxyResponse.setHeaders(headers);
        apiGatewayProxyResponse.setStatusCode(200);

        context.getLogger().log("Retornando " + fretes.size() + " tabela(s) de frete...");
        return apiGatewayProxyResponse;
    }
}
