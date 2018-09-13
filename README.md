# Demo - Geographic Catastrophe Modeling

## Overview

As Hurricane Florence one of the strongest storms on [the Eastern Seaboard in decades](http://www.cnn.com/2018/09/10/us/major-hurricanes-north-south-carolina-wxc/index.html) threatens millions of people with hurricane-force winds and dangerous storm surges, one’s safety is paramount.

The other aspect of any natural disaster is the financial burden due to property damage. This is why insurance companies are using data and analytics to optimally evaluate risk and respond quicker to weather-related events.  Insurance agencies can use tools to help businesses and homeowners also assess their own risks and influence their policy purchasing.

Leveraging advanced analytics on hot data **at scale from various sources** (geospatial, weather feeds, wind speeds etc.)  and enriched with historical data, can provide more accurate information for risk reduction and quick responses.

Check out a great demo below that was created for an insurance company that wanted to present to customers the potential asset loss according to the hurricane tracking predictions and the personal client data.
Gigaspaces' XAP Catastrophe-Modeling demo is a risk management tool that demonstrates how insurers, reinsurers, private businesses, and government agencies can assess exposure due to natural and man-made catastrophes.

![Screen shot](/screen-shot.png?raw=true "Demo Screen Shot")

# Running the demo

## Requirements
* InsightEdge / XAP 11 or later

## Dependencies
* You will need to add XAP geospatial dependency to web application ${XAP_HOME}/lib/spatial/xap-spatial.jar

## Build

```bash
mvn clean package
```

## Deploy

1. Empty space "datagrid"
2. Feeder
3. Web Application
