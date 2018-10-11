package pe.edu.pucp.rdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;

public class Search {
    private static final String EXAMPLE_01_FILE = "minimal-example.rdf";
    private static final String EXAMPLE_02_FILE = "geo-position.rdf";
    private static final String EXAMPLE_03_FILE = "telephone-fax.rdf";

    public static void main(String[] args) {
        FileManager fileManager = FileManager.get();

        Model model = fileManager.loadModel(EXAMPLE_01_FILE);
        StmtIterator statementsIterator = model.listStatements();

        while (statementsIterator.hasNext()) {
            Statement statement = statementsIterator.nextStatement();
            System.out.println(statement);
        }

//        Model model = fileManager.loadModel(EXAMPLE_01_FILE);
//        ResIterator resIterator = model.listSubjects();
//
//        while (resIterator.hasNext()) {
//            Resource resource = resIterator.nextResource();
//            System.out.println(resource);
//        }
    }
}