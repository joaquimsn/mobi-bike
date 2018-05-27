package br.com.mobibike.coletor.expose.resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mobibike.coletor.domain.ciclista.entity.Ciclista;
import br.com.mobibike.coletor.domain.ciclista.entity.Track;
import br.com.mobibike.coletor.domain.ciclista.entity.ViagemTrack;
import br.com.mobibike.coletor.domain.ciclista.service.CiclistaService;
import br.com.mobibike.coletor.domain.ciclista.service.ViagemTrackService;
import br.com.mobibike.coletor.main.exceptions.MessageResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiKeyAuthDefinition.ApiKeyLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

@Path("/ciclistas")
@Api(value = "Ciclista", authorizations = { @Authorization(value = "jwt") })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SwaggerDefinition(securityDefinition = @SecurityDefinition(apiKeyAuthDefinitions = {
		@ApiKeyAuthDefinition(in = ApiKeyLocation.HEADER, key = "jwt", name = "authorization", description = "Necesário informar token para autorização") }))
@ApiResponses({ @ApiResponse(code = 401, message = "Não autenticado", response = MessageResponseError.class),
		@ApiResponse(code = 403, message = "Não autorizado", response = MessageResponseError.class),
		@ApiResponse(code = 500, message = "Erro interno", response = MessageResponseError.class) })
public class CiclistaResource {

	@Inject
	private CiclistaService ciclistaService;

	@Inject
	private ViagemTrackService viagemTrackService;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Retorna o ciclista correspondete ao id informado", code = 200)
	public Ciclista buscarPorId(@PathParam("id") Long id) {
		return ciclistaService.buscarPorId(id);
	}

	@POST
	@ApiOperation(value = "Realiza o cadastro da ciclista", code = 201)
	@ApiResponses({
			@ApiResponse(code = 400, message = "O dado informado não atende o padrão da model", response = MessageResponseError.class),
			@ApiResponse(code = 422, message = "Validação de campo especifico", response = MessageResponseError.class)
	})
	public Response cadastrar(@Valid Ciclista ciclista) {
		return Response.status(Status.CREATED).entity(ciclistaService.inserir(ciclista)).build();
	}

	@POST
	@Path("/viagem-track")
	@ApiOperation(value = "Registra percurso da viagem coletado", code = 201, notes = "Eftuar o cadastro de todo o trajeto armazendo offline")
	@ApiResponses({
			@ApiResponse(code = 400, message = "O dado informado não atende o padrão da model", response = MessageResponseError.class),
			@ApiResponse(code = 422, message = "Validação de campo especifico", response = MessageResponseError.class) })
	public Response registraTrajeto(@Valid ViagemTrack viagem) {
		return Response.status(Status.CREATED).entity(viagemTrackService.inserir(viagem)).build();
	}

	@PUT
	@Path("/{idViagem}/tracks")
	@ApiOperation(value = "Incremetar percurso", code = 201, notes = "Eftuar o cadastro de todo o trajeto armazendo offline")
	@ApiResponses({
			@ApiResponse(code = 400, message = "O dado informado não atende o padrão da model", response = MessageResponseError.class)
	})
	public Response adicionarTrack(@PathParam("idViagem") Long idViagem, List<Track> tracks) {
		ViagemTrack viagemTrack = viagemTrackService.buscarPorId(idViagem);
		viagemTrack.addTrack(tracks);
		return Response.status(Status.CREATED).entity(viagemTrackService.atualizar(viagemTrack)).build();
	}

}
