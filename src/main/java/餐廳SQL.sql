CREATE DATABASE IF NOT EXISTS restaurant;

USE restaurant;





CREATE TABLE IF NOT EXISTS RESTAURANT_MENU (
    ITEM_ID     INT           NOT NULL AUTO_INCREMENT,
    CATEGORY_ID INT           NOT NULL,
    ITEM_NAME   VARCHAR(50)   NOT NULL,
    ITEM_DESC   VARCHAR(200),
    PRICE       DECIMAL(8, 2) NOT NULL,
    IMAGE_URL   VARCHAR(255),
    SORT_ORDER  INT           NOT NULL DEFAULT 0,
    PRIMARY KEY (ITEM_ID),
    CONSTRAINT fk_rm_category FOREIGN KEY (CATEGORY_ID) REFERENCES MENU_CATEGORY (CATEGORY_ID)
);
 
INSERT INTO RESTAURANT_MENU (CATEGORY_ID, ITEM_NAME, ITEM_DESC, PRICE, IMAGE_URL, SORT_ORDER) VALUES
(1, '凱薩沙拉',     '新鮮蘿蔓生菜搭配凱薩醬與帕馬森起司',    180.00, '1.jpg',  1),
(2, '奶油義大利麵', '選用特製奶油醬汁搭配培根與蘑菇',        320.00, '2.jpg',         1),
(3, '南瓜濃湯',     '香濃南瓜搭配鮮奶油與麵包丁',            150.00, '3.jpg',  1),
(4, '提拉米蘇',     '經典義式甜點，濃郁咖啡與馬斯卡彭起司',   220.00, '4.jpg',      1),
(5, '手工檸檬氣泡水','現榨檸檬汁搭配氣泡水與薄荷葉',          120.00, '5.jpg',    1);
     