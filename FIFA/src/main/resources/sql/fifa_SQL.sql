club
-- 대륙(Continent) 테이블 생성
CREATE TABLE Continent (
    continent_id INT AUTO_INCREMENT PRIMARY KEY,
    continent_name VARCHAR(100) NOT NULL
);

-- 국가(Country) 테이블 생성
CREATE TABLE Country (
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL,
    continent_id INT,
    FOREIGN KEY (continent_id) REFERENCES Continent(continent_id)
);

-- 구단(Club) 테이블 생성
CREATE TABLE Club (
    club_id INT AUTO_INCREMENT PRIMARY KEY,
    club_name VARCHAR(100) NOT NULL,
    country_id INT,
    club_funds DECIMAL(15, 2),
    FOREIGN KEY (country_id) REFERENCES Country(country_id)
);

-- 선수(Player) 테이블 생성
CREATE TABLE Player (
    player_id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    country_id INT,
    club_id INT,
    position VARCHAR(50),
    market_value DECIMAL(15, 2),
    FOREIGN KEY (country_id) REFERENCES Country(country_id),
    FOREIGN KEY (club_id) REFERENCES Club(club_id)
);

-- 이적시장(TransferMarket) 테이블 생성
CREATE TABLE TransferMarket (
    transfer_id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT,
    previous_club_id INT,
    new_club_id INT,
    transfer_fee DECIMAL(15, 2),
    transfer_date DATE,
    continent_id INT,
    FOREIGN KEY (player_id) REFERENCES Player(player_id),
    FOREIGN KEY (previous_club_id) REFERENCES Club(club_id),
    FOREIGN KEY (new_club_id) REFERENCES Club(club_id),
    FOREIGN KEY (continent_id) REFERENCES Continent(continent_id)
);
