/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.City;
import entity.Country;
import facade.Facade;
import java.util.Collection;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utility.JsonConverter;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("country")
public class CountryService {
private Facade facade  = new Facade();
private JsonConverter converter = new JsonConverter();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CountryService
     */
    public CountryService() {
        
        facade.addEntityManagerFacotry(Persistence.createEntityManagerFactory("pu", null));
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
      List<Country>countries=facade.getAllContries();
      return converter.getJSONCountries(countries);
    }
    
     
    @GET
    @Path("/CountriesBiggerThan/{pop}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonPop(@PathParam("pop") int population) {
      List<Country>countries=facade.getCountriesGreaterThan(population);
      return converter.getJSONCountries(countries);
    }
    @GET
    @Path("/cityOfThisContrie/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCities (@PathParam("code") String code){
        Collection<City> cities = facade.getAllCityinThisCountry(code);
        return converter.getJsonCities(cities);
    }
    @POST
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addCity(@PathParam("code") String code, String json) {
    City city = converter.getCityFromJson(json);
    
    City addCity = facade.addCitytoThisCountry(code, city);
        return converter.getJSONFromCity(addCity);
}
}
