package com.neo.admin.system.modular.lbs.service;

import org.springframework.stereotype.Service;

@Service
public class MongoStationService {/*

	@Autowired
	private MongoTemplate mongoTemplate;
	private static Logger logger = LoggerFactory.getLogger(MongoStationService.class);
	// private Logger logger = Logger.getLogger(MongoStationService.class);

	*//**
	 * 聚合查询，查询一个点附近的点，并返回每一个点到该中心点的距离，在坐标表分片的情况下$nearSphere不支持， 可以使用该方法进行查询
	 * 
	 * @param collection
	 *            集合名称
	 * @param query
	 *            查询条件
	 * @param point
	 *            中心点坐标
	 * @param limit
	 *            返回记录数量限制
	 * @param maxDistance
	 *            最大距离
	 * @return 非NULL的list
	 *//*
	public List<DBObject> geoNear(String collection,double [] loc, int limit, long maxDistance) {

		List<DBObject> pipeLine = new ArrayList<>();
		BasicDBObject aggregate = new BasicDBObject("$geoNear",
				new BasicDBObject("near",
						new BasicDBObject("type", "Point").append("coordinates",
								loc)).append("distanceField", "dist.calculated")
										.append("query", new BasicDBObject()).append("num", limit)
										.append("maxDistance", maxDistance).append("spherical", true));
		pipeLine.add(aggregate);
		Cursor cursor = mongoTemplate.getCollection(collection).aggregate(pipeLine,
				AggregationOptions.builder().build());
		List<DBObject> list = new LinkedList<>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public DBObject findOne(String collection, DBObject query, DBObject fields) {
		return mongoTemplate.getCollection(collection).findOne(query, fields);
	}

	public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum,
			int pageSize) {
		List<DBObject> list = new ArrayList<>();
		Cursor cursor = mongoTemplate.getCollection(collection).find(query, fields).skip((pageNum - 1) * pageSize)
				.limit(pageSize).sort(orderBy);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list.size() > 0 ? list : null;
	}

	public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int limit) {
		List<DBObject> list = new ArrayList<>();
		Cursor cursor = mongoTemplate.getCollection(collection).find(query, fields).sort(orderBy).limit(limit);
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list.size() > 0 ? list : null;
	}

	public void delete(String collection, DBObject dbObject) {
		mongoTemplate.getCollection(collection).remove(dbObject);
	}

	public void save(String collection, DBObject dbObject) {
		mongoTemplate.getCollection(collection).save(dbObject);
	}

	public void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi) {
		mongoTemplate.getCollection(collection).update(query, update, upsert, multi);
	}

	public Long count(String collection, DBObject query) {
		return mongoTemplate.getCollection(collection).count(query);
	}

	public List<?> distinct(String collection, String key, DBObject query) {
		return mongoTemplate.getCollection(collection).distinct(key, query);
	}

	public List<DBObject> geoNear(String collection, DBObject query, double[] point, int limit, long maxDistance) {
		if (query == null)
			query = new BasicDBObject();

		List<DBObject> pipeLine = new ArrayList<>();
		BasicDBObject aggregate = new BasicDBObject("$geoNear",
				new BasicDBObject("near",
						new BasicDBObject("type", "Point").append("coordinates",
								new double[] { 118.783799, 31.979234 })).append("distanceField", "dist.calculated")
										.append("query", new BasicDBObject()).append("num", 5)
										.append("maxDistance", 5000).append("spherical", true));
		pipeLine.add(aggregate);
		Cursor cursor = mongoTemplate.getCollection(collection).aggregate(pipeLine,
				AggregationOptions.builder().build());
		List<DBObject> list = new LinkedList<>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public List<DBObject> withinCircle(String collection, String locationField, double[] center, long radius,
			DBObject fields, DBObject query, int limit) {
		LinkedList<Object> circle = new LinkedList<>();
		// Set the center coordinate
		circle.addLast(center);
		// Set the radius. unit:meter
		circle.addLast(radius / 6378137.0);

		if (query == null)
			query = new BasicDBObject();
		query.put(locationField, new BasicDBObject("$geoWithin", new BasicDBObject("$centerSphere", circle)));
		logger.info("withinCircle:{}", query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}

	public List<DBObject> nearSphere(String collection, String locationField, double[] center, long minDistance,
			long maxDistance, DBObject query, DBObject fields, int limit) {
		if (query == null)
			query = new BasicDBObject();

		query.put(locationField,
				new BasicDBObject("$nearSphere",
						new BasicDBObject("$geometry", new BasicDBObject("type", "Point").append("coordinates", center))
								.append("$minDistance", minDistance).append("$maxDistance", maxDistance)));
		logger.info("nearSphere:{}", query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}

	public List<DBObject> withinPolygon(String collection, String locationField, List<double[]> polygon,
			DBObject fields, DBObject query, int limit) {
		if (query == null)
			query = new BasicDBObject();

		List<List<double[]>> polygons = new LinkedList<>();
		polygons.add(polygon);
		query.put(locationField, new BasicDBObject("$geoWithin",
				new BasicDBObject("$geometry", new BasicDBObject("type", "Polygon").append("coordinates", polygons))));
		logger.info("withinPolygon:{}", query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}

	public List<DBObject> withinMultiPolygon(String collection, String locationField, List<List<double[]>> polygons,
			DBObject fields, DBObject query, int limit) {
		if (query == null)
			query = new BasicDBObject();

		List<List<List<double[]>>> list = new LinkedList<>();
		for (List<double[]> polygon : polygons) {
			List<List<double[]>> temp = new LinkedList<>();
			temp.add(polygon);
			list.add(temp);
		}
		query.put(locationField, new BasicDBObject("$geoWithin",
				new BasicDBObject("$geometry", new BasicDBObject("type", "MultiPolygon").append("coordinates", list))));
		logger.info("withinMultiPolygon:{}", query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}

	public List<DBObject> withinBox(String collection, String locationField, double[] bottomLeft, double[] upperRight,
			DBObject fields, DBObject query, int limit) {
		if (query == null)
			query = new BasicDBObject();

		LinkedList<double[]> box = new LinkedList<>();
		box.add(bottomLeft);
		box.add(upperRight);

		query.put(locationField, new BasicDBObject("$geoWithin", new BasicDBObject("$box", box)));
		logger.info("withinBox:{}", query.toString());
		return mongoTemplate.getCollection(collection).find(query, fields).limit(limit).toArray();
	}

*/}
