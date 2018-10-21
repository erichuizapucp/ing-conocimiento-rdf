package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

public class HotelsInference {
    private final String SCHEMA_FILE = "schema.rdf";
    private final String RDF_FILE = "peru-hotels-inference.rdf";

    private final String LB_NS = "http://pucp.edu.pe/LodgingBusiness/";
    private final String HOTELS_NS = "http://pucp.edu.pe/hotels/";

    private FileManager fileManager;
    private Model schemaModel;
    private Model model;

    public HotelsInference() {
        fileManager = FileManager.get();
        schemaModel = fileManager.loadModel(SCHEMA_FILE);
        model = fileManager.loadModel(RDF_FILE);
    }

    public void addSuperClassesAtRuntime() {
        Resource marriot = model.createResource(LB_NS + "marriot");
        Resource marriotObject = model.createResource();
        marriotObject.addProperty(SchemaDOTOrg.NAME, "Marriot");

        model.add(marriot, SchemaDOTOrg.LODGING_BUSINESS, marriotObject);

        Resource jwMarriot = model.getResource(HOTELS_NS + "jw-marriot");
        Resource marriotCusco = model.getResource(HOTELS_NS + "jw-marriot-convento-cusco");

        jwMarriot.addProperty(RDFS.subClassOf, marriot);
        marriotCusco.addProperty(RDFS.subClassOf, marriot);

        InfModel infModel = ModelFactory.createRDFSModel(model);

        ResIterator it = infModel.listSubjectsWithProperty(RDFS.subClassOf, marriot);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }

    public void hotelsInferenceSubClass(String superClass) {
        Resource accordHotelGroup = model.getResource(LB_NS + superClass);
        InfModel infModel = ModelFactory.createRDFSModel(model);

        ResIterator it = infModel.listSubjectsWithProperty(RDFS.subClassOf, accordHotelGroup);
        while (it.hasNext()) {
            System.out.println(it.nextResource());
        }
    }
}
