
create table  sales_flex_discount_data (
DEVICE_ID           INTEGER    PRIMARY KEY,
DEVICE_NAME                  VARCHAR2(100)NOT NULL  ,
TERM_IN_MONTHS             NUMBER(11) NOT NULL ,    
DISCOUNT_PERC                NUMBER(11)  NOT NULL   ,
DISCOUNT_AUTH_LEVEL          VARCHAR2(50) NOT NULL  ,
PARENT_DEVICE_ID              INTEGER,
CONSTRAINT FK_sales_flex_discount_data FOREIGN KEY (PARENT_DEVICE_ID) REFERENCES sales_flex_discount_data (DEVICE_ID)
)

create table sales_flex_term_discount 
(
ID	  INTEGER PRIMARY KEY,
TERM_IN_MONTHS             NUMBER(11) NOT NULL ,    
DISCOUNT_PERC                NUMBER(11)  NOT NULL,
DEVICE_ID           INTEGER ,
CONSTRAINT FK_sales_flex_term_discount FOREIGN KEY (DEVICE_ID) REFERENCES sales_flex_discount_data (DEVICE_ID)
)

--------------------------------------------------------
--  File created - Wednesday-July-19-2017   
--------------------------------------------------------
REM INSERTING into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA
SET DEFINE OFF;
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (1,'Host Devices',24,0,'AE',null);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (2,'ATT Managed VNF Devices',24,36,'AE',null);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (3,'Router VNF',24,36,'AE',2);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (4,'Firewall VNF',24,36,'AE',2);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (5,'WANX VNF',24,36,'AE',2);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (6,'Custom Managed VNF Devices',24,10,'AE',null);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (7,'Router VNF',24,10,'AE',6);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (8,'Firewall VNF',24,10,'AE',6);
Insert into ADOPT_SB2.SALES_FLEX_DISCOUNT_DATA (DEVICE_ID,DEVICE_NAME,TERM_IN_MONTHS,DISCOUNT_PERC,DISCOUNT_AUTH_LEVEL,PARENT_DEVICE_ID) values (9,'WANX VNF',24,10,'AE',6);
REM INSERTING into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT
SET DEFINE OFF;
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (1,24,0,1);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (2,24,36,2);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (3,24,36,3);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (4,24,36,4);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (5,24,36,5);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (6,24,10,6);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (7,24,10,7);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (8,24,10,8);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (9,24,10,9);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (10,36,33,1);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (11,48,42,1);
Insert into ADOPT_SB2.SALES_FLEX_TERM_DISCOUNT (ID,TERM_IN_MONTHS,DISCOUNT_PERC,DEVICE_ID) values (12,60,49,1);
