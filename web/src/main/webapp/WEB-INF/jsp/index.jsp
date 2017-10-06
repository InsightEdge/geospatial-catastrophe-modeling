<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<title>XAP Geospatial</title>
<meta name='viewport' content='initial-scale=1,maximum-scale=1,user-scalable=no' />
<script src='https://api.mapbox.com/mapbox.js/v2.2.2/mapbox.js'></script>
<link href='https://api.mapbox.com/mapbox.js/v2.2.2/mapbox.css'
	rel='stylesheet' />
<style>
body {
	margin: 0;
	padding: 0;
}

#map {
	position: absolute;
	top: 0;
	bottom: 0;
	width: 100%;
}

#displayOptions {
	position: absolute;
	top: 10px;
	right: 10px;
	z-index: 10;
	background: #fff;
	width: 300px;
	padding: 1em;
	opacity: 0.8;
	border-radius: 10px;
	border: 1px solid #000000;
}

#displayScenarioChoice {
	position: absolute;
	top: 10px;
	right: 10px;
	z-index: 10;
	background: #fff;
	width: 300px;
	padding: 1em;
	opacity: 0.8;
	border-radius: 10px;
	border: 1px solid #000000;
}

#resultTable {
	position: absolute;
	top: 10px;
	right: 10px;
	z-index: 10;
	background: #fff;
	width: 300px;
	padding: 1em;
	opacity: 0.8;
	border-radius: 10px;
	border: 1px solid #000000;
}
</style>
</head>
<body>
 
	<link href='https://api.mapbox.com/mapbox.js/plugins/leaflet-draw/v0.2.2/leaflet.draw.css' rel='stylesheet' />
	<!-- 
	<script	src='https://api.mapbox.com/mapbox.js/plugins/leaflet-draw/v0.2.2/leaflet.draw.js'></script>
	-->
	
	<!-- Easy Button -->
	<script src="resources/easy-button/src/easy-button.js"></script>
	<link href='resources/easy-button/src/easy-button.css' rel='stylesheet' />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
	<script src="resources/leaflet-draw-touch/src/Leaflet.draw.js"></script>

    <script src="resources/leaflet-draw-touch/src/edit/handler/Edit.Poly.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/handler/Edit.SimpleShape.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/handler/Edit.Circle.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/handler/Edit.Rectangle.js"></script>

    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Feature.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Polyline.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Polygon.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.SimpleShape.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Rectangle.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Circle.js"></script>
    <script src="resources/leaflet-draw-touch/src/draw/handler/Draw.Marker.js"></script>

    <script src="resources/leaflet-draw-touch/src/ext/TouchEvents.js"></script>
    <script src="resources/leaflet-draw-touch/src/ext/LatLngUtil.js"></script>
    <script src="resources/leaflet-draw-touch/src/ext/GeometryUtil.js"></script>
    <script src="resources/leaflet-draw-touch/src/ext/LineUtil.Intersect.js"></script>
    <script src="resources/leaflet-draw-touch/src/ext/Polyline.Intersect.js"></script>
    <script src="resources/leaflet-draw-touch/src/ext/Polygon.Intersect.js"></script>

    <script src="resources/leaflet-draw-touch/src/Control.Draw.js"></script>
    <script src="resources/leaflet-draw-touch/src/Tooltip.js"></script>
    <script src="resources/leaflet-draw-touch/src/Toolbar.js"></script>

    <script src="resources/leaflet-draw-touch/src/draw/DrawToolbar.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/EditToolbar.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/handler/EditToolbar.Edit.js"></script>
    <script src="resources/leaflet-draw-touch/src/edit/handler/EditToolbar.Delete.js"></script>
    
	<script src='https://api.mapbox.com/mapbox.js/plugins/leaflet-geodesy/v0.1.0/leaflet-geodesy.js'></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

	<!--Marker Cluster-->
	<script src='https://api.tiles.mapbox.com/mapbox.js/plugins/leaflet-markercluster/v0.4.0/leaflet.markercluster.js'></script>
	<link href='https://api.tiles.mapbox.com/mapbox.js/plugins/leaflet-markercluster/v0.4.0/MarkerCluster.css' rel='stylesheet' />
	<link href='https://api.tiles.mapbox.com/mapbox.js/plugins/leaflet-markercluster/v0.4.0/MarkerCluster.Default.css' rel='stylesheet' />

	<!-- Turf spatial operations-->
	<script src='https://api.tiles.mapbox.com/mapbox.js/plugins/turf/v2.0.2/turf.min.js'></script>
	
	<!-- GeoDesy Circle to Circle - polygon -->
	<script src='https://api.mapbox.com/mapbox.js/plugins/leaflet-geodesy/v0.1.0/leaflet-geodesy.js'></script>
	
	<div id='map'></div>
	<div id='stats'>
		
		<form id='displayOptions'>
			<button type="button" id="closeButton" style="position:absolute;top:0;right:0;border:none;background:none;"><b>X</b></button>
    
			<img id='logo' alt="Gigaspaces" src="resources/images/xap_logo.png"
				style="width:200px;display:block; margin: 0 auto;"  />

			<h3 id='demoTitle' style='text-align: center;'>Geographic Catastrophe Modeling Demo</h2>

			<table style='line-height:12px;'>
				<tr>
					<td>Portfolio</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;<input type="checkbox" name="portfolioCheckBox" value="10000" onclick="updateAssetsOnMap()" checked>Acme Residential</td>
				</tr>
				<tr>
					<td>&nbsp;<input type="checkbox" name="portfolioCheckBox" value="10001" onclick="updateAssetsOnMap()" >Acme Commercial</td>
				</tr>
			</table>
			
			<br/>
			<table style='line-height:12px;'>
				<tr>
					<td>Scenario</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;<input type="radio" name="scenarioRadioButton" value="none" onclick="updateMapWithScenario()" checked>None</td>
				</tr>
				<tr>
					<td>&nbsp;<input type="radio" name="scenarioRadioButton" value="sandyScenario1"	onclick="updateMapWithScenario()">Hurricane Projection 24</td>
				</tr>
				<tr>
					<td>&nbsp;<input type="radio" name="scenarioRadioButton" value="sandyScenario2"	onclick="updateMapWithScenario()">Hurricane Projection 25</td>
				</tr>
				<tr>
					<td>&nbsp;<input type="radio" name="scenarioRadioButton" value="sandyScenario3"	onclick="updateMapWithScenario()">Hurricane Projection 26</td>
				</tr>
			</table>
			<br/>
			<table style='line-height:12px;'>
				<tr>
					<td>Options</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;<input type="checkbox" id="groupByStateCheckbox" name="groupByStateCheckbox" value="groupByState" onclick="updateGroupByState()">Group By State</td>
				</tr>
			</table>
			<br/>
			<table id="resultsTable" style='line-height:12px;'>
				<tr>
					<td>Results</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;Total Assets</td>
					<td>&nbsp;<input type="label" id="TotalAssets" name="TotalAssets" value="0" readonly="true"></td>
				</tr>
				<tr>
					<td>&nbsp;Total Exposure</td>
					<td>&nbsp;<input type="label" id="TotalExposure" name="TotalExposure" value="0" readonly="true"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				<tr>
					<td>&nbsp;Scenario Assets</td>
					<td>&nbsp;<input type="label" id="ScenarioAssets" name="ScenarioAssets" value="0" readonly="true"></td>
				</tr>
				<tr >
					<td>&nbsp;Scenario Exposure</td>
					<td>&nbsp;<input type="label" id="ScenarioExposure" name="ScenarioExposure" value="0" readonly="true"></td>
				</tr>

			</table>

			<script>
				L.mapbox.accessToken = 'pk.eyJ1IjoiZ2lnYW1hcHMiLCJhIjoiY2lnNzY5bmRlMGkxb3Voa3BpN3J2Mm42MCJ9._nOW907q1yPYnxxtU5KUBA';

				var map = L.mapbox.map('map').setView([ 40.73, -74.011 ], 6).addLayer(L.mapbox.tileLayer('mapbox.streets'));

				var drawingFeatureGroup = L.featureGroup().addTo(map);
				var scenarioFeatureGroup = L.featureGroup().addTo(map);
				var markerFeatureGroup = L.featureGroup().addTo(map);

				updateAssetsOnMap();
				updateMapWithScenario();

		
				var drawControl = new L.Control.Draw({
					edit : {
						featureGroup : drawingFeatureGroup
					},
					draw : {
						position : 'topleft',
						polygon : {
							title : 'Draw a polygon!',
							allowIntersection : false,
							drawError : {
								color : '#b00b00',
								timeout : 1000
							},
							showArea : true
						},
						rectangle : false,
						circle : {
							title : 'Draw a circle!',
							shapeOptions : {
								color : '#662d91'
							},
							showArea : true
						},
						marker : false,
						polyline : false
					}
				}).addTo(map);
				
			   changeFilterDisplayBasedOnScreen();
			   
			   $("#closeButton").click(function(){
			   	   $("#stats").fadeOut(500);
			   });
			   
				var legandButton = L.easyButton('fa-filter', function(btn, map){
					updateFilterDisplay();
					$("#stats").fadeToggle(500);
				}, 'Filtering').addTo( map ); // probably just `map`
				
				map.on('draw:created', showPolygonArea);
				map.on('draw:edited', showPolygonAreaEdited);
				map.on('draw:deleted', deletePolygonArea);

				$( window ).on( "orientationchange", function( event ) {
					  changeFilterDisplayBasedOnScreen();
					});
					
				function showPolygonAreaEdited(e) {
					var etype = e.layerType;

					e.layers.eachLayer(function(layer) {
						showPolygonArea({
							layer : layer
						});

					});
					
				}
				function changeFilterDisplayBasedOnScreen(){
					updateFilterDisplay();
					
					var width = $(window).width(); 
				   
					if(width <= 500) {
					    $("#stats").hide();
					} 
				}
				function updateFilterDisplay(){
					var height = $(window).height(); 
				    var width = $(window).width(); 
				   
				    if(height <= 570) {
				      $("#resultsTable").hide();
				    } else{
				       $("#resultsTable").show();
				    }
				    if(height <= 460) {
				      $("#demoTitle").hide();
				      $("#logo").hide();
				    } else{
				      $("#demoTitle").show();
				      $("#logo").show();
				    }
				}
				function showPolygonArea(e) {
				
					var selectedShape = e.layer;
					calculateAreaForShape(selectedShape, true);
					
				}
				function calculateAreaForShape(selectedShape, performPopup) {

					var selectedShapeType = getShapeType(selectedShape);
					
					var selectedShapeGeoJSON = selectedShape.toGeoJSON();
					
					//Always a polygon
					var scenarioLayer = scenarioFeatureGroup.getLayers()[0];
					
					var scenarioLayerGeoJSON = scenarioLayer.getGeoJSON();
					
					var selectedScenarioId = getSelectedScenario();
					var portfolioIdList = getSelectedPortfolios();
						
					if (selectedShapeType === 'circle') {
					    
						var centerlatLng = selectedShape.getLatLng();
						var centerlat = centerlatLng.lat;
						var centerlng = centerlatLng.lng;
						var radius = selectedShape.getRadius();
						
						//Add radius as property because leaflet considers it a point
						selectedShapeGeoJSON.properties.radius = radius;
						
						var geodesyCircle = LGeo.circle([centerlat, centerlng], radius);
						var geodesyCircleGeoJSON = geodesyCircle.toGeoJSON();
						
						var intersectionGeoJSON = turf.intersect(geodesyCircleGeoJSON, scenarioLayerGeoJSON);
						
						if(intersectionGeoJSON === undefined){
							//Does not intersect
							calculateExposureForSelectedCircle(selectedShape, performPopup, selectedShapeGeoJSON, portfolioIdList);
						}else{
						    calculateExposureForSelectedPolygonWithScenario(selectedShape, performPopup, geodesyCircleGeoJSON, intersectionGeoJSON, selectedScenarioId, portfolioIdList);
						}
					}
					
					if (selectedShapeType === 'polygon') {
						var intersectionGeoJSON = turf.intersect(selectedShapeGeoJSON, scenarioLayerGeoJSON);
						
						if(intersectionGeoJSON === undefined){
							//Does not intersect
							calculateExposureForSelectedPolygon(selectedShape, performPopup, selectedShapeGeoJSON, portfolioIdList);
						}else{
						    calculateExposureForSelectedPolygonWithScenario(selectedShape, performPopup, selectedShapeGeoJSON, intersectionGeoJSON, selectedScenarioId, portfolioIdList);
						}
					}

				}
				function calculateExposureForSelectedPolygonWithScenario(selectedShape, performPopup, selectedShapeGeoJSON, intersectionGeoJSON, selectedScenarioId, portfolioIdList){
					
					var selectedShapeGeoJSONString = JSON.stringify(selectedShapeGeoJSON);
					var intersectionGeoJSONString = JSON.stringify(intersectionGeoJSON);
					var portfolioIdListString = portfolioIdList.toString();
					var isGroupByStateChecked = getSelectedOptionGroupedByState();
					
					var jsonRequest = '{\"selectedShape\":' + selectedShapeGeoJSONString + ',\"scenarioId\":\"' + selectedScenarioId + '\",\"selectedShapeScenarioIntersection\":'+ intersectionGeoJSONString + ',\"portfolioIds\":[' + portfolioIdListString +']'+ ',\"groupByState\":' + isGroupByStateChecked + '}';
						
						console.log("calculateExposureForSelectedPolygonWithScenario: " +  jsonRequest);

						$.ajax({
								url : "exposureForPolygonSelection",
								type : "post",
								data : jsonRequest,
								contentType : "application/json",
								dataType : "json",
							})
							.done(
									function(json) {
										drawingFeatureGroup.addLayer(selectedShape);
										selectedShape.bindPopup(createEposureSummaryHTML(json.portfolioExposureInfo, json.scenarioExposureInfo));
										if(performPopup === true){
										    selectedShape.openPopup();
										}
									})
							.fail(function(jqxhr, textStatus, error) {
								var err = textStatus + ", " + error;
								console.log("Request Failed: " + err);
							})
				}
				function calculateExposureForSelectedPolygon(selectedShape, performPopup, selectedShapeGeoJSON, portfolioIdList){
					
					var selectedShapeGeoJSONString = JSON.stringify(selectedShapeGeoJSON);
					var portfolioIdListString = portfolioIdList.toString();
					var isGroupByStateChecked = getSelectedOptionGroupedByState();
					
					var jsonRequest = '{\"selectedShape\":' + selectedShapeGeoJSONString + ',\"portfolioIds\":[' + portfolioIdListString +']'+ ',\"groupByState\":' + isGroupByStateChecked + '}';
						
					console.log("calculateExposureForSelectedPolygon: " +  jsonRequest);

						$.ajax({
								url : "poly",
								type : "post",
								data : jsonRequest,
								contentType : "application/json",
								dataType : "json",
							})
							.done(
									function(portfolioExposureInfoJSON) {
										drawingFeatureGroup.addLayer(selectedShape);
										selectedShape.bindPopup(createEposureSummaryHTML(portfolioExposureInfoJSON));
										if(performPopup === true){
										    selectedShape.openPopup();
										}
									})
							.fail(function(jqxhr, textStatus, error) {
								var err = textStatus + ", " + error;
								console.log("Request Failed: " + err);
							})
				}
				function calculateExposureForSelectedCircle(selectedShape, performPopup, selectedShapeGeoJSON, portfolioIdList){
					
					var selectedShapeGeoJSONString = JSON.stringify(selectedShapeGeoJSON);
					var portfolioIdListString = portfolioIdList.toString();
					var isGroupByStateChecked = getSelectedOptionGroupedByState();
					
					var jsonRequest = '{\"selectedShape\":' + selectedShapeGeoJSONString  + ',\"portfolioIds\":[' + portfolioIdListString +']'+ ',\"groupByState\":' + isGroupByStateChecked + '}';
						
					console.log("calculateExposureForSelectedCircle: " +  jsonRequest);

						$.ajax({
								url : "circle",
								type : "post",
								data : jsonRequest,
								contentType : "application/json",
								dataType : "json",
							})
							.done(
									function(portfolioExposureInfoJSON) {
										
										drawingFeatureGroup.addLayer(selectedShape);
										selectedShape.bindPopup(createEposureSummaryHTML(portfolioExposureInfoJSON));
										if(performPopup === true){
										    selectedShape.openPopup();
										}
									})
							.fail(function(jqxhr, textStatus, error) {
								var err = textStatus + ", " + error;
								console.log("Request Failed: " + err);
							})
				}
				function createMarkerSummaryHTML(feature){
				
					var popupString = '<table style=\"line-height:12px;\"><tr><td><b>Asset: '+ feature.properties.assetId + '</b></td><td></td></tr>'+
					                      '<tr><td><i>&nbsp;Portfolio</i></td><td>' + feature.properties.portfolioId +'</td></tr>' +
										  '<tr><td>&nbsp;Exposure</td><td>' + feature.properties.exposure +'</td></tr></table>';
					
					
					
					return popupString;
				}
				function createEposureSummaryHTML(portfolioExposureInfoJSON, scenarioExposureInfoJSON){
				
					var popupString = '<table cellpadding=\"2\" style=\"line-height:12px\"><tr><td><p style=\"color:#000099\"><b>Summary</b></p></td><td></td></tr>' +
					'<tr><td></td><td><b>Assets</b></td><td><b>Exposure</b></td><td><b>Respone</b></td></tr>' ;
					
					if(portfolioExposureInfoJSON !== undefined){
						popupString = popupString +
						'<tr><td><i>&nbsp;Portfolio</i></td><td>';
						if(portfolioExposureInfoJSON.groupbyExposureInfoList !== null){
							portfolioExposureInfoJSON.groupbyExposureInfoList.forEach(function(oneExposure){
							   popupString = popupString +
							   '<tr><td>&nbsp;&nbsp;' + oneExposure.title + '</td><td>' + oneExposure.total +'</td><td>'+ oneExposure.exposure+'</td></tr>';
								oneExposure
							});
								
						}
						 popupString = popupString +
						'<tr><td>&nbsp;&nbsp;Total</td><td>' + portfolioExposureInfoJSON.totalCount +'</td><td>'+ portfolioExposureInfoJSON.totalExposure+'</td><td>'+portfolioExposureInfoJSON.responseTime +' (ms)</td></tr>';
						
					}
					popupString = popupString + '<tr><td> </td></tr>'; 
					
					if(scenarioExposureInfoJSON !== undefined){
						popupString = popupString +
						'<tr><td><i>&nbsp;Scenario</i></td><td>';
						if(scenarioExposureInfoJSON.groupbyExposureInfoList !== null){
							scenarioExposureInfoJSON.groupbyExposureInfoList.forEach(function(oneExposure){
							   popupString = popupString +
							   '<tr><td>&nbsp;&nbsp;' + oneExposure.title + '</td><td>' + oneExposure.total +'</td><td>'+ oneExposure.exposure+'</td></tr>';
								oneExposure
							});
						}
						popupString = popupString +
						'<tr><td>&nbsp;&nbsp;Total</td><td>' + scenarioExposureInfoJSON.totalCount +'</td><td>'+ scenarioExposureInfoJSON.totalExposure+'</td><td>'+scenarioExposureInfoJSON.responseTime +' (ms)</td></tr>';
						
					}
					
					return popupString;
				}
				function recalculateDrawnPolygons() {
					drawingFeatureGroup.eachLayer(function (oneLayer) {
    					calculateAreaForShape(oneLayer);
					});
				}
				function deleteAllDrawnPolygons() {
					drawingFeatureGroup.eachLayer(function (oneLayer) {
    					drawingFeatureGroup.removeLayer(oneLayer);
					});
				}
				function deletePolygonArea(e) {
					/* Place holder
					var layers = e.layers;
					layers.eachLayer(function(layer) {
						console.log("Delete Feature: " + layer._leaflet_id);
					});*/
				}
				function getShapeType(layer) {

					if (layer instanceof L.Circle) {
						return 'circle';
					}

					if (layer instanceof L.Marker) {
						return 'marker';
					}

					if (layer instanceof L.Rectangle) {
						return 'rectangle';
					}

					if (layer instanceof L.Polygon) {
						return 'polygon';
					}

					if (layer instanceof L.Polyline) {
						return 'polyline';
					}
				};
				function updateGroupByState(){
					recalculateDrawnPolygons();
				}
				function updateMapWithScenario() {
					var selectedScenario = getSelectedScenario();
					
					$.ajax({
						url : "scenario",
						type : "get",
						data : {
							scenarioId : selectedScenario
						},
						contentType : "application/json",
						dataType : "json",
					}).done(function(selectedScenarioJSON) {
						scenarioFeatureGroup.clearLayers();
						var scenarioFeatureLayer = L.mapbox.featureLayer(selectedScenarioJSON);
						
						scenarioFeatureGroup.addLayer(scenarioFeatureLayer);
						scenarioFeatureLayer.bringToBack();
						updateEposureForScenario();
					}).fail(function(jqxhr, textStatus, error) {
						var err = textStatus + ", " + error;
						console.log("Request Failed: " + err);
					})
					
				}
				function updateExposureForPortfolio() {
					var portfolioList = getSelectedPortfolios();

					$.ajax({
						url : "exposureForPortfolios",
						type : "get",
						data : {
							portfolioIds : portfolioList.toString()
						},
						contentType : "application/json",
						dataType : "json",
					}).done(
							function(json) {

								var assetLabel = document.getElementById('TotalAssets');
								var exposureLabel = document.getElementById('TotalExposure');

								assetLabel.value = json.totalCount;
								exposureLabel.value = json.totalExposure;

							}).fail(function(jqxhr, textStatus, error) {
						var err = textStatus + ", " + error;
						console.log("Request Failed: " + err);
					})
				}
				function updateEposureForScenario() {
					var portfolioList = getSelectedPortfolios();
					var scenarioIdValue = getSelectedScenario();
					
					$.ajax({
						url : "exposureForScenario",
						type : "get",
						data : {
							scenarioId : scenarioIdValue,
							portfolioIds : portfolioList.toString()
						},
						contentType : "application/json",
						dataType : "json",
					}).done(
							function(json) {

								var assetLabel = document.getElementById('ScenarioAssets');
								var exposureLabel = document.getElementById('ScenarioExposure');

								assetLabel.value = json.totalCount;
								exposureLabel.value = json.totalExposure;

							}).fail(function(jqxhr, textStatus, error) {
						var err = textStatus + ", " + error;
						console.log("Request Failed: " + err);
					})
					recalculateDrawnPolygons();
				}
				function updateAssetsOnMap() {
					var portfolioList = getSelectedPortfolios();

					$.ajax({
						url : "markers",
						type : "get",
						data : {
							portfolioIds : portfolioList.toString()
						},
						contentType : "application/json",
						dataType : "json",
					}).done(function(json) {
					
						markerFeatureGroup.clearLayers();
						var clusterGroup = new L.MarkerClusterGroup();
						var geojson = L.geoJson(json, {
							onEachFeature : function(feature, layer) {
								layer.setIcon(L.mapbox.marker.icon({
									'marker-color' : '#CC0000'
								}));
								layer.bindPopup(createMarkerSummaryHTML(feature));
								clusterGroup.addLayer(layer);
							}
						})
						markerFeatureGroup.addLayer(clusterGroup);
					}).fail(function(jqxhr, textStatus, error) {
						var err = textStatus + ", " + error;
						console.log("Request Failed: " + err);
					})
					var selectedScenario = getSelectedScenario();
					updateExposureForPortfolio();
					updateEposureForScenario(selectedScenario);

				}
				function getSelectedOptionGroupedByState() {
					return document.getElementById('groupByStateCheckbox').checked;
				}
				function getSelectedPortfolios(layer) {
					var portfolioList = [];

					var portfolioCheckBoxs = document
							.getElementsByName('portfolioCheckBox');
					for (var i = 0, length = portfolioCheckBoxs.length; i < length; i++) {
						if (portfolioCheckBoxs[i].checked) {
							portfolioList.push(portfolioCheckBoxs[i].value);
						}
					}
					return portfolioList;
				}
				function getSelectedScenario() {
					var scenarioRadioButtons = document
							.getElementsByName('scenarioRadioButton');
					var selectedScenario = '';

					for (var i = 0, length = scenarioRadioButtons.length; i < length; i++) {
						if (scenarioRadioButtons[i].checked) {
							selectedScenario = scenarioRadioButtons[i].value;

							// only one radio can be logically checked, don't check the rest
							break;
						}
					}
					return selectedScenario;
				}
			</script>
</body>
</html>
