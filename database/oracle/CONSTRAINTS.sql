--------------------------------------------------------
--  Constraints for Table ACCOUNT
--------------------------------------------------------

  ALTER TABLE "DBUSER"."ACCOUNT" ADD CONSTRAINT "ACCOUNT_CHK_ACCOUNT_TYPE" CHECK (ACCOUNT_TYPE IN ('FREE', 'PREMIUM')) ENABLE
  ALTER TABLE "DBUSER"."ACCOUNT" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ACCOUNT" ADD CONSTRAINT "ACCOUNT_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "DBUSER"."ACCOUNT" MODIFY ("ACCOUNT_NUMBER" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ACCOUNT" ADD CONSTRAINT "ACCOUNT_UK_ACCOUNT_NUM" UNIQUE ("ACCOUNT_NUMBER") ENABLE
--------------------------------------------------------
--  Constraints for Table ADDRESS
--------------------------------------------------------

  ALTER TABLE "DBUSER"."ADDRESS" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ADDRESS" MODIFY ("ADDRESS_TYPE" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ADDRESS" MODIFY ("PRIMARY_ADDRESS_LINE" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ADDRESS" MODIFY ("CITY_NAME" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ADDRESS" MODIFY ("STATE_ID" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."ADDRESS" ADD CONSTRAINT "ADDRESS_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "DBUSER"."ADDRESS" ADD CONSTRAINT "ADDRESS_UK1" UNIQUE ("ADDRESS_TYPE", "CUSTOMER_ID") ENABLE
--------------------------------------------------------
--  Constraints for Table COUNTRY
--------------------------------------------------------

  ALTER TABLE "DBUSER"."COUNTRY" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."COUNTRY" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."COUNTRY" MODIFY ("CODE" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."COUNTRY" ADD CONSTRAINT "COUNTRY_PK" PRIMARY KEY ("ID") ENABLE
--------------------------------------------------------
--  Constraints for Table STATE
--------------------------------------------------------

  ALTER TABLE "DBUSER"."STATE" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."STATE" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."STATE" MODIFY ("CODE" NOT NULL ENABLE)
  ALTER TABLE "DBUSER"."STATE" ADD CONSTRAINT "STATE_PK" PRIMARY KEY ("ID") ENABLE
  ALTER TABLE "DBUSER"."STATE" MODIFY ("COUNTRY_ID" NOT NULL ENABLE)