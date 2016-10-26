
package com.hg.dwhelloworld.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hg.dwhelloworld.entity.DrHelloWorld;
import com.yammer.metrics.annotation.Timed;

import net.vz.mongodb.jackson.JacksonDBCollection;

@Path( "/drhw" )
@Produces( value = MediaType.APPLICATION_JSON )
@Consumes( value = MediaType.APPLICATION_JSON )
public class DrHelloWorldResource
{
    private JacksonDBCollection<DrHelloWorld, String> collection;

    public DrHelloWorldResource( JacksonDBCollection<DrHelloWorld, String> drhwCollection )
    {
        this.collection = drhwCollection;
    }

    @POST
    @Timed
    public Response publishNewBlog( @Valid DrHelloWorld drhw )
    {
        collection.insert( drhw );
        return Response.noContent().build();
    }
}
