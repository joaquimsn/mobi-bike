package br.com.mobibike.autenticacao.expose.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialAcesso;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialSessao;
import br.com.mobibike.autenticacao.domain.seguranca.beans.TokenAcesso;
import br.com.mobibike.autenticacao.domain.seguranca.beans.UsuarioSessao;
import br.com.mobibike.autenticacao.domain.seguranca.service.AutenticacaoService;
import br.com.mobibike.autenticacao.domain.seguranca.service.AutorizacaoService;
import br.com.mobibike.autenticacao.main.exceptions.MessageResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Autenticação", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class AutenticacaoResource {

	@Inject
	private AutenticacaoService autenticacaoService;
	@Inject
	private AutorizacaoService autorizacaoService;

	@POST
	@Path("/{aplicacao}/request-token")
	@ApiOperation(value = "Eftuar autenticação", notes = "Para consumir os demais serviços é necessário gerar um token de acesso a partir do usuário e senha", code = 201)
	@ApiResponses({
			@ApiResponse(code = 400, message = "Dado informado não atende aos padrões especificados", response = MessageResponseError.class),
			@ApiResponse(code = 403, message = "Não autorizado", response = MessageResponseError.class),
			@ApiResponse(code = 500, message = "Erro interno", response = MessageResponseError.class)
	})
	public Response requestToken(@Valid CredencialAcesso credencial,
			@ApiParam(name = "aplicacao", value = "Nome da aplicação que será autenticada") @PathParam("aplicacao") String aplicacao) {
		UsuarioSessao usuarioSessao = autenticacaoService.autenticar(credencial, aplicacao.toUpperCase());
		return Response.status(Status.OK).entity(usuarioSessao).build();
	}

	@PUT
	@Path("/{aplicacao}/check-access")
	@ApiOperation(value = "Validar token", notes = "Verificar se o token é válido, e caso seja informado uma funcionalidade é verificador se a credencial tem acesso a mesma", code = 200)
	@ApiResponses({
			@ApiResponse(code = 401, message = "Não autenticado", response = MessageResponseError.class),
			@ApiResponse(code = 403, message = "Não autorizado", response = MessageResponseError.class),
			@ApiResponse(code = 500, message = "Erro interno", response = MessageResponseError.class)
	})
	public CredencialSessao verificarAcesso(TokenAcesso tokenAcesso,
			@ApiParam(name = "aplicacao", value = "Nome da aplicação que será autenticada") @PathParam("aplicacao") String aplicacao) {
		CredencialSessao credencialSessao = autenticacaoService.recuperarCredencialToken(tokenAcesso.getToken());
		autorizacaoService.autorizar(credencialSessao, aplicacao);
		return credencialSessao;
	}
}
