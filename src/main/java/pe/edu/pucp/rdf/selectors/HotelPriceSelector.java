package pe.edu.pucp.rdf.selectors;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

public class HotelPriceSelector extends SimpleSelector {
    private double price;

    public HotelPriceSelector(double price) {
        super(null, SchemaDOTOrg.PRICE_RANGE, (RDFNode)null);
        this.price = price;
    }

    @Override
    public boolean selects(Statement s) {
        String priceRange = s.getString();
        String[] ranges = priceRange.split("-");

        double startRange = Double.parseDouble(ranges[0].trim().replace("$", ""));
        double endRange = Double.parseDouble(ranges[1].trim().replace("$", ""));

        return price >= startRange && price <= endRange;
    }
}
