SELECT m0.*,
       AD.airport_id AS airport_departure_id,
       AD.airport_name AS airport_departure_name,
       CD.city_id AS city_departure_id,
       CD.city_name AS city_departure_name,
       AA.airport_id AS airport_arrival_id,
       AA.airport_name AS airport_arrival_name,
       CD.city_id AS city_arrival_id,
       CD.city_name AS city_arrival_name,
       CO.company_name
FROM   flight m0
       INNER JOIN company CO ON m0.company_id = CO.company_id
       INNER JOIN airport AD ON m0.airport_departure_id = AD.airport_id
       INNER JOIN city CD ON AD.city_id = CD.city_id
       INNER JOIN airport AA ON m0.airport_arrival_id = AA.airport_id
       INNER JOIN city CA ON AA.city_id = CA.city_id
WHERE  1=1
  /*WHERE_PLACEHOLDER*/
  /*COMPANY_PLACEHOLDER*/
  /*AIRPORT_DEPARTURE_PLACEHOLDER*/
  /*CITY_DEPARTURE_PLACEHOLDER*/
  /*AIRPORT_ARRIVAL_PLACEHOLDER*/
  /*CITY_ARRIVAL_PLACEHOLDER*/
/*ORDERBY_PLACEHOLDER*/
/*LIMIT_PLACEHOLDER*/