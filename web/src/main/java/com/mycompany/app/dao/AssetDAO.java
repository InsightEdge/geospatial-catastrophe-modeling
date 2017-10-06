package com.mycompany.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.stereotype.Component;

import com.gigaspaces.query.aggregators.AggregationResult;
import com.gigaspaces.query.aggregators.AggregationSet;
import com.gigaspaces.query.aggregators.GroupByAggregator;
import com.gigaspaces.query.aggregators.GroupByResult;
import com.gigaspaces.query.aggregators.GroupByValue;
import org.openspaces.spatial.shapes.Shape;
import com.j_spaces.core.client.SQLQuery;
import com.mycompany.model.GISScenario;
import com.mycompany.model.Asset;
import com.mycompany.model.AggregateExposureInfo;
import com.mycompany.model.AggregateExposureResponse;

import static org.openspaces.extensions.QueryExtension.groupBy;

@Component
public class AssetDAO {

	@GigaSpaceContext(name = "gigaSpace")
	private GigaSpace gigaSpace;

	private static final ExecutorService pool = Executors.newFixedThreadPool(30);

	private static final Logger logger = Logger.getLogger(AssetDAO.class.getName());

	public AggregateExposureResponse aggregateExposureInShape(Shape shape, List<Integer> portfolioIds) {

		SQLQuery<Asset> query = new SQLQuery<Asset>(Asset.class, "location spatial:within ? AND portfolioId IN (?)");
		query.setParameters(shape, portfolioIds);

		return aggregatePortfolio(query);
	}

	public AggregateExposureResponse aggregateExposureInShapeGroupedByState(Shape shape, List<Integer> portfolioIds) {

		SQLQuery<Asset> query = new SQLQuery<Asset>(Asset.class, "location spatial:within ? AND portfolioId IN (?)");
		query.setParameters(shape, portfolioIds);
		
		GroupByAggregator groupByAggregator = new GroupByAggregator().selectCount().selectSum("exposure").groupBy("state");
		
		Long startTime = new Date().getTime();
		GroupByResult groupByResult = groupBy(gigaSpace, query, groupByAggregator);
		Long endTime = new Date().getTime() - startTime;

		return populateDTOForResponse(groupByResult, endTime);
	}
	public AggregateExposureResponse populateDTOForResponse(GroupByResult groupByResult, Long responseTime){
		AggregateExposureResponse response = new AggregateExposureResponse();
		List<AggregateExposureInfo> exposureList = new ArrayList<AggregateExposureInfo>();
		Long totalCount = 0L;
		Long totalExposure = 0L;

		for (GroupByValue group : groupByResult) {
			AggregateExposureInfo oneExposure = new AggregateExposureInfo();
			Long total = (Long) group.get("count(*)");
			Long exposure = (Long) group.get("sum(exposure)");

			totalCount += total;
			totalExposure += exposure;

			oneExposure.setExposure(exposure);
			oneExposure.setTotal(total);
			
			String key = (String) group.getKey().get("state");
			oneExposure.setTitle(key);

			exposureList.add(oneExposure);
			
			logger.log(Level.INFO, "One Group Exposure Result - count:"+ total +" exposure:" + exposure + " key:" + key );
			
		}
		response.setGroupbyExposureInfoList(exposureList);
		response.setTotalCount(totalCount);
		response.setTotalExposure(totalExposure);
		response.setResponseTime(responseTime);
		return response;
	}

	public AggregateExposureResponse aggregateExposureInPortfolio(List<Integer> portfolioIds) {

		SQLQuery<Asset> query = new SQLQuery<Asset>(Asset.class, "portfolioId IN (?)");
		query.setParameters(portfolioIds);

		return aggregatePortfolio(query);
	}

	public AggregateExposureResponse aggregatePortfolio(SQLQuery<Asset> query) {

		logger.log(Level.INFO, "Aggregate:" + query.toString());
		AggregationSet aggregationSet = new AggregationSet().count().sum("exposure");
		AggregateExposureResponse total = new AggregateExposureResponse();

		try {
			long start = new Date().getTime();
			AggregationResult result = gigaSpace.aggregate(query, aggregationSet);
			long endTime = new Date().getTime() - start;

			Long resultCount = (Long) result.get("count(*)");
			Long resultExposure = (Long) result.get("sum(exposure)");
			
			total.setTotalCount(resultCount);
			total.setTotalExposure((Long) result.get("sum(exposure)"));
			total.setResponseTime(endTime);
			
			logger.log(Level.INFO, "Exposure Result - count:"+ resultCount +" exposure:" + resultExposure + " endTime:" + endTime );
			
			return total;

		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Aggregation Failed: " + query.getQuery(), ex);
		}

		total.setTotalCount(0L);
		total.setTotalExposure(0L);
		total.setResponseTime(null);

		return total;
	}

	public Asset[] retrieveMarkers(List<Integer> portfolioIds) {
		SQLQuery<Asset> query = new SQLQuery<Asset>(Asset.class, "portfolioId IN (?)");
		query.setParameters(portfolioIds);

		// TODO:For frontend client display. This May need to change if number of assets in portfolio grows
		Asset[] assets = gigaSpace.readMultiple(query);
		logger.log(Level.INFO, "Total markers: " + assets.length);
		return assets;
	}

	public GISScenario retrieveScenarioProperties(String scenarioId) {
		return gigaSpace.readById(GISScenario.class, scenarioId);
	}

	public Future<AggregateExposureResponse> asyncAggregateExposureInShape(final Shape shape,
			final List<Integer> portfolioIds) {
		return pool.submit(new Callable<AggregateExposureResponse>() {
			@Override
			public AggregateExposureResponse call() throws Exception {
				return aggregateExposureInShape(shape, portfolioIds);
			}
		});
	}

	public Future<AggregateExposureResponse> asyncAggregateExposureInShapeGroupedByState(final Shape shape,
			final List<Integer> portfolioIds) {
		return pool.submit(new Callable<AggregateExposureResponse>() {
			@Override
			public AggregateExposureResponse call() throws Exception {
				return aggregateExposureInShapeGroupedByState(shape, portfolioIds);
			}
		});
	}

}
