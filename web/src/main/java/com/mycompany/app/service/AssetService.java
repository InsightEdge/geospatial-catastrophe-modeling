package com.mycompany.app.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openspaces.spatial.shapes.Polygon;
import org.openspaces.spatial.shapes.Shape;
import com.mycompany.app.dao.AssetDAO;
import com.mycompany.model.GISScenario;
import com.mycompany.model.ScenarioAggregatedExposureResponse;
import com.mycompany.model.Asset;
import com.mycompany.model.AggregateExposureResponse;

@Component
public class AssetService {
	
	@Autowired
	private AssetDAO assetDAO;

	private static final Logger logger = Logger.getLogger(AssetService.class.getName());

	public AggregateExposureResponse retrievePortfolioExposure(List<Integer> portfolioIds){
		
		return assetDAO.aggregateExposureInPortfolio(portfolioIds);
	}
	public AggregateExposureResponse retrieveScenarioExposure(String scenarioId, List<Integer> portfolioIds){
		GISScenario scenario = assetDAO.retrieveScenarioProperties(scenarioId);
		
		if(scenario != null){
			return assetDAO.aggregateExposureInShape(scenario.getGeometry(), portfolioIds);
		}else{
			AggregateExposureResponse exposure = new AggregateExposureResponse();
			exposure.setTotalCount(0L);
			exposure.setTotalExposure(0L);
			return exposure;
		}
	}
	public Polygon retrieveScenarioShape(String scenarioId){
		
		GISScenario scenario = assetDAO.retrieveScenarioProperties(scenarioId);
		
		if(scenario != null){
			return scenario.getGeometry();
		}else{
			return  null;
		}
		
	}
	public Asset[] retrieveAssetsFromPortfolios(List<Integer> portfolioIds){
		return assetDAO.retrieveMarkers(portfolioIds);
	}

	public AggregateExposureResponse aggregateExposureForScenarioGivenShape(Shape shape, List<Integer> portfolioIds, Boolean groupByState) {
		
		if(groupByState == true){
			return assetDAO.aggregateExposureInShapeGroupedByState(shape, portfolioIds);
		}else{
			return assetDAO.aggregateExposureInShape(shape, portfolioIds);
		}
	}
	
	public ScenarioAggregatedExposureResponse aggregateExposureForSelectedShapeInScenario(Shape selectedShape, Polygon scenarioPolygon, List<Integer> portfolioIds, Boolean groupByState) {
		
		logger.log(Level.INFO, "Circle:" + selectedShape + ", scenarioShape:" + scenarioPolygon + ", portfolioIds:" + portfolioIds);
		
		Future<AggregateExposureResponse> portfolioResult; 
		Future<AggregateExposureResponse> scenarioResult; 
		
		if(groupByState == true){
			logger.log(Level.INFO, "Circle:" + selectedShape + ", scenarioShape:" + scenarioPolygon + ", portfolioIds:" + portfolioIds);
			
			portfolioResult = assetDAO.asyncAggregateExposureInShapeGroupedByState(selectedShape, portfolioIds);
			scenarioResult = assetDAO.asyncAggregateExposureInShapeGroupedByState(scenarioPolygon, portfolioIds);
		}else{
			portfolioResult = assetDAO.asyncAggregateExposureInShape(selectedShape, portfolioIds);
			scenarioResult = assetDAO.asyncAggregateExposureInShape(scenarioPolygon, portfolioIds);
		}
		AggregateExposureResponse portfolioExposure = null;
		AggregateExposureResponse scenarioExposure = null;
		
		try{
			 portfolioExposure = portfolioResult.get();
			 scenarioExposure = scenarioResult.get();
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error getting exposure:", ex);
		}
		
		ScenarioAggregatedExposureResponse totalExposure = new ScenarioAggregatedExposureResponse();
		totalExposure.setPortfolioExposureInfo(portfolioExposure);
		totalExposure.setScenarioExposureInfo(scenarioExposure);
		
		return totalExposure;
	}
}
