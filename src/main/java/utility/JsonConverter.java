
package utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.City;
import entity.Country;
import java.util.Collection;
import java.util.List;

public class JsonConverter {
    GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
    Gson gson = gsonBuilder.create();
    
 public Country getCountryFromJson(String js) {
        
        return gson.fromJson(js, Country.class);

    }  
  public String getJSONFromCountry(Country c) {

        return gson.toJson(c);
        
    }
  public City getCityFromJson (String js){
      return gson.fromJson(js, City.class);
  }
  public String getJSONFromCity (City c){
      return gson.toJson(c);
  }
  public String getJSONCountries(List<Country> countries){
      return gson.toJson(countries);
  }
  public String getJsonCities (Collection<City> cities){
      return gson.toJson(cities);
  }
}
