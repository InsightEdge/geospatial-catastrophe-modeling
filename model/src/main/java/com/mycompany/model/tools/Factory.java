package com.mycompany.model.tools;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openspaces.spatial.ShapeFactory;
import org.openspaces.spatial.shapes.Point;
import com.mycompany.model.Asset;

public class Factory {

	private Factory(){}
	
	public static Object createObject() {
		return new Asset();
	}

	public static Object[] createMultipleObjects(Integer count, Integer startingValue) throws ParseException {

		Integer currentCount = startingValue;
		List<Asset> buffer = new ArrayList<Asset>();
		for (int i = 0; i < count; i++) {
			// New York
			// Latitude: 40.7142700
			// Longitude: -74.0059700
			// Remember that x is longitude (East/West), and y is latitude
			// (North/South)

			// String connecticut =
			// "\"coordinates\":[[[-73.053528,42.039048],[-71.799309,42.022617],[-71.799309,42.006186],[-71.799309,41.414677],[-71.859555,41.321569],[-71.947186,41.338],[-72.385341,41.261322],[-72.905651,41.28323],[-73.130205,41.146307],[-73.371191,41.102491],[-73.655992,40.987475],[-73.727192,41.102491],[-73.48073,41.21203],[-73.55193,41.294184],[-73.486206,42.050002],[-73.053528,42.039048]]]}}";
			// String delaware =
			// "\"coordinates\":[[[-75.414089,39.804456],[-75.507197,39.683964],[-75.611259,39.61824],[-75.589352,39.459409],[-75.441474,39.311532],[-75.403136,39.065069],[-75.189535,38.807653],[-75.09095,38.796699],[-75.047134,38.451652],[-75.693413,38.462606],[-75.786521,39.722302],[-75.616736,39.831841],[-75.414089,39.804456]]]}}";

			// "Polygon((-10 30, -40 40, -10 -20, 40 20, 0 0, -10 30))";

			Point onePoint = createRandomPointWithinBounds(-75.0, 41.0, 6);
			String stateName = PointInPolygonHelper.getInstance().getBoundingStateForCoordinate(onePoint.getX(),
					onePoint.getY());

			Long exposure = createRandomAppraisal(10000, 100000);
			Long premium = exposure / 5;
			Integer portfolioId = createRandomPortfolio(10000, 10003);

			buffer.add(new Asset(currentCount, portfolioId, stateName, onePoint, exposure, premium));
			currentCount++;
		}
		return buffer.toArray(new Asset[buffer.size()]);
	}

	private static Point createRandomPointWithinBounds(double xLat, double yLong, double radius) throws ParseException {
		ThreadLocalRandom random = ThreadLocalRandom.current();

		double x = random.nextDouble(xLat - radius, xLat + radius);
		double y = random.nextDouble(yLong - radius, yLong + radius);

		if (PointInPolygonHelper.getInstance().isPointInPolygon(x, y)) {
			return ShapeFactory.point(x, y);
		} else {
			return createRandomPointWithinBounds(xLat, yLong, radius);
		}
	}

	private static long createRandomAppraisal(long lower, long upper) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextLong(lower, upper);
	}

	private static int createRandomPortfolio(int lower, int upper) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(lower, upper);
	}
}
