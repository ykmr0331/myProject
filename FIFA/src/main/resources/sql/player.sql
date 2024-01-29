-- 루카모드리치
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Luka Modric', (SELECT id FROM country WHERE name = 'Croatia'),'Croatia',   1,'real_madrid',  'midfielder', 100000000000,'/images/luka_modric.jpg');
-- 리오넬메시
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Lionel Messi', (SELECT id FROM country WHERE name = 'Argentina'),'Argentina',   2,'fc_barcelona',  'forward', 200000000000,'/images/Lionel Messi.jpg');

-- 크리스티아노 호날두
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Cristiano Ronaldo', (SELECT id FROM country WHERE name = 'Portugal'),'Portugal',   1,'real_madrid',  'forward', 250000000000,'/images/Cristiano Ronaldo.jpg');

-- 데브라이너
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Kevin De Bruyne', (SELECT id FROM country WHERE name = 'Belgium'),'Belgium',   9,'manchester_city', 'midfielder', 300000000000,'/images/Kevin De Bruyne.jpg');


-- 마커스 래시포드
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Marcus Rashford', (SELECT id FROM country WHERE name = 'England'),'England',   9,'manchester_united', 'forward', 300000000000,'/images/Marcus Rashford.jpg');

-- 모하메드 살라
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Mohamed Salah', (SELECT id FROM country WHERE name = 'Egypt'),'Egypt',   10,'liverpool',  'forward', 280000000000,'/images/Mohamed Salah.jpg');

-- 티아구 실바
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Thiago Silva', (SELECT id FROM country WHERE name = 'Brazil'),'Brazil',   11,'chelsea',  'defender', 100000000000,'/images/Thiago Silva.jpg');

-- 헤수스 나바스
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value,img)
values 				('Jesus Navas', (SELECT id FROM country WHERE name = 'Spain'),'Spain',   4,'Sevilla',  'forward', 130000000000, '/images/Jesus Navas.jpg');

-- 앙투앙 그리즈만
INSERT INTO player (name, country_id, country_name, club_id,club_name,  position, market_value, img)
values 				('Antoine Griezmann', (SELECT id FROM country WHERE name = 'France'),'France',   3,'atletico_madrid',  'forward', 18000000000,'/images/Antoine Griezmann.jpg');