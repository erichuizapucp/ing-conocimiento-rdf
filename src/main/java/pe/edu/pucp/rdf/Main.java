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

        return options;
    }

    private static Options buildHotelInferenceOptions(Options options) {
        options.addOption("i", "inferences", false, "performs inferences in RDF");
        return options;
    }
}