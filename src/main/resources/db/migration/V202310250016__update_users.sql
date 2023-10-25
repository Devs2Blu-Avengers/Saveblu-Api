UPDATE users
SET
  device_token = (CASE
                    WHEN id = 1 THEN 'token1'
                    WHEN id = 2 THEN 'token2'
                    WHEN id = 3 THEN 'token3'
                    WHEN id = 4 THEN 'token4'
                    WHEN id = 5 THEN 'token5'
                    WHEN id = 6 THEN 'token6'
                    WHEN id = 7 THEN 'token7'
                    WHEN id = 8 THEN 'token8'
                    WHEN id = 9 THEN 'token9'
                    WHEN id = 10 THEN 'token10'
                    WHEN id = 11 THEN 'token11'
                    WHEN id = 12 THEN 'token12'
                    WHEN id = 13 THEN 'token13'
                 END),
  last_latitude = (CASE
                    WHEN id = 1 THEN -26.917847
                    WHEN id = 2 THEN -26.913834
                    WHEN id = 3 THEN -26.918288
                    WHEN id = 4 THEN -26.916624
                    WHEN id = 5 THEN -26.914504
                    WHEN id = 6 THEN -26.920180
                    WHEN id = 7 THEN -26.922254
                    WHEN id = 8 THEN -26.923509
                    WHEN id = 9 THEN -26.915568
                    WHEN id = 10 THEN -26.914923
                    WHEN id = 11 THEN -26.918586
                    WHEN id = 12 THEN -26.916349
                    WHEN id = 13 THEN -26.918911
                 END),
  last_longitude = (CASE
                    WHEN id = 1 THEN -49.066316
                    WHEN id = 2 THEN -49.073029
                    WHEN id = 3 THEN -49.077043
                    WHEN id = 4 THEN -49.079906
                    WHEN id = 5 THEN -49.083512
                    WHEN id = 6 THEN -49.086756
                    WHEN id = 7 THEN -49.090992
                    WHEN id = 8 THEN -49.093866
                    WHEN id = 9 THEN -49.068707
                    WHEN id = 10 THEN -49.070475
                    WHEN id = 11 THEN -49.074477
                    WHEN id = 12 THEN -49.081020
                    WHEN id = 13 THEN -49.084325
                 END);
