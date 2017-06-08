/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.City;
import entity.Country;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


/**
 *
 * @author Bruger
 */
public class Facade {
   
    EntityManagerFactory emf;
    EntityManager em;

    public Facade() {
        
    }

      public void addEntityManagerFacotry(EntityManagerFactory emf) {

        this.emf = emf;
        this.em = emf.createEntityManager();


    }

    public List<Country> getAllContries(){
        
       
        List<Country> contries = em.createNamedQuery("Country.findAll", Country.class).getResultList();
        return contries;
    }
     public List<Country> getCountriesGreaterThan(int number) {
        
        List<Country> contries = em.createQuery("SELECT c FROM Country c WHERE c.population >"+ number,Country.class).getResultList();
        return contries;
     }
    public Collection <City>  getAllCityinThisCountry(String code){
        Country c;
        TypedQuery<Country> createQuery = em.createQuery("SELECT c FROM Country c WHERE c.code = :code", Country.class);
        createQuery.setParameter("code", code);
        c=createQuery.getSingleResult();
        Collection <City> cities = c.getCityCollection();
        return cities;
         
     }
    public City addCitytoThisCountry(String code, City nyCity){
        Country c;
        em.getTransaction().begin();
        TypedQuery<Country> createQuery = em.createQuery("SELECT c FROM Country c WHERE c.code = :code", Country.class);
        createQuery.setParameter("code", code);
        c=createQuery.getSingleResult();
        nyCity.setCountryCode(c);
        em.persist(nyCity);
        c.addCity(nyCity);
        em.persist(c);
        em.getTransaction().commit();
        return nyCity;
        
    }
    public Country getCountry(String code) {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Country> result = em.createNamedQuery("Country.findByCode", Country.class);
        Country con = result.setParameter("code", code).getSingleResult();
        return con;

    }
    
}
