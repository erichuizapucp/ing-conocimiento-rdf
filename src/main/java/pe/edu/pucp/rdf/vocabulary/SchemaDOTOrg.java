package pe.edu.pucp.rdf.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class SchemaDOTOrg {
    public static final String uri = "http://schema.org/";
    private static final Model m = ModelFactory.createDefaultModel();

    public static final Property NAME = m.createProperty(uri, "name");
    public static final Property CITY = m.createProperty(uri, "city");
    public static final Property PRICE_RANGE = m.createProperty(uri, "priceRange");
}
