SELECT m0.company_id,
	   m0.company_name,
	   CAST((
	   	   SELECT 100 - 100.0 * count(*) / (SELECT count(*) FROM flight f2 WHERE f2.company_id = m0.company_id)
		   FROM   flight f
		   WHERE  ABS(EXTRACT(MINUTE FROM f.flight_plandeparture - f.flight_factdeparture)) > 15
		   	 AND  f.company_id = m0.company_id
	   ) as float8) rating_departure,
       CAST((
           SELECT 100 - 100.0 * count(*) / (SELECT count(*) FROM flight f2 WHERE f2.company_id = m0.company_id)
           FROM   flight f
           WHERE  ABS(EXTRACT(MINUTE FROM f.flight_planarrival - f.flight_factarrival)) > 15
             AND  f.company_id = m0.company_id
       ) as float8) rating_arrival
FROM   company m0
/*ORDERBY_PLACEHOLDER*/
/*LIMIT_PLACEHOLDER*/