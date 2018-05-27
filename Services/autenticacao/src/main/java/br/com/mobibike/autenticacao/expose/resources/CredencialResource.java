package br.com.mobibike.autenticacao.expose.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;
import br.com.mobibike.autenticacao.domain.seguranca.service.CredencialService;
import br.com.mobibike.autenticacao.main.exceptions.MessageResponseError;
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

@Path("/credenciais")
@Api(value = "Credencial", authorizations = { @Authorization(value = "jwt") })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
		@ApiKeyAuthDefinition(in = ApiKeyLocation.HEADER, key = "jwt", name = "authorization", description = "Necesário informar token para autorização seguindo o padrão jwt {token}")
}))
@ApiResponses({
		@ApiResponse(code = 401, message = "Não autenticado", response = MessageResponseError.class),
		@ApiResponse(code = 403, message = "Não autorizado", response = MessageResponseError.class),
		@ApiResponse(code = 500, message = "Erro interno", response = MessageResponseError.class)
})
public class CredencialResource {

	@Inject
	private CredencialService credencialService;

	@GET
	@ApiOperation(value = "Retorna todas as credenciaiss cadastradas")
	public List<Credencial> buscarCredenciais() {
		return credencialService.buscarTodos();
	}

	@PUT
	@Path("/credenciais/{idCredencial}")
	@ApiOperation(value = "Atualiza a credencial com base no id informado", code = 201)
	@ApiResponses({
			@ApiResponse(code = 400, message = "O dado informado não atende o padrão da model", response = MessageResponseError.class),
			@ApiResponse(code = 422, message = "Validação de campo especifico", response = MessageResponseError.class)
	})
	public Response requestToken(@Valid Credencial credencial,
			@ApiParam(name = "idCredencial", value = "Id da credencial que será alterada") @PathParam("idCredencial") Long idCredencial) {

		return Response.status(Status.OK).entity(credencialService.inserir(credencial)).build();
	}
}
