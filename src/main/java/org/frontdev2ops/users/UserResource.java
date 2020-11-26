// tag::adocResource[]
package org.frontdev2ops.users;

// end::adocResource[]

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import java.net.URI;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@Path("/api/users")
@Produces(APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    @Operation(summary = "Creates a valid user")
    @APIResponse(responseCode = "200", description = "The URI of the created user", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    // tag::adocMetrics[]
    @Counted(name = "countCreateUser", description = "Counts how many times the createUser method has been invoked")
    @Timed(name = "timeCreateUser", description = "Times how long it takes to invoke the createUser method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @POST
    public Response createUser(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = User.class)))  @Valid User user, @Context UriInfo uriInfo) {
        user = service.persistUser(user);
        return Response.ok(user).build();
    }

    @Operation(summary = "Login a user")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = User.class, required = true)))
    // tag::adocMetrics[]
    @Counted(name = "countLoginUser", description = "Counts how many times the loginUser method has been invoked")
    @Timed(name = "timeLoginUser", description = "Times how long it takes to invoke the loginUser method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @POST
    @Path("/login")
    public Response loginUser(@RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = User.class)))  @Valid User user) {
        User foundUser = service.findUser(user);
        if (foundUser == null){
            return Response.status(Status.FORBIDDEN).build();
        }
        return Response.ok(foundUser).build();
    }

    @Operation(summary = "Health check")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON))
    // tag::adocMetrics[]
    @Counted(name = "countHealthCheck", description = "Counts how many times the healthCheck method has been invoked")
    @Timed(name = "timeHealthCheck", description = "Times how long it takes to invoke the healthCheck method", unit = MetricUnits.MILLISECONDS)
    // end::adocMetrics[]
    @GET
    @Path("/health")
    public Response healthCheck() {
        return Response.ok().build();
    }

}
// end::adocResource[]
