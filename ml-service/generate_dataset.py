import csv
import random

# 100 diverse civic problem domains
domains = [
    "Water Supply", "Electricity", "Roads", "Garbage", "Traffic", "Drainage", "Street Lighting",
    "Public Transport", "Parks and Recreation", "Sewage", "Hospitals", "Schools", "Noise Pollution",
    "Air Pollution", "Animal Control", "Parking", "Fire Safety", "Police", "Street Vendors",
    "Flooding", "Public Toilets", "Public WiFi", "Road Signs", "Sidewalks", "Bus Stops", "Public Libraries",
    "Street Cleaning", "Potholes", "Tree Maintenance", "Water Quality", "Public Pools", "Public Markets",
    "Bridge Maintenance", "Monuments", "Cemeteries", "Street Furniture", "Building Codes", "Zoning",
    "Parks Lighting", "Graffiti", "Public Events Noise", "Traffic Signals", "Taxi Services",
    "Bike Lanes", "Snow Removal", "Sewer Maintenance", "Street Drainage", "Street Sweeping", "Rodent Control",
    "Public Health Clinics", "Emergency Services", "Urban Planning", "Permit Issuance", "Illegal Dumping",
    "Street Performers", "Construction Noise", "Public Safety", "Disaster Management", "Water Metering",
    "Stormwater Management", "Homeless Shelters", "Street Closures", "City Planning", "Waste Management",
    "Sidewalk Repair", "Public Schools Maintenance", "Water Billing", "Electric Billing", "Energy Conservation",
    "Public Parks Safety", "Community Centers", "Street Lighting Maintenance", "Street Repairs", "Building Maintenance",
    "Graffiti Removal", "Public Parking Enforcement", "Public Playground Safety", "Bus Schedules", "Road Accidents",
    "Animal Shelters", "Building Permits", "Street Sign Maintenance", "Public Landscaping", "Public Art",
    "Swimming Pools Safety", "Public Pools Maintenance", "Recycling Services", "Street Cleaning Schedule", "Traffic Enforcement",
    "Public Transport Maintenance", "Street Furniture Maintenance", "Water Leak Detection", "Pavement Markings", "Bike Sharing Services"
]

# Sample problem templates
templates = {
    "Water Supply": [
        "There is a leak in the water pipe near {location} street.",
        "No water supply since morning in {location}.",
        "Water pressure is very low in {location} neighborhood.",
    ],
    "Electricity": [
        "Electricity has been out for hours in {location}.",
        "Streetlight on {location} avenue is broken.",
        "Frequent power fluctuations damaging appliances in {location}.",
    ],
    "Roads": [
        "Large potholes on {location} road making driving unsafe.",
        "Road needs urgent repair on {location} street.",
        "Cracks and damage visible on {location} highway.",
    ],
    "Garbage": [
        "Garbage bins overflowed at {location} for days.",
        "Trash has not been collected from {location} area.",
        "Illegal dumping found near {location} park.",
    ],
}

# Locations
locations = ["MG Road", "Park Avenue", "Sector 9", "Hill Valley", "Main Street", 
             "Riverbank", "Old Town", "Downtown", "North End", "West Park"]

# Generate 1000 entries
entries = []
id_counter = 1

for _ in range(1000):
    domain = random.choice(domains)
    
    # Use template if available, else generic
    if domain in templates:
        template = random.choice(templates[domain])
        location = random.choice(locations)
        description = template.format(location=location)
    else:
        location = random.choice(locations)
        description = f"Problem reported in {domain} area at {location} causing inconvenience."
    
    entries.append([id_counter, description, domain])
    id_counter += 1

# Write to CSV
filename = "expanded_feedback_dataset_1000.csv"
with open(filename, mode="w", newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    writer.writerow(["ID", "Problem Description", "Domain"])
    writer.writerows(entries)

print(f"✅ Dataset created: {filename}")
print(f"✅ Total entries: {len(entries)}")
print(f"✅ Unique domains: {len(set([e[2] for e in entries]))}")
