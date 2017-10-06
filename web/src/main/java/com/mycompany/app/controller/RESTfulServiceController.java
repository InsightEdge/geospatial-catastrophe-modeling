
package com.mycompany.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import org.openspaces.spatial.shapes.Circle;
import org.openspaces.spatial.shapes.Polygon;

import com.mycompany.app.service.AssetService;
import com.mycompany.app.utils.MapJSTools;
import com.mycompany.model.SelectedPolygonRequest;
import com.mycompany.model.SelectedCircleRequest;
import com.mycompany.model.Asset;
import com.mycompany.model.AggregateExposureResponse;
import com.mycompany.model.ScenarioAggregatedExposureResponse;
import com.mycompany.model.leafletjs.FeatureCollection;
import com.mycompany.model.leafletjs.MapBoxPolygonLayer;

@Controller
public class RESTfulServiceController {

	@Autowired
	private AssetService assetService;

	private static final Logger logger = Logger.getLogger(RESTfulServiceController.class.getName());

	@RequestMapping(value = "/exposureForScenario", method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ResponseBody
	public AggregateExposureResponse updateScenarioExposure(@RequestParam String scenarioId,
			@RequestParam Integer[] portfolioIds) {
		logger.log(Level.INFO, "Servlet updateScenarioExposure:" + scenarioId + " portfolioIds:"+ Arrays.toString(portfolioIds));

		return assetService.retrieveScenarioExposure(scenarioId, Arrays.asList(portfolioIds));
	}

	@RequestMapping(value = "/exposureForPortfolios", method = RequestMethod.GET, headers = {
			"Accept=application/json" })
	@ResponseBody
	public AggregateExposureResponse updatePortfolioExposure(@RequestParam Integer[] portfolioIds) {
		logger.log(Level.INFO, "Servlet updatePortfolioExposure:" + " portfolioIds:"+ Arrays.toString(portfolioIds));

		return assetService.retrievePortfolioExposure(Arrays.asList(portfolioIds));
	}

	@RequestMapping(value = "/scenario", method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ResponseBody
	public MapBoxPolygonLayer updateScenario(@RequestParam String scenarioId) {
		logger.log(Level.INFO, "Servlet updateScenario:" + scenarioId);

		Polygon scenarioShape = assetService.retrieveScenarioShape(scenarioId);
		
		return MapJSTools.convertGigaspacesPolygonToLeafletPolygonLayer(scenarioShape);
	}

	@RequestMapping(value = "/markers", method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ResponseBody
	public FeatureCollection retrieveMarkers(@RequestParam Integer[] portfolioIds) {
		logger.log(Level.INFO, "Servlet retrieveMarkers" + " portfolioIds:"+ Arrays.toString(portfolioIds));

		Asset[] assets = assetService.retrieveAssetsFromPortfolios(Arrays.asList(portfolioIds));

		
		return MapJSTools.convertAssetsToMarkers(assets);
	}

	@RequestMapping(value = "/poly", method = RequestMethod.POST, headers = { "Accept=application/json" })
	@ResponseBody
	public AggregateExposureResponse exposureForScenarioGivenPolygon(
			@RequestBody SelectedPolygonRequest intersectRequest) {
	
		logger.log(Level.INFO, "Servlet exposureForScenarioGivenPolygon");

		Polygon polygon = MapJSTools.convertLeafletPolygonToGigaspacesPolygon(intersectRequest.getSelectedShape());

		List<Integer> portfolioIds = intersectRequest.getPortfolioIds();
		
		
		Boolean groupByState = intersectRequest.getGroupByState();
		
		logger.log(Level.INFO, "Servlet exposureForScenarioGivenPolygon" + " portfolioIds:" + Arrays.toString(portfolioIds.toArray()) +" groupByState:"+groupByState);

		return assetService.aggregateExposureForScenarioGivenShape(polygon, portfolioIds, groupByState);
	}

	@RequestMapping(value = "/circle", method = RequestMethod.POST, headers = { "Accept=application/json" })
	@ResponseBody
	public AggregateExposureResponse exposureForScenarioUsingCircle(
			@RequestBody SelectedCircleRequest intersectRequest) {
		logger.log(Level.INFO, "Servlet exposureForScenarioUsingCircle");

		Circle circle = MapJSTools.convertLeafletCircleToGigaspacesCircle(intersectRequest.getSelectedShape());

		List<Integer> portfolioIds = intersectRequest.getPortfolioIds();

		Boolean groupByState = intersectRequest.getGroupByState();

		logger.log(Level.INFO, "Servlet exposureForScenarioUsingCircle" + " portfolioIds:" + Arrays.toString(portfolioIds.toArray()) +" groupByState:"+groupByState);

		return assetService.aggregateExposureForScenarioGivenShape(circle, portfolioIds, groupByState);
	}

	@RequestMapping(value = "/exposureForPolygonSelection", method = RequestMethod.POST, headers = {
			"Accept=application/json" })
	@ResponseBody
	public ScenarioAggregatedExposureResponse aggregateExposureForScenarioGivenPolygonIntersection(
			@RequestBody SelectedPolygonRequest intersectRequest) {
		logger.log(Level.INFO, "Servlet aggregateExposureForScenarioGivenIntersection " + intersectRequest);

		Polygon selectedShape = MapJSTools
				.convertLeafletPolygonToGigaspacesPolygon(intersectRequest.getSelectedShape());

		Polygon scenarioIntersection = MapJSTools
				.convertLeafletPolygonToGigaspacesPolygon(intersectRequest.getSelectedShapeScenarioIntersection());

		List<Integer> portfolioIds = intersectRequest.getPortfolioIds();

		Boolean groupByState = intersectRequest.getGroupByState();

		logger.log(Level.INFO, "Servlet aggregateExposureForScenarioGivenIntersection" + " portfolioIds:" + Arrays.toString(portfolioIds.toArray()) +" groupByState:"+groupByState);

		return assetService.aggregateExposureForSelectedShapeInScenario(selectedShape, scenarioIntersection,
				portfolioIds, groupByState);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexScenario() {
		logger.log(Level.INFO, "Servlet indexScenario");
		ModelAndView model = new ModelAndView("index");
		return model;
	}

}
