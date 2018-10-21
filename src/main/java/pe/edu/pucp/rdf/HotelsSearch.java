package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import pe.edu.pucp.rdf.selectors.HotelPriceSelector;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

public class HotelsSearch {
    private static final String EXAMPLE_FILE = "peru-hotels.rdf";
    private static final String NS = "http://pucp.edu.pe/hotels/";

    private FileManager fileManager;
    private Model model;

    public HotelsSearch() {
        fileManager = FileManager.get();
        model = fileManager.loadModel(EXAMPLE_FILE);
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
}