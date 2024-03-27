SELECT m0.*,
       C.city_id,
       C.city_name
FROM   airport m0
       INNER JOIN city C ON m0.city_id = C.city_id
WHERE  1=1
  /*WHERE_PLACEHOLDER*/
  /*CITY_PLACEHOLDER*/
/*ORDERBY_PLACEHOLDER*/
/*LIMIT_PLACEHOLDER*/