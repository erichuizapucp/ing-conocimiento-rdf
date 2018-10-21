package pe.edu.pucp.rdf.selectors;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import pe.edu.pucp.rdf.vocabulary.SchemaDOTOrg;

public class HotelRatingSelector extends SimpleSelector {
    private int ratingValue;

    public HotelRatingSelector(int ratingValue) {
        super(null, SchemaDOTOrg.RATING_VALUE, (RDFNode)null);
        this.ratingValue = ratingValue;
    }

    @Override
    public boolean selects(Statement s) {
        int rating = Integer.parseInt(s.getString().trim());
        return ratingValue <= rating;
    }
}
