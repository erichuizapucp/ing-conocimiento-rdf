package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HotelsRules {
    private final String RDF_FILE = "peru-hotels.rdf";
    private final String HOTELS_NS = "http://pucp.edu.pe/hotels/";
    private static final String RDFS_NS = "http://www.w3.org/2000/01/rdf-schema#";
    private static final String SCHEMA_NS = "http://schema.org/";

    private FileManager fileManager;
    private Model model;

    public HotelsRules() {
        fileManager = FileManager.get();
        model = fileManager.loadModel(RDF_FILE);
    }

    // Muestra los hoteles 5 estrellas con precios menores a 200 USD para la temporada navideña
    public void hotDealsForChristmas() {
        Resource offer = model.createResource(SchemaDOTOrg.OFFER);
        Resource room = model.createResource(SchemaDOTOrg.HOTEL_ROOM);

        Resource offer1 = populateOffer(model.createResource(HOTELS_NS + "offer1"), 180, "USD",
                LocalDateTime.of(2018, 12,1, 0, 0),
                LocalDateTime.of(2018, 12, 31, 0, 0));
        Resource offer2 = populateOffer(model.createResource(HOTELS_NS + "offer2"), 300, "USD",
                LocalDateTime.of(2018, 12,1, 0, 0),
                LocalDateTime.of(2018, 12, 31, 0, 0));
        Resource offer3 = populateOffer(model.createResource(HOTELS_NS + "offer3"), 300, "USD",
                LocalDateTime.of(2018, 10,1, 0, 0),
                LocalDateTime.of(2018, 12, 31, 0, 0));

        model.add(offer1, RDFS.subClassOf, offer);
        model.add(offer2, RDFS.subClassOf, offer);
        model.add(offer2, RDFS.subClassOf, offer);
        model.add(offer3, RDFS.subClassOf, offer);

        Resource room1 = model.createResource(HOTELS_NS + "room1");
        Resource room2 = model.createResource(HOTELS_NS + "room2");
        Resource room3 = model.createResource(HOTELS_NS + "room3");

        room1.addProperty(SchemaDOTOrg.OFFERS, offer1);
        room2.addProperty(SchemaDOTOrg.OFFERS, offer2);
        room3.addProperty(SchemaDOTOrg.OFFERS, offer3);

        model.add(room1, RDFS.subClassOf, room);
        model.add(room2, RDFS.subClassOf, room);
        model.add(room3, RDFS.subClassOf, room);

        Resource christmasHotDealHotel = model.createResource( HOTELS_NS + "christmasHotDealHotel");
        Resource jwMarriot = model.getResource(HOTELS_NS + "jw-marriot");
        Resource hyattCentric = model.getResource(HOTELS_NS + "hyatt-centric-san-isidro");

        jwMarriot.addProperty(SchemaDOTOrg.MAKES_OFFERS, offer1);
        hyattCentric.addProperty(SchemaDOTOrg.MAKES_OFFERS, offer1);
        hyattCentric.addProperty(SchemaDOTOrg.MAKES_OFFERS, offer2);
        hyattCentric.addProperty(SchemaDOTOrg.MAKES_OFFERS, offer3);

        List<Rule> rules = Rule.rulesFromURL("rules/hotel-rules.rules");
        Reasoner reasoner = new GenericRuleReasoner(rules);
        InfModel infModel = ModelFactory.createInfModel(reasoner, model);

        System.out.println("Lista de \"Hotels 5 estrellas con ofertas para Navidad\"");
        ResIterator hotels = infModel.listSubjectsWithProperty(RDFS.subClassOf, christmasHotDealHotel);
        while (hotels.hasNext()) {
            Resource resource = hotels.nextResource();
            System.out.println(resource);
        }
    }

    private Resource populateOffer(Resource offer, double price, String currency, LocalDateTime validFrom, LocalDateTime validThrough) {
        offer.addLiteral(SchemaDOTOrg.PRICE, price);
        offer.addLiteral(SchemaDOTOrg.PRICE_CURRENCY, currency);

        String pattern = "yyyy-MM-dd'T'HH:mm'Z'";
        offer.addLiteral(SchemaDOTOrg.VALID_FROM, DateTimeFormatter.ofPattern(pattern).withZone(ZoneOffset.UTC).format(validFrom));
        offer.addLiteral(SchemaDOTOrg.VALID_THROUGH, DateTimeFormatter.ofPattern(pattern).withZone(ZoneOffset.UTC).format(validThrough));

        return offer;
    }
}
