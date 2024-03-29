SELECT m0.company_id,
	   m0.company_name,
	   (SELECT CAST(count(f.*) AS int4)
	    FROM   flight f
               INNER JOIN airport AD ON f.airport_departure_id = AD.airport_id
               INNER JOIN city CD ON AD.city_id = CD.city_id
               INNER JOIN airport AA ON f.airport_arrival_id = AA.airport_id
               INNER JOIN city CA ON AA.city_id = CA.city_id
        WHERE f.company_id = m0.company_id
              /*AIRPORT_DEPARTURE_PLACEHOLDER*/
              /*AIRPORT_ARRIVAL_PLACEHOLDER*/
              /*CITY_DEPARTURE_PLACEHOLDER*/
              /*CITY_ARRIVAL_PLACEHOLDER*/
              /*DATERANGE_ARRIVAL_PLACEHOLDER*/
              /*DATERANGE_DEPARTURE_PLACEHOLDER*/
  ) as flight_count,
	   CAST((
	   	   SELECT 100.0 * count(*) / (SELECT count(*) FROM flight f2 WHERE f2.company_id = m0.company_id)
		   FROM   flight f
		          INNER JOIN airport AD ON f.airport_departure_id = AD.airport_id
                  INNER JOIN city CD ON AD.city_id = CD.city_id
                  INNER JOIN airport AA ON f.airport_arrival_id = AA.airport_id
                  INNER JOIN city CA ON AA.city_id = CA.city_id
		   WHERE  ABS(EXTRACT(MINUTE FROM f.flight_plandeparture - f.flight_factdeparture)) < 15
		   	 AND  f.company_id = m0.company_id
		   	 /*AIRPORT_DEPARTURE_PLACEHOLDER*/
		   	 /*AIRPORT_ARRIVAL_PLACEHOLDER*/
		   	 /*CITY_DEPARTURE_PLACEHOLDER*/
             /*CITY_ARRIVAL_PLACEHOLDER*/
             /*DATERANGE_ARRIVAL_PLACEHOLDER*/
             /*DATERANGE_DEPARTURE_PLACEHOLDER*/
	   ) as float8) rating_departure,
       CAST((
           SELECT 100.0 * count(*) / (SELECT count(*) FROM flight f2 WHERE f2.company_id = m0.company_id)
           FROM   flight f
                  INNER JOIN airport AD ON f.airport_departure_id = AD.airport_id
                  INNER JOIN city CD ON AD.city_id = CD.city_id
                  INNER JOIN airport AA ON f.airport_arrival_id = AA.airport_id
                  INNER JOIN city CA ON AA.city_id = CA.city_id
           WHERE  ABS(EXTRACT(MINUTE FROM f.flight_planarrival - f.flight_factarrival)) < 15
             AND  f.company_id = m0.company_id
           /*AIRPORT_DEPARTURE_PLACEHOLDER*/
           /*AIRPORT_ARRIVAL_PLACEHOLDER*/
           /*CITY_DEPARTURE_PLACEHOLDER*/
           /*CITY_ARRIVAL_PLACEHOLDER*/
           /*DATERANGE_ARRIVAL_PLACEHOLDER*/
           /*DATERANGE_DEPARTURE_PLACEHOLDER*/
       ) as float8) rating_arrival
FROM   company m0
WHERE 1=1
GROUP BY m0.company_id
/*ORDERBY_PLACEHOLDER*/
/*LIMIT_PLACEHOLDER*/