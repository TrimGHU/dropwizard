
package com.hg.dwhelloworld.configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class DrHelloWorldConfiguration extends Configuration
{
    @JsonProperty
    @NotEmpty
    public String mongohost = "localhost";

    @JsonProperty
    @Min( 1 )
    @Max( 65535 )
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "mongodb";
}
