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
    public static final Property LODGING_BUSINESS = m.createProperty(uri, "LodgingBusiness");

    public static final Property DISTRICT = m.createProperty(uri, "addressLocality");
    public static final Property POSTAL_CODE = m.createProperty(uri, "postalCode");
    public static final Property RATING_VALUE = m.createProperty(uri, "ratingValue");

    public static final Property OFFER = m.createProperty(uri, "Offer");
    public static final Property HOTEL_ROOM = m.createProperty(uri, "HotelRoom");

    public static final Property PRICE = m.createProperty(uri, "price");
    public static final Property PRICE_CURRENCY = m.createProperty(uri, "priceCurrency");
    public static final Property VALID_FROM = m.createProperty(uri, "validFrom");
    public static final Property VALID_THROUGH = m.createProperty(uri, "validThrough");
    public static final Property OFFERS = m.createProperty(uri, "offers");
    public static final Property MAKES_OFFERS = m.createProperty(uri, "makesOffers");

    public static final Property PERSON = m.createProperty(uri, "Person");
}
