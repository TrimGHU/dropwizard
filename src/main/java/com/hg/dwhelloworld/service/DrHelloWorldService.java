
package com.hg.dwhelloworld.service;

import com.hg.dwhelloworld.configuration.DrHelloWorldConfiguration;
import com.hg.dwhelloworld.entity.DrHelloWorld;
import com.hg.dwhelloworld.managed.MongoHealthCheck;
import com.hg.dwhelloworld.managed.MongoManaged;
import com.hg.dwhelloworld.resource.DrHelloWorldResource;
import com.hg.dwhelloworld.resource.IndexResource;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import net.vz.mongodb.jackson.JacksonDBCollection;

public class DrHelloWorldService extends Service<DrHelloWorldConfiguration>
{

    public static void main( String[] args ) throws Exception
    {
        new DrHelloWorldService().run( new String[] {"server"} );
    }

    @Override
    public void initialize( Bootstrap<DrHelloWorldConfiguration> bootstrap )
    {
        bootstrap.setName( "dropwizard hello world" );
    }

    @Override
    public void run( DrHelloWorldConfiguration config, Environment env ) throws Exception
    {
        Mongo mongo = new Mongo( config.mongohost, config.mongoport );
        MongoManaged mongoManaged = new MongoManaged( mongo );
        env.manage( mongoManaged );

        env.addHealthCheck( new MongoHealthCheck( mongo ) );

        DB db = mongo.getDB( config.mongodb );
        JacksonDBCollection<DrHelloWorld, String> jsdbCollection = JacksonDBCollection.wrap( db.getCollection( "drhw" ),
                DrHelloWorld.class, String.class );

        env.addResource( new DrHelloWorldResource( jsdbCollection ) );

        env.addResource( new IndexResource( jsdbCollection ) );
    }
}
