package io.swagger.sample.petstore.parameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Resource with some Parameters examples
 */
@Path("/parameters")
public class ParametersResource {
    @POST
    @Operation(
            operationId = "subscribe",
            description = "subscribes a client to updates relevant to the requestor's account, as " +
                    "identified by the input token.  The supplied url will be used as the delivery address for response payloads",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "subscriptionId", required = true,
                            schema = @Schema(implementation = RepeatableParametersResource.SubscriptionResponse.class), style = ParameterStyle.SIMPLE),
                    @Parameter(in = ParameterIn.QUERY, name = "formId", required = true,
                            example = "Example"),
                    @Parameter(in = ParameterIn.QUERY, name = "explodeFalse", required = true, explode = Explode.FALSE,
                            schema = @Schema(implementation = SubscriptionResponse.class)),
                    @Parameter(in = ParameterIn.QUERY, name = "explodeTrue", required = true, explode = Explode.TRUE,
                            schema = @Schema(implementation = SubscriptionResponse.class)),
                    @Parameter(in = ParameterIn.QUERY, name = "explodeAvoiding", required = true, explode = Explode.TRUE,
                            schema = @Schema(
                                    type = "int",
                                    format = "id",
                                    description = "the generated id",
                                    readOnly = true
                            )),
                    @Parameter(in = ParameterIn.QUERY, name = "arrayParameter", required = true, explode = Explode.TRUE,
                            array = @ArraySchema(maxItems = 10, minItems = 1,
                                    schema = @Schema(implementation = SubscriptionResponse.class),
                                    uniqueItems = true
                            )
                            ,
                            schema = @Schema(
                                    type = "int",
                                    format = "id",
                                    description = "the generated id",
                                    readOnly = true),
                            content = @Content(schema = @Schema(type = "number",
                                    description = "the generated id",
                                    readOnly = true))
                    ),
                    @Parameter(in = ParameterIn.QUERY, name = "arrayParameterImplementation", required = true, explode = Explode.TRUE,
                            array = @ArraySchema(maxItems = 10, minItems = 1,
                                    schema = @Schema(implementation = SubscriptionResponse.class),
                                    uniqueItems = true
                            )
                    ),
                    @Parameter(in = ParameterIn.QUERY, name = "arrayParameterImplementation2", required = true, explode = Explode.TRUE,
                            schema = @Schema(implementation = SubscriptionResponse.class))

            },
            responses = {
                    @ApiResponse(
                            description = "test description", content = @Content(
                            mediaType = "*/*",
                            schema =
                            @Schema(
                                    implementation = SubscriptionResponse.class)
                    ))
            })
    @Consumes({"application/json", "application/xml"})
    public Response subscribe(@Parameter(description = "idParam")
                              @QueryParam("id") final String id) {
        return Response.ok().entity(new SubscriptionResponse()).build();
    }

    static class SubscriptionResponse {
        public String subscriptionId;
    }
}
