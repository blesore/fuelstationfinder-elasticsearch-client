package com.ktenas.orestis.p03078.fuelstationfinder.elastic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktenas.orestis.p03078.fuelstationfinder.elastic.entities.FuelStation;
import com.ktenas.orestis.p03078.fuelstationfinder.elastic.enums.FuelType;
import com.ktenas.orestis.p03078.fuelstationfinder.elastic.enums.StationBrand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.GeoDistanceFilterBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    Client client;

    @RequestMapping(value = {"/fuelstations/", "/fuelstations"}, method = RequestMethod.GET)
    public List<FuelStation> getPoints(@RequestParam(value = "lat") double latitude,
            @RequestParam(value = "lon") double longitude,
            @RequestParam(value = "fuelType") String fuelType,
            @RequestParam(value = "brand") String stationBrand,
            @RequestParam(value = "numOfPoints") int numOfPoints) throws IOException {

        FilterBuilder brandFilter;
        if (stationBrand.equals("0")) {
            brandFilter = FilterBuilders.matchAllFilter();
        } else {
            StationBrand brand = null;
            switch (stationBrand) {
                case "1":
                    brand = StationBrand.AEGEAN;
                    break;
                case "2":
                    brand = StationBrand.AVIN;
                    break;
                case "3":
                    brand = StationBrand.BP;
                    break;
                case "4":
                    brand = StationBrand.CYCLON;
                    break;
                case "5":
                    brand = StationBrand.ELIN;
                    break;
                case "6":
                    brand = StationBrand.EKO;
                    break;
                case "7":
                    brand = StationBrand.ETEKA;
                    break;
                case "8":
                    brand = StationBrand.JETOIL;
                    break;
                case "9":
                    brand = StationBrand.REVOIL;
                    break;
                case "10":
                    brand = StationBrand.SHELL;
                    break;
            }
            brandFilter = FilterBuilders.termFilter("brand", brand.toString()
                    .toLowerCase());
        }
        FuelType type;
        switch (fuelType) {
            case "0":
                type = FuelType.UNLEADED_95;
                break;
            case "1":
                type = FuelType.UNLEADED_100;
                break;
            case "2":
                type = FuelType.DIESEL;
                break;
            case "3":
                type = FuelType.AUTOGAS;
                break;
            default:
                type = FuelType.UNLEADED_95;
                break;
        }
        // filter
        FilterBuilder typeFilter = FilterBuilders.termFilter("availableFuel.fuelType", type.toString()
                .toLowerCase());
        GeoDistanceFilterBuilder distanceFilter = FilterBuilders.geoDistanceFilter("location")
                .point(latitude, longitude)
                .distance(15, DistanceUnit.KILOMETERS)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);

        FilterBuilder filter = FilterBuilders.andFilter(typeFilter, brandFilter, distanceFilter);
        // sort with closest first
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location")
                .point(latitude, longitude)
                .order(SortOrder.ASC);

        SearchResponse response = client.prepareSearch("fuelstations")
                .setQuery(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), filter))
                .addSort(sort)
                .setSize(numOfPoints)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits()
                .getHits();
        List<FuelStation> points = new ArrayList<>();
        String fuelStationSource;
        ObjectMapper mapper = new ObjectMapper();
        for (SearchHit hit : hits) {
            fuelStationSource = hit.getSourceAsString();
            FuelStation fs = mapper.readValue(fuelStationSource, FuelStation.class);
            points.add(fs);
        }

        return points;
    }
}
