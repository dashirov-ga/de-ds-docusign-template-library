package ly.generalassemb.de.dataservices.constants;

import java.util.TimeZone;

/**
 * SELECT
 * UPPER(REPLACE(metro_name, ' ', '_')) as enum,
 * metro_name,
 * metro_abbreviation,
 * metro_slug,
 * metro_continent_name,
 * metro_continent_slug,
 * metro_core_metro_id,
 * metro_core_continent_id,
 * metro_timezone
 * FROM presentation.dim_metros;
 */
public enum Metro {
    // "enum","metro_name","metro_abbreviation","metro_slug","metro_continent_name","metro_continent_slug","metro_core_metro_id","metro_core_continent_id","metro_timezone"
    NEW_YORK_CITY("New York City", "NYC", "new-york-city", "North America", "north-america", 1, 1, TimeZone.getTimeZone("America/New_York")),
    SAN_FRANCISCO("San Francisco", "SF", "san-francisco", "North America", "north-america", 2, 1, TimeZone.getTimeZone("America/Los_Angeles")),
    LONDON("London", "LDN", "london", "Europe", "europe", 3, 2, TimeZone.getTimeZone("Europe/London")),
    BERLIN("Berlin", "BER", "berlin", "Europe", "europe", 4, 2, TimeZone.getTimeZone("Europe/Berlin")),
    SYDNEY("Sydney", "SYD", "sydney", "Australia", "australia", 5, 4, TimeZone.getTimeZone("Australia/Sydney")),
    PHILADELPHIA("Philadelphia", "PHL", "philadelphia", "North America", "north-america", 6, 1, TimeZone.getTimeZone("America/New_York")),
    BOSTON("Boston", "BOS", "boston", "North America", "north-america", 7, 1, TimeZone.getTimeZone("America/New_York")),
    LOS_ANGELES("Los Angeles", "LA", "los-angeles", "North America", "north-america", 8, 1, TimeZone.getTimeZone("America/Los_Angeles")),
    MELBOURNE("Melbourne", "MEL", "melbourne", "Australia", "australia", 9, 4, TimeZone.getTimeZone("Australia/Melbourne")),
    WASHINGTON_DC("Washington, D.C.", "DC", "washington-dc", "North America", "north-america", 10, 1, TimeZone.getTimeZone("America/New_York")),
    AUSTIN("Austin", "ATX", "austin", "North America", "north-america", 11, 1, TimeZone.getTimeZone("America/Chicago")),
    HONG_KONG("Hong Kong", "HK", "hong-kong", "Asia", "asia", 12, 3, TimeZone.getTimeZone("Asia/Hong_Kong")),
    ATLANTA("Atlanta", "ATL", "atlanta", "North America", "north-america", 13, 1, TimeZone.getTimeZone("America/New_York")),
    SEATTLE("Seattle", "SEA", "seattle", "North America", "north-america", 14, 1, TimeZone.getTimeZone("America/Los_Angeles")),
    CHICAGO("Chicago", "CHI", "chicago", "North America", "north-america", 15, 1, TimeZone.getTimeZone("America/Chicago")),
    ONLINE("Online", "ONL", "online", "Online", "online", 16, 5, TimeZone.getTimeZone("America/New_York")),
    DALLAS("Dallas", "DAL", "dallas", "North America", "north-america", 17, 1, TimeZone.getTimeZone("America/Chicago")),
    SINGAPORE("Singapore", "SGP", "singapore", "Asia", "asia", 18, 3, TimeZone.getTimeZone("Asia/Singapore")),
    DENVER("Denver", "DEN", "denver", "North America", "north-america", 19, 1, TimeZone.getTimeZone("America/Denver")),
    RALEIGH("Raleigh", "RAL", "raleigh", "North America", "north-america", 20, 1, TimeZone.getTimeZone("America/New_York")),
    TORONTO("Toronto", "TOR", "toronto", "North America", "north-america", 21, 1, TimeZone.getTimeZone("America/Toronto")),
    BRISBANE("Brisbane", "BNE", "brisbane", "Australia", "australia", 22, 4, TimeZone.getTimeZone("Australia/Brisbane")),
    KANSAS_CITY("Kansas City", "KC", "kansas-city", "North America", "north-america", 23, 1, TimeZone.getTimeZone("America/Chicago")),
    PROVIDENCE("Providence", "PVD", "providence", "North America", "north-america", 24, 1, TimeZone.getTimeZone("America/New_York")),
    SAN_DIEGO("San Diego", "SD", "san-diego", "North America", "north-america", 25, 1, TimeZone.getTimeZone("America/Los_Angeles"));


    private final String metroName;
    private final String metroAbbreviation;
    private final String metroSlug;
    private final String metroContinentName;
    private final String getMetroContinentSlug;
    private final int metroId;
    private final int continentId;
    private final TimeZone metroTz;

    Metro(String metroName, String metroAbbreviation, String metroSlug, String metroContinentName, String getMetroContinentSlug, int metroId, int continentId, TimeZone metroTz) {
        this.metroName = metroName;
        this.metroAbbreviation = metroAbbreviation;
        this.metroSlug = metroSlug;
        this.metroContinentName = metroContinentName;
        this.getMetroContinentSlug = getMetroContinentSlug;
        this.metroId = metroId;
        this.continentId = continentId;
        this.metroTz = metroTz;

    }

    public String getMetroName() {
        return metroName;
    }

    public String getMetroAbbreviation() {
        return metroAbbreviation;
    }

    public String getMetroSlug() {
        return metroSlug;
    }

    public String getMetroContinentName() {
        return metroContinentName;
    }

    public String getGetMetroContinentSlug() {
        return getMetroContinentSlug;
    }

    public int getMetroId() {
        return metroId;
    }

    public int getContinentId() {
        return continentId;
    }

    public TimeZone getMetroTz() {
        return metroTz;
    }


}
