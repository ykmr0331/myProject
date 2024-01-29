
INSERT INTO transfer_market (create_date,new_club_id, player_id, previous_club_id, transfer_fee) 
VALUES 					
-- 메시  바르셀로나에서 아르헨티나로 
(NOW(),	(SELECT id FROM club where name = 'real_madrid'),(SELECT id FROM player where name = 'Lionel Messi'),
															(SELECT id FROM club where name = 'fc_barcelona'),200000000000),
-- 모드리치 레알마드리드에서 바르셀로나로
(NOW(),	(SELECT id FROM club where name = 'fc_barcelona'),(SELECT id FROM player where name = 'Luka Modric'),
															(SELECT id FROM club where name = 'real_madrid'),200000000000),
-- 호날두  레알마드리드에서 맨시티로
(NOW(),	(SELECT id FROM club where name = 'manchester_city'),(SELECT id FROM player where name = 'Cristiano Ronaldo'),
															(SELECT id FROM club where name = 'real_madrid'),200000000000);