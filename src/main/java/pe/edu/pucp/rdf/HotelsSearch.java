package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import pe.edu.pucp.rdf.selectors.HotelPriceSelector;
import pe.edu.pucp.rdf.selectors.HotelRatingSelector;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

public class HotelsSearch {
    private final String RDF_FILE = "peru-hotels.rdf";

    private FileManager fileManager;
    private Model model;

    public HotelsSearch() {
        fileManager = FileManager.get();
        model = fileManager.loadModel(RDF_FILE);
    }

    public void hotelsByName(String hotelName) {
        ResIterator it = model.listSubjectsWithProperty(SchemaDOTOrg.NAME, hotelName);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }

    public void hotelsByCity(String city) {
        ResIterator it = model.listSubjectsWithProperty(SchemaDOTOrg.CITY, city);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }

    public void hotelsByPrice(double price) {
        SimpleSelector selector = new HotelPriceSelector(price);
        StmtIterator it = model.listStatements(selector);

        while (it.hasNext()) {
            System.out.println(it.nextStatement());
        }
    }

    public void hotelsByDistrict(String hotelDistrict) {
        ResIterator it = model.listSubjectsWithProperty(SchemaDOTOrg.DISTRICT, hotelDistrict);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }

    public void hotelsByPostalCode(String hotelPostalCode) {
        ResIterator it = model.listSubjectsWithProperty(SchemaDOTOrg.POSTAL_CODE, hotelPostalCode);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }

    public void hotelsByMinumRatingValue(int ratingValue) {
        SimpleSelector selector = new HotelRatingSelector(ratingValue);
        StmtIterator it = model.listStatements(selector);

        while (it.hasNext()) {
            System.out.println(it.nextStatement());
        }
    }
}