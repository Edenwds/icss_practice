CREATE TABLE "TClass"
  (
    "cname" VARCHAR2(50) NOT NULL,
    "cno"   VARCHAR2(18) NOT NULL,
    CONSTRAINT PK_TCLASS PRIMARY KEY ("cno")
  );
CREATE TABLE "TStudent"
  (
    "sname"    VARCHAR2(50) NOT NULL,
    "sno"      VARCHAR2(18) NOT NULL,
    "cno"      VARCHAR2(18),
    "sex"      CHAR(1),
    "address"  VARCHAR2(180),
    "tel"      VARCHAR2(18),
    "birthday" DATE,
    "height"   NUMBER(3),
    "state"    NUMBER(2),
    CONSTRAINT PK_TSTUDENT PRIMARY KEY ("sno")
  );
COMMENT ON column "TStudent"."state"
IS
  '1 --------Õý³£
0--------ÀëÐ£';
  ALTER TABLE "TStudent" ADD CONSTRAINT FK_TSTUDENT_REFERENCE_TCLASS FOREIGN KEY ("cno") REFERENCES "TClass" ("cno");
  CREATE TABLE "TCourse"
    (
      "kname" VARCHAR2(50) NOT NULL,
      "kno"   VARCHAR2(16) NOT NULL,
      CONSTRAINT PK_TCOURSE PRIMARY KEY ("kno")
    );
  CREATE TABLE "TStuCourse"
    (
      "aid" NUMBER(9) NOT NULL,
      "sno" VARCHAR2(18),
      "kno" VARCHAR2(16),
      CONSTRAINT PK_TSTUCOURSE PRIMARY KEY ("aid"),
      CONSTRAINT AK_KEY_2_TSTUCOUR UNIQUE ("sno", "kno")
    );
  ALTER TABLE "TStuCourse" ADD CONSTRAINT FK_TSTUCOUR_REFERENCE_TSTUDENT FOREIGN KEY ("sno") REFERENCES "TStudent" ("sno");
  ALTER TABLE "TStuCourse" ADD CONSTRAINT FK_TSTUCOUR_REFERENCE_TCOURSE FOREIGN KEY ("kno") REFERENCES "TCourse" ("kno");
  CREATE TABLE "User"
    (
      "uname" VARCHAR2(30) NOT NULL,
      "sno"   VARCHAR2(18) NOT NULL,
      "pwd"   VARCHAR2(18) NOT NULL,
      "role"  NUMBER(1) NOT NULL,
      "tel"   VARCHAR2(11),
      CONSTRAINT PK_USER PRIMARY KEY ("sno"),
      CONSTRAINT AK_KEY_2_USER UNIQUE ("uname")
    );
  ALTER TABLE "User" ADD CONSTRAINT FK_USER_REFERENCE_TSTUDENT FOREIGN KEY ("sno") REFERENCES "TStudent" ("sno");