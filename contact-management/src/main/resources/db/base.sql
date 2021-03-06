CREATE OR REPLACE FUNCTION FUNC_INS_SB()
    RETURNS trigger AS
$BODY$
BEGIN
        NEW.MODIFIED_BY = NEW.CREATED_BY;
        NEW.CREATED_DATETIME = NOW();
        NEW.MODIFIED_DATETIME = NOW();
        NEW.VERSION_NO = 0;
        NEW.STATUS = 'ACT';
RETURN NEW;
END
$BODY$
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION FUNC_UPD_SB()
    RETURNS trigger AS
$BODY$
BEGIN
        NEW.CREATED_BY = OLD.CREATED_BY;
        NEW.CREATED_DATETIME = OLD.CREATED_DATETIME;
        NEW.MODIFIED_DATETIME = NOW();
        NEW.VERSION_NO = OLD.VERSION_NO + 1;
RETURN NEW;
END
$BODY$
LANGUAGE PLPGSQL;
