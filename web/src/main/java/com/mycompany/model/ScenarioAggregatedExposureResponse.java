package com.mycompany.model;

public class ScenarioAggregatedExposureResponse {
	AggregateExposureResponse portfolioExposureInfo;
	AggregateExposureResponse scenarioExposureInfo;
	
	public AggregateExposureResponse getPortfolioExposureInfo() {
		return portfolioExposureInfo;
	}
	public void setPortfolioExposureInfo(AggregateExposureResponse portfolioExposureInfo) {
		this.portfolioExposureInfo = portfolioExposureInfo;
	}
	public AggregateExposureResponse getScenarioExposureInfo() {
		return scenarioExposureInfo;
	}
	public void setScenarioExposureInfo(AggregateExposureResponse scenarioExposureInfo) {
		this.scenarioExposureInfo = scenarioExposureInfo;
	}
	
	
}
