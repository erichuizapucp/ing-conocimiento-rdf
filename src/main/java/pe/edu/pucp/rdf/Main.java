package pe.edu.pucp.rdf;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        try {
            Options options = new Options();
            options = buildHotelSearchOptions(options);
            options = buildHotelInferenceOptions(options);

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("s")) {
                HotelsSearch hotelsSearch = new HotelsSearch();
                if (cmd.hasOption("hotel-name")) {
                    String hotelName = cmd.getOptionValue("hotel-name");
                    hotelsSearch.hotelsByName(hotelName);
                }
                if (cmd.hasOption("hotel-city")) {
                    String city = cmd.getOptionValue("hotel-city");
                    hotelsSearch.hotelsByCity(city);
                }
                if (cmd.hasOption("hotel-price")) {
                    double price = Double.parseDouble(cmd.getOptionValue("hotel-price"));
                    hotelsSearch.hotelsByPrice(price);
                }
                if (cmd.hasOption("hotel-district")) {
                    String hotelDistrict = cmd.getOptionValue("hotel-district");
                    hotelsSearch.hotelsByDistrict(hotelDistrict);
                }
                if (cmd.hasOption("hotel-postal-code")) {
                    String hotelPostalCode = cmd.getOptionValue("hotel-postal-code");
                    hotelsSearch.hotelsByPostalCode(hotelPostalCode);
                }
                if (cmd.hasOption("hotel-rating-value")) {
                    int ratingValue = Integer.parseInt(cmd.getOptionValue("hotel-rating-value"));
                    hotelsSearch.hotelsByMinumRatingValue(ratingValue);
                }
            }

            if (cmd.hasOption("i")) {
                HotelsInference hotelsInference = new HotelsInference();
                if (cmd.hasOption("super-class-runtime")) {
                    hotelsInference.addSuperClassesAtRuntime();
                }
                if (cmd.hasOption("super-class")) {
                    String superClass = cmd.getOptionValue("super-class");
                    hotelsInference.hotelsInferenceSubClass(superClass);
                }
                if (cmd.hasOption("super-class-accommodation")) {
                    String superClass = cmd.getOptionValue("super-class-accommodation");
                    hotelsInference.hotelsAccommodationInferenceSubClass(superClass);
                }
            }
        }
        catch (ParseException e) {
            System.err.println(e);
        }
    }

    private static Options buildHotelSearchOptions(Options options) {
        options.addOption("s", "search", false, "Performs searches in RDF");
        options.addOption(Option.builder()
                .longOpt("hotel-name")
                .desc("Hotel Name")
                .hasArg()
                .argName("NAME")
                .build());
        options.addOption(Option.builder()
                .longOpt("hotel-city")
                .desc("City where a hotel is located")
                .hasArg()
                .argName("CITY")
                .build());
        options.addOption(Option.builder()
                .longOpt("hotel-price")
                .desc("Hotel price")
                .hasArg()
                .argName("PRICE")
                .build());
        options.addOption(Option.builder()
                .longOpt("hotel-district")
                .desc("Hotel district")
                .hasArg()
                .argName("DISTRICT")
                .build());
        options.addOption(Option.builder()
                .longOpt("hotel-postal-code")
                .desc("Hotel postal code")
                .hasArg()
                .argName("POSTAL_CODE")
                .build());
        options.addOption(Option.builder()
                .longOpt("hotel-rating-value")
                .desc("Hotel rating value")
                .hasArg()
                .argName("RATING_VALUE")
                .build());
        return options;
    }

    private static Options buildHotelInferenceOptions(Options options) {
        options.addOption("i", "inferences", false, "performs inferences in RDF");
        options.addOption(Option.builder()
                .longOpt("super-class-runtime")
                .desc("Add super classes at runtime")
                .build());
        options.addOption(Option.builder()
                .longOpt("super-class")
                .desc("Determine super classes based on RDF")
                .hasArg()
                .argName("SUPERCLASS")
                .build());
        options.addOption(Option.builder()
                .longOpt("super-class-accommodation")
                .desc("Determine super classes based on RDF")
                .hasArg()
                .argName("SUPERCLASS")
                .build());
        return options;
    }
}