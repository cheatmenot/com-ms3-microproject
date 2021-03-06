DROP TABLE IF EXISTS TBL_IDENTIFICATION;

DROP SEQUENCE IF EXISTS SEQ_IDENTIFICATION_ID;
CREATE SEQUENCE IF NOT EXISTS SEQ_IDENTIFICATION_ID START 1 INCREMENT 1 NO MAXVALUE CACHE 1;

CREATE TABLE TBL_IDENTIFICATION(
	ID BIGINT NOT NULL DEFAULT NEXTVAL('SEQ_IDENTIFICATION_ID'),
	FIRST_NAME VARCHAR(64) NOT NULL,
	LAST_NAME VARCHAR(64) NOT NULL,
	DOB DATE NOT NULL,
	GENDER VARCHAR(1) NOT NULL,
	TITLE VARCHAR(64) NOT NULL,
	
	
	STATUS VARCHAR(3),
	CREATED_BY VARCHAR(32)NOT NULL,
	CREATED_DATETIME TIMESTAMP(6) WITH TIME ZONE,
	MODIFIED_BY VARCHAR(32)NOT NULL,
	MODIFIED_DATETIME TIMESTAMP(6) WITH TIME ZONE,
	VERSION_NO SMALLINT,
	SOURCE VARCHAR(32)
);

DROP TRIGGER IF EXISTS TRG_INS_IDENTIFICATION on TBL_IDENTIFICATION;
CREATE TRIGGER TRG_INS_IDENTIFICATION BEFORE INSERT ON TBL_IDENTIFICATION
	FOR EACH ROW EXECUTE PROCEDURE FUNC_INS_SB();
	
DROP TRIGGER IF EXISTS TRG_UPD_IDENTIFICATION on TBL_IDENTIFICATION;
CREATE TRIGGER TRG_UPD_IDENTIFICATION BEFORE UPDATE ON TBL_IDENTIFICATION
	FOR EACH ROW EXECUTE PROCEDURE FUNC_UPD_SB();

