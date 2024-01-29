INSERT INTO club (name, country_id, funds,img) 
SELECT 'real_madrid', c.id, 10000000000000,'/images/real_madrid.jpg'
FROM country c
WHERE c.name = 'Spain';


-- fc_barcelona
INSERT INTO club (name, country_id, funds, img)
SELECT 'fc_barcelona', c.id, 2000000000000,'/images/fc_barcelona.jpg'
FROM country c
WHERE c.name = 'Spain';

-- atletico_madrid
INSERT INTO club (name, country_id, funds, img)
SELECT 'atletico_madrid', c.id, 6000000000000,'/images/atletico_madrid.jpg'
FROM country c
WHERE c.name = 'Spain';

-- Sevilla
INSERT INTO club (name, country_id, funds,img)
SELECT 'Sevilla', c.id, 5000000000000, '/images/Sevilla.jpg'
FROM country c
WHERE c.name = 'Spain';
-- Rcd Mallorca
INSERT INTO club (name,country_id,funds,img) SELECT 'Rcd Mallorca',c.id,5000000000000, '/images/Rcd Mallorca.jpg' FROM country c WHERE c.name='Spain';
-- Athletic Club
INSERT INTO club (name,country_id,funds,img) SELECT 'Athletic Club',c.id,7000000000000, '/images/Athletic Club.jpg' FROM country c WHERE c.name='Spain';
-- Valencia CF
INSERT INTO club (name,country_id,funds,img) SELECT 'Valencia CF',c.id,4000000000000, '/images/Valencia CF.jpg' FROM country c WHERE c.name='Spain';

-- 잉글랜드 국가번호 153
-- 맨유, 맨시티, 리버풀,  첼시, 아스날, 토트넘, 에버튼, 뉴캐슬 
INSERT INTO club (name,country_id,funds,img) SELECT 'manchester_united',c.id,10000000000000, '/images/manchester_united.jpg' FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img)  SELECT 'manchester_city', c.id, 14000000000000, '/images/manchester_city.jpg' FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img) SELECT 'liverpool',c.id, 5000000000000, '/images/liverpool.jpg'  FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img) SELECT 'chelsea', c.id, 13000000000000, '/images/chelsea.jpg'  FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img) SELECT 'arsenal', c.id, 5000000000000,  '/images/arsenal.jpg'  FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img)  SELECT 'tottenham',c.id, 20000000000000,'/images/tottenham.jpg'  FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img) SELECT 'everton', c.id, 50000000000000,  '/images/everton.jpg'  FROM country c WHERE c.name='England';
INSERT INTO club (name,country_id,funds,img) SELECT 'newcastle',c.id, 15000000000000, '/images/newcastle.jpg'  FROM country c WHERE c.name='England';

-- 독일 국가번호 65
-- 뮌헨, 도르트문트
INSERT INTO club (name,country_id,funds, img)  SELECT 'bayern_munchen',c.id,8000000000000,'/images/bayern_munchen.jpg' FROM country c WHERE c.name='Germany';
INSERT INTO club (name,country_id,funds, img) SELECT 'Dortmund',c.id,70000000000000, '/images/Dortmund.jpg' FROM country c WHERE c.name='Germany';
INSERT INTO club (name,country_id,funds, img) SELECT 'RB Leipzig',c.id,30000000000000, '/images/RB Leipzig.jpg' FROM country c WHERE c.name='Germany';
INSERT INTO club (name,country_id,funds, img) SELECT 'Bayer 04 Leverkusen',c.id,60000000000000, '/images/Bayer 04 Leverkusen.jpg' FROM country c WHERE c.name='Germany';

-- 프랑스 국가번호 61
-- 파리생제르망, 마르세유
INSERT INTO club (name,country_id,funds, img) SELECT 'paris_saint_germain',c.id,11000000000000,'/images/paris_saint_germain.jpg' FROM country c WHERE c.name='France';
INSERT INTO club (name,country_id,funds, img) SELECT 'marseilles',c.id,6000000000000, '/images/marseilles.jpg' FROM country c WHERE c.name='France';
INSERT INTO club (name,country_id,funds, img) SELECT 'AS Monaco FC',c.id,7000000000000, '/images/AS Monaco FC.jpg' FROM country c WHERE c.name='France';
INSERT INTO club (name,country_id,funds, img) SELECT 'LOSC Lille',c.id,2000000000000, '/images/LOSC Lille.jpg' FROM country c WHERE c.name='France';