CREATE ALIAS IF NOT EXISTS READ_BLOB FOR "org.h2.tools.Recover.readBlob";
CREATE ALIAS IF NOT EXISTS READ_CLOB FOR "org.h2.tools.Recover.readClob";
CREATE ALIAS IF NOT EXISTS READ_BLOB_DB FOR "org.h2.tools.Recover.readBlobDb";
CREATE ALIAS IF NOT EXISTS READ_CLOB_DB FOR "org.h2.tools.Recover.readClobDb";
-- pageSize: 2048 writeVersion: 3 readVersion: 3
-- head 1: writeCounter: 95 log 6:0/0 crc 856555548 (ok)
-- head 2: writeCounter: 95 log 6:0/0 crc 856555548 (ok)
-- log 6:0/0
-- page 3: free list 
-- 3 1111111 1111
-- page 4: data leaf (last) parent: 0 table: -1 entries: 11 columns: 6
CREATE TABLE O_M1(C0 VARCHAR, C1 VARCHAR, C2 VARCHAR, C3 VARCHAR, C4 VARCHAR, C5 VARCHAR);
INSERT INTO O_M1 VALUES(0, 0, 0, 11, 'OFF,0,,,false', '0,1,2,3');
INSERT INTO O_M1 VALUES(2, 0, 2, 10, 'OFF,0,,,false', '0,1,2');
INSERT INTO O_M1 VALUES(3, 1, 2, 0, 'OFF,0,,d,false', '0');
INSERT INTO O_M1 VALUES(5, 1, 2, 9, 'OFF,0,,,false', '2');
INSERT INTO O_M1 VALUES(6, 0, 6, 8, 'OFF,0,,,false', '0,1,2,3,4');
INSERT INTO O_M1 VALUES(7, 1, 6, 7, 'OFF,0,,,false', '0,1');
INSERT INTO O_M1 VALUES(9, 1, 6, 6, 'OFF,0,,,false', '4,0');
INSERT INTO O_M1 VALUES(10, 0, 10, 5, 'OFF,0,,,false', '0,1,2');
INSERT INTO O_M1 VALUES(11, 1, 10, 0, 'OFF,0,,d,false', '0');
INSERT INTO O_M1 VALUES(14, 0, 14, 13, 'OFF,0,,,false', '0,1,2,3');
INSERT INTO O_M1 VALUES(15, 1, 14, 12, 'OFF,0,,,false', '0');
-- page 5: data leaf (last) parent: 0 table: 10 entries: 0 columns: 3
-- page 6: b-tree leaf (last) parent: 0 index: 9 entries: 0
-- page 7: b-tree leaf (last) parent: 0 index: 7 entries: 0
-- page 8: data leaf (last) parent: 0 table: 6 entries: 0 columns: 5
-- page 9: b-tree leaf (last) parent: 0 index: 5 entries: 0
-- page 10: data leaf (last) parent: 0 table: 2 entries: 0 columns: 3
-- page 11: data leaf (last) parent: 0 table: 0 entries: 16 columns: 4
CREATE TABLE O_0(C0 VARCHAR, C1 VARCHAR, C2 VARCHAR, C3 VARCHAR);
INSERT INTO O_0 VALUES(1, 0, 6, 'SET CREATE_BUILD 176');
INSERT INTO O_0 VALUES(2, 0, 0, STRINGDECODE('CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOBS(\n    ID BIGINT NOT NULL,\n    BYTE_COUNT BIGINT,\n    TABLE INT\n)\nHIDDEN'));
INSERT INTO O_0 VALUES(3, 0, 1, 'CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_2 ON INFORMATION_SCHEMA.LOBS(ID)');
INSERT INTO O_0 VALUES(4, 0, 5, 'ALTER TABLE INFORMATION_SCHEMA.LOBS ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_2 PRIMARY KEY(ID) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_2');
INSERT INTO O_0 VALUES(5, 0, 1, 'CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_TABLE ON INFORMATION_SCHEMA.LOBS(TABLE)');
INSERT INTO O_0 VALUES(6, 0, 0, STRINGDECODE('CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_MAP(\n    LOB BIGINT NOT NULL,\n    SEQ INT NOT NULL,\n    POS BIGINT,\n    HASH INT,\n    BLOCK BIGINT\n)\nHIDDEN'));
INSERT INTO O_0 VALUES(7, 0, 1, 'CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_3 ON INFORMATION_SCHEMA.LOB_MAP(LOB, SEQ)');
INSERT INTO O_0 VALUES(8, 0, 5, 'ALTER TABLE INFORMATION_SCHEMA.LOB_MAP ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_3 PRIMARY KEY(LOB, SEQ) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_3');
INSERT INTO O_0 VALUES(9, 0, 1, 'CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_MAP_DATA_LOB ON INFORMATION_SCHEMA.LOB_MAP(BLOCK, LOB)');
INSERT INTO O_0 VALUES(10, 0, 0, STRINGDECODE('CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_DATA(\n    BLOCK BIGINT NOT NULL,\n    COMPRESSED INT,\n    DATA BINARY\n)\nHIDDEN'));
INSERT INTO O_0 VALUES(11, 0, 1, 'CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_9 ON INFORMATION_SCHEMA.LOB_DATA(BLOCK)');
INSERT INTO O_0 VALUES(12, 0, 5, 'ALTER TABLE INFORMATION_SCHEMA.LOB_DATA ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_9 PRIMARY KEY(BLOCK) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_9');
INSERT INTO O_0 VALUES(13, 0, 2, 'CREATE USER IF NOT EXISTS SA SALT ''1f34061a4169449d'' HASH ''76fc3993a687fa4f38a79e6d0613b0cf056d042094a54b117a474eae7367f845'' ADMIN');
INSERT INTO O_0 VALUES(14, 0, 0, STRINGDECODE('CREATE CACHED TABLE PUBLIC.TEST(\n    ID VARCHAR2(20) NOT NULL,\n    VALUE1 VARCHAR2(20),\n    VALUE2 VARCHAR2(20),\n    VALUE3 VARCHAR2(20)\n)'));
INSERT INTO O_0 VALUES(15, 0, 1, 'CREATE PRIMARY KEY PUBLIC.PRIMARY_KEY_2 ON PUBLIC.TEST(ID)');
INSERT INTO O_0 VALUES(16, 0, 5, 'ALTER TABLE PUBLIC.TEST ADD CONSTRAINT PUBLIC.PK_TEST PRIMARY KEY(ID) INDEX PUBLIC.PRIMARY_KEY_2');
-- page 12: b-tree leaf (last) parent: 0 index: 15 entries: 2
-- page 13: data leaf (last) parent: 0 table: 14 entries: 2 columns: 4
CREATE TABLE O_14(C0 VARCHAR, C1 VARCHAR, C2 VARCHAR, C3 VARCHAR);
INSERT INTO O_14 VALUES('1', STRINGDECODE('\u5218\u5ddd'), '25', '33');
INSERT INTO O_14 VALUES('2', STRINGDECODE('\u5b59\u6d77\u653f'), '33', '16');
---- Schema ----
SET CREATE_BUILD 176;
CREATE USER IF NOT EXISTS SA SALT '1f34061a4169449d' HASH '76fc3993a687fa4f38a79e6d0613b0cf056d042094a54b117a474eae7367f845' ADMIN;
CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOBS(
    ID BIGINT NOT NULL,
    BYTE_COUNT BIGINT,
    TABLE INT
)
HIDDEN;
CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_MAP(
    LOB BIGINT NOT NULL,
    SEQ INT NOT NULL,
    POS BIGINT,
    HASH INT,
    BLOCK BIGINT
)
HIDDEN;
CREATE CACHED TABLE IF NOT EXISTS INFORMATION_SCHEMA.LOB_DATA(
    BLOCK BIGINT NOT NULL,
    COMPRESSED INT,
    DATA BINARY
)
HIDDEN;
CREATE CACHED TABLE PUBLIC.TEST(
    ID VARCHAR2(20) NOT NULL,
    VALUE1 VARCHAR2(20),
    VALUE2 VARCHAR2(20),
    VALUE3 VARCHAR2(20)
);
INSERT INTO PUBLIC.TEST SELECT * FROM O_14;
DROP TABLE O_M1;
DROP TABLE O_0;
DROP TABLE O_14;
DROP ALIAS READ_BLOB;
DROP ALIAS READ_CLOB;
DROP ALIAS READ_BLOB_DB;
DROP ALIAS READ_CLOB_DB;
CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_2 ON INFORMATION_SCHEMA.LOBS(ID);
CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_TABLE ON INFORMATION_SCHEMA.LOBS(TABLE);
CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_3 ON INFORMATION_SCHEMA.LOB_MAP(LOB, SEQ);
CREATE INDEX IF NOT EXISTS INFORMATION_SCHEMA.INDEX_LOB_MAP_DATA_LOB ON INFORMATION_SCHEMA.LOB_MAP(BLOCK, LOB);
CREATE PRIMARY KEY IF NOT EXISTS INFORMATION_SCHEMA.PRIMARY_KEY_9 ON INFORMATION_SCHEMA.LOB_DATA(BLOCK);
CREATE PRIMARY KEY PUBLIC.PRIMARY_KEY_2 ON PUBLIC.TEST(ID);
ALTER TABLE INFORMATION_SCHEMA.LOBS ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_2 PRIMARY KEY(ID) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_2;
ALTER TABLE INFORMATION_SCHEMA.LOB_MAP ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_3 PRIMARY KEY(LOB, SEQ) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_3;
ALTER TABLE INFORMATION_SCHEMA.LOB_DATA ADD CONSTRAINT IF NOT EXISTS INFORMATION_SCHEMA.CONSTRAINT_9 PRIMARY KEY(BLOCK) INDEX INFORMATION_SCHEMA.PRIMARY_KEY_9;
ALTER TABLE PUBLIC.TEST ADD CONSTRAINT PUBLIC.PK_TEST PRIMARY KEY(ID) INDEX PUBLIC.PRIMARY_KEY_2;
---- Transaction log ----
---- Statistics ----
-- page count: 14, free: 0
-- page data bytes: head 157, empty 9910, rows 2221 (20% full)
-- data leaf 42%, 6 page(s)
-- btree leaf 28%, 4 page(s)
-- free list 7%, 1 page(s)