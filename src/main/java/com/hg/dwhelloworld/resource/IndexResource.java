
package com.hg.dwhelloworld.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hg.dwhelloworld.entity.DrHelloWorld;
import com.yammer.metrics.annotation.Timed;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

@Path( "/" )
public class IndexResource
{
    private JacksonDBCollection<DrHelloWorld, String> collection;

    public IndexResource( JacksonDBCollection<DrHelloWorld, String> drhwCollection )
    {
        this.collection = drhwCollection;
    }

    @GET
    @Produces( value = MediaType.APPLICATION_JSON )
    @Timed
    public List<DrHelloWorld> index()
    {
        DBCursor<DrHelloWorld> dbCursor = collection.find();
        List<DrHelloWorld> drhws = new ArrayList<>();
        while (dbCursor.hasNext()) {
            DrHelloWorld drhw = dbCursor.next();
            drhws.add(drhw);
        }
        return drhws;
    }

}
