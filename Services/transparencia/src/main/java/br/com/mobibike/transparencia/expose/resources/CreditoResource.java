package br.com.mobibike.transparencia.expose.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mobibike.transparencia.domain.relatorio.RelatorioService;
import br.com.mobibike.transparencia.main.exceptions.MessageResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

@Path("/relatorio/credito")
@Api(value = "Relatório Creditos", authorizations = { @Authorization(value = "jwt") })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
		@ApiKeyAuthDefinition(in = ApiKeyLocation.HEADER, key = "jwt", name = "authorization", description = "Necesário informar token para autorização")
}))
@ApiResponses({
		@ApiResponse(code = 401, message = "Não autenticado", response = MessageResponseError.class),
		@ApiResponse(code = 403, message = "Não autorizado", response = MessageResponseError.class),
		@ApiResponse(code = 500, message = "Erro interno", response = MessageResponseError.class)
})
public class CreditoResource {

	@Inject
	private RelatorioService relatorioService;

	@GET
	@Path("/retorno-ciclista")
	@ApiOperation(value = "Total de creditos pago por período")
	public Response relatorioCreditoPago(@ApiParam(format = "2018-05-01") @QueryParam("dataInicio") String dataIncio,
			@ApiParam(format = "2018-05-030") @QueryParam("dataFim") String dataFim) {
		return Response.status(Status.OK).entity("{}").build();
	}

	@GET
	@Path("/regiao")
	@ApiOperation(value = "Resumo das viagem feitas por bicicletas até o momento", notes = "Quantidade de km percorrido por bike, viagem economizadas, "
			+ "quantidade usuário ativo")
	public Response relatorio(
			@ApiParam(example = "norte", allowableValues = "norte, sul, leste, oeste, centro") @QueryParam("regiao") String regiao) {
		return Response.status(Status.OK).entity(relatorioService.resumoRegiao(regiao)).build();
	}
}
