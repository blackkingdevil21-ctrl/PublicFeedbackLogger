package com.feedback.demo;

import com.feedback.demo.service.OfficerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDataRunner implements CommandLineRunner {

    private final OfficerService officerService;

    public InitDataRunner(OfficerService officerService) {
        this.officerService = officerService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Initializing Officers for 100 Domains...");

        List<OfficerData> officers = List.of(
            new OfficerData("water.officer", "Water Officer", "water@gov.in", "Water Supply"),
            new OfficerData("roads.officer", "Roads Officer", "roads@gov.in", "Roads"),
            new OfficerData("electricity.officer", "Electricity Officer", "electricity@gov.in", "Electricity"),
            new OfficerData("sanitation.officer", "Sanitation Officer", "sanitation@gov.in", "Garbage"),
            new OfficerData("traffic.officer", "Traffic Officer", "traffic@gov.in", "Traffic"),
            new OfficerData("drainage.officer", "Drainage Officer", "drainage@gov.in", "Drainage"),
            new OfficerData("streetlighting.officer", "Street Lighting Officer", "streetlighting@gov.in", "Street Lighting"),
            new OfficerData("transport.officer", "Public Transport Officer", "transport@gov.in", "Public Transport"),
            new OfficerData("parks.officer", "Parks Officer", "parks@gov.in", "Parks and Recreation"),
            new OfficerData("sewage.officer", "Sewage Officer", "sewage@gov.in", "Sewage"),
            new OfficerData("hospitals.officer", "Hospitals Officer", "hospitals@gov.in", "Hospitals"),
            new OfficerData("schools.officer", "Schools Officer", "schools@gov.in", "Schools"),
            new OfficerData("noisepollution.officer", "Noise Pollution Officer", "noisepollution@gov.in", "Noise Pollution"),
            new OfficerData("airpollution.officer", "Air Pollution Officer", "airpollution@gov.in", "Air Pollution"),
            new OfficerData("animalcontrol.officer", "Animal Control Officer", "animalcontrol@gov.in", "Animal Control"),
            new OfficerData("parking.officer", "Parking Officer", "parking@gov.in", "Parking"),
            new OfficerData("firesafety.officer", "Fire Safety Officer", "firesafety@gov.in", "Fire Safety"),
            new OfficerData("police.officer", "Police Officer", "police@gov.in", "Police"),
            new OfficerData("streetvendors.officer", "Street Vendors Officer", "streetvendors@gov.in", "Street Vendors"),
            new OfficerData("flooding.officer", "Flooding Officer", "flooding@gov.in", "Flooding"),
            new OfficerData("publictoilets.officer", "Public Toilets Officer", "publictoilets@gov.in", "Public Toilets"),
            new OfficerData("publicwifi.officer", "Public WiFi Officer", "publicwifi@gov.in", "Public WiFi"),
            new OfficerData("roadsigns.officer", "Road Signs Officer", "roadsigns@gov.in", "Road Signs"),
            new OfficerData("sidewalks.officer", "Sidewalks Officer", "sidewalks@gov.in", "Sidewalks"),
            new OfficerData("busstops.officer", "Bus Stops Officer", "busstops@gov.in", "Bus Stops"),
            new OfficerData("publiclibraries.officer", "Public Libraries Officer", "publiclibraries@gov.in", "Public Libraries"),
            new OfficerData("streetcleaning.officer", "Street Cleaning Officer", "streetcleaning@gov.in", "Street Cleaning"),
            new OfficerData("potholes.officer", "Potholes Officer", "potholes@gov.in", "Potholes"),
            new OfficerData("treemaintenance.officer", "Tree Maintenance Officer", "treemaintenance@gov.in", "Tree Maintenance"),
            new OfficerData("waterquality.officer", "Water Quality Officer", "waterquality@gov.in", "Water Quality"),
            new OfficerData("publicpools.officer", "Public Pools Officer", "publicpools@gov.in", "Public Pools"),
            new OfficerData("publicmarkets.officer", "Public Markets Officer", "publicmarkets@gov.in", "Public Markets"),
            new OfficerData("bridgemaintenance.officer", "Bridge Maintenance Officer", "bridgemaintenance@gov.in", "Bridge Maintenance"),
            new OfficerData("monuments.officer", "Monuments Officer", "monuments@gov.in", "Monuments"),
            new OfficerData("cemeteries.officer", "Cemeteries Officer", "cemeteries@gov.in", "Cemeteries"),
            new OfficerData("streetfurniture.officer", "Street Furniture Officer", "streetfurniture@gov.in", "Street Furniture"),
            new OfficerData("buildingcodes.officer", "Building Codes Officer", "buildingcodes@gov.in", "Building Codes"),
            new OfficerData("zoning.officer", "Zoning Officer", "zoning@gov.in", "Zoning"),
            new OfficerData("parkslighting.officer", "Parks Lighting Officer", "parkslighting@gov.in", "Parks Lighting"),
            new OfficerData("graffiti.officer", "Graffiti Officer", "graffiti@gov.in", "Graffiti"),
            new OfficerData("publiceventsnoise.officer", "Public Events Noise Officer", "publiceventsnoise@gov.in", "Public Events Noise"),
            new OfficerData("trafficsignals.officer", "Traffic Signals Officer", "trafficsignals@gov.in", "Traffic Signals"),
            new OfficerData("taxiservices.officer", "Taxi Services Officer", "taxiservices@gov.in", "Taxi Services"),
            new OfficerData("bikelanes.officer", "Bike Lanes Officer", "bikelanes@gov.in", "Bike Lanes"),
            new OfficerData("snowremoval.officer", "Snow Removal Officer", "snowremoval@gov.in", "Snow Removal"),
            new OfficerData("sewermaintenance.officer", "Sewer Maintenance Officer", "sewermaintenance@gov.in", "Sewer Maintenance"),
            new OfficerData("streetdrainage.officer", "Street Drainage Officer", "streetdrainage@gov.in", "Street Drainage"),
            new OfficerData("streetsweeping.officer", "Street Sweeping Officer", "streetsweeping@gov.in", "Street Sweeping"),
            new OfficerData("rodentcontrol.officer", "Rodent Control Officer", "rodentcontrol@gov.in", "Rodent Control"),
            new OfficerData("publichealthclinics.officer", "Public Health Clinics Officer", "publichealthclinics@gov.in", "Public Health Clinics"),
            new OfficerData("emergencyservices.officer", "Emergency Services Officer", "emergencyservices@gov.in", "Emergency Services"),
            new OfficerData("urbanplanning.officer", "Urban Planning Officer", "urbanplanning@gov.in", "Urban Planning"),
            new OfficerData("permitissuance.officer", "Permit Issuance Officer", "permitissuance@gov.in", "Permit Issuance"),
            new OfficerData("illegaldumping.officer", "Illegal Dumping Officer", "illegaldumping@gov.in", "Illegal Dumping"),
            new OfficerData("streetperformers.officer", "Street Performers Officer", "streetperformers@gov.in", "Street Performers"),
            new OfficerData("constructionnoise.officer", "Construction Noise Officer", "constructionnoise@gov.in", "Construction Noise"),
            new OfficerData("publicsafety.officer", "Public Safety Officer", "publicsafety@gov.in", "Public Safety"),
            new OfficerData("disastermanagement.officer", "Disaster Management Officer", "disastermanagement@gov.in", "Disaster Management"),
            new OfficerData("watermetering.officer", "Water Metering Officer", "watermetering@gov.in", "Water Metering"),
            new OfficerData("stormwatermanagement.officer", "Stormwater Management Officer", "stormwatermanagement@gov.in", "Stormwater Management"),
            new OfficerData("homelessshelters.officer", "Homeless Shelters Officer", "homelessshelters@gov.in", "Homeless Shelters"),
            new OfficerData("streetclosures.officer", "Street Closures Officer", "streetclosures@gov.in", "Street Closures"),
            new OfficerData("cityplanning.officer", "City Planning Officer", "cityplanning@gov.in", "City Planning"),
            new OfficerData("wastemanagement.officer", "Waste Management Officer", "wastemanagement@gov.in", "Waste Management"),
            new OfficerData("sidewalkrepair.officer", "Sidewalk Repair Officer", "sidewalkrepair@gov.in", "Sidewalk Repair"),
            new OfficerData("publicschoolsmaintenance.officer", "Public Schools Maintenance Officer", "publicschoolsmaintenance@gov.in", "Public Schools Maintenance"),
            new OfficerData("waterbilling.officer", "Water Billing Officer", "waterbilling@gov.in", "Water Billing"),
            new OfficerData("electricbilling.officer", "Electric Billing Officer", "electricbilling@gov.in", "Electric Billing"),
            new OfficerData("energyconservation.officer", "Energy Conservation Officer", "energyconservation@gov.in", "Energy Conservation"),
            new OfficerData("publicparkssafety.officer", "Public Parks Safety Officer", "publicparkssafety@gov.in", "Public Parks Safety"),
            new OfficerData("communitycenters.officer", "Community Centers Officer", "communitycenters@gov.in", "Community Centers"),
            new OfficerData("streetlightingmaintenance.officer", "Street Lighting Maintenance Officer", "streetlightingmaintenance@gov.in", "Street Lighting Maintenance"),
            new OfficerData("streetrepairs.officer", "Street Repairs Officer", "streetrepairs@gov.in", "Street Repairs"),
            new OfficerData("buildingmaintenance.officer", "Building Maintenance Officer", "buildingmaintenance@gov.in", "Building Maintenance"),
            new OfficerData("graffitiremoval.officer", "Graffiti Removal Officer", "graffitiremoval@gov.in", "Graffiti Removal"),
            new OfficerData("publicparkingenforcement.officer", "Public Parking Enforcement Officer", "publicparkingenforcement@gov.in", "Public Parking Enforcement"),
            new OfficerData("publicplaygroundsafety.officer", "Public Playground Safety Officer", "publicplaygroundsafety@gov.in", "Public Playground Safety"),
            new OfficerData("busschedules.officer", "Bus Schedules Officer", "busschedules@gov.in", "Bus Schedules"),
            new OfficerData("roadaccidents.officer", "Road Accidents Officer", "roadaccidents@gov.in", "Road Accidents"),
            new OfficerData("animalshelters.officer", "Animal Shelters Officer", "animalshelters@gov.in", "Animal Shelters"),
            new OfficerData("buildingpermits.officer", "Building Permits Officer", "buildingpermits@gov.in", "Building Permits"),
            new OfficerData("streetsignmaintenance.officer", "Street Sign Maintenance Officer", "streetsignmaintenance@gov.in", "Street Sign Maintenance"),
            new OfficerData("publiclandscaping.officer", "Public Landscaping Officer", "publiclandscaping@gov.in", "Public Landscaping"),
            new OfficerData("publicart.officer", "Public Art Officer", "publicart@gov.in", "Public Art"),
            new OfficerData("swimmingpoolssafety.officer", "Swimming Pools Safety Officer", "swimmingpoolssafety@gov.in", "Swimming Pools Safety"),
            new OfficerData("publicpoolsmaintenance.officer", "Public Pools Maintenance Officer", "publicpoolsmaintenance@gov.in", "Public Pools Maintenance"),
            new OfficerData("recyclingservices.officer", "Recycling Services Officer", "recyclingservices@gov.in", "Recycling Services"),
            new OfficerData("streetcleaningschedule.officer", "Street Cleaning Schedule Officer", "streetcleaningschedule@gov.in", "Street Cleaning Schedule"),
            new OfficerData("trafficenforcement.officer", "Traffic Enforcement Officer", "trafficenforcement@gov.in", "Traffic Enforcement"),
            new OfficerData("publictransportmaintenance.officer", "Public Transport Maintenance Officer", "publictransportmaintenance@gov.in", "Public Transport Maintenance"),
            new OfficerData("streetfurnituremaintenance.officer", "Street Furniture Maintenance Officer", "streetfurnituremaintenance@gov.in", "Street Furniture Maintenance"),
            new OfficerData("waterleakdetection.officer", "Water Leak Detection Officer", "waterleakdetection@gov.in", "Water Leak Detection"),
            new OfficerData("pavementmarkings.officer", "Pavement Markings Officer", "pavementmarkings@gov.in", "Pavement Markings"),
            new OfficerData("bikesharingservices.officer", "Bike Sharing Services Officer", "bikesharingservices@gov.in", "Bike Sharing Services")
        );

        for (OfficerData o : officers) {
            try {
                officerService.createOfficer(o.username, "password123", o.name, o.email, o.domain);
                System.out.println("✅ Created officer: " + o.username + " for " + o.domain);
            } catch (Exception e) {
                System.out.println("⚠️ Officer already exists: " + o.username);
            }
        }

        System.out.println("🎉 Officers initialization complete!");
    }

    private static class OfficerData {
        String username, name, email, domain;

        OfficerData(String username, String name, String email, String domain) {
            this.username = username;
            this.name = name;
            this.email = email;
            this.domain = domain;
        }
    }
}
