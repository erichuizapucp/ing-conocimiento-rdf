package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;

import java.util.Iterator;

public class HotelsSchemaValidation {
    private final String RDF_FILE = "peru-hotels.rdf";
    private final String SCHEMA_FILE = "schema.rdf";
    private final String HOTELS_NS = "http://pucp.edu.pe/hotels/";
    private static final String RDFS_NS = "http://www.w3.org/2000/01/rdf-schema#";
    private static final String SCHEMA_NS = "http://schema.org/";

    private FileManager fileManager;
    private Model model;
    private Model schemaModel;

    public HotelsSchemaValidation() {
        fileManager = FileManager.get();
        model = fileManager.loadModel(RDF_FILE);
        schemaModel = fileManager.loadModel(SCHEMA_FILE);
    }

    // Realiza una validación hacia el Schema.org
    public void hotelValidation1() {
        performValidation(schemaModel, model);
    }


    public void hotelValidation2() {
        Property p = model.createProperty(HOTELS_NS + "employes");
        Resource e1 = model.createResource(HOTELS_NS + "employee1");
        Resource e2 = model.createResource(HOTELS_NS + "employee2");

        Resource hotel = model.getResource("http://pucp.edu.pe/hotels/novotel-san-isidro");

        model.add(hotel, p, e1);
        model.add(hotel, p, e2);

        performValidation(schemaModel, model);
    }

    private void performValidation(Model schemaModel, Model model) {
        InfModel infmodel = ModelFactory.createRDFSModel(schemaModel, model);
        ValidityReport validity = infmodel.validate();

        if (validity.isValid()) {
            System.out.println("The schema is valid");
        }
        else {
            System.out.println("Hay conflictos");
            for (Iterator i = validity.getReports(); i.hasNext();) {
                System.out.println("  -  " + i.next());
            }
        }
    }
}
