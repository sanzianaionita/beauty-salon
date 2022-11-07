CREATE TABLE LOCATION
(
    ID            RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT LOCATION_PK
        PRIMARY KEY,
    STREET        VARCHAR2(256),
    STREET_NUMBER NUMBER,
    POSTAL_CODE   NUMBER
);

CREATE TABLE SALON
(
    ID            RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT SALON_PK
        PRIMARY KEY,
    SALON_NAME    VARCHAR2(256) NOT NULL,
    SALON_PROGRAM VARCHAR2(256),
    LOCATION_ID   RAW(16) NOT NULL,

    CONSTRAINT LOCATION_SALON_FK FOREIGN KEY (LOCATION_ID) REFERENCES LOCATION (ID)
);

CREATE TABLE POSITION
(
    ID                   RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT POSITION_PK
        PRIMARY KEY,
    FUNCTION_NAME        VARCHAR2(256) NOT NULL,
    FUNCTION_DESCRIPTION VARCHAR2(256),
    COR_CODE             NUMBER NOT NULL
);

CREATE TABLE EMPLOYEE
(
    ID              RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT EMPLOYEE_PK
        PRIMARY KEY,
    FIRST_NAME      VARCHAR2(256) NOT NULL,
    LAST_NAME       VARCHAR2(256) NOT NULL,
    WAGE            NUMBER NOT NULL,
    EMPLOYMENT_DATE DATE   NOT NULL,
    POSITION_ID     RAW(16) NOT NULL,
    SALON_ID        RAW(16) NOT NULL,

    CONSTRAINT EMPLOYEE_POSITION_FK FOREIGN KEY (POSITION_ID) REFERENCES POSITION (ID),
    CONSTRAINT EMPLOYEE_SALON_FK FOREIGN KEY (SALON_ID) REFERENCES SALON (ID)
);

CREATE TABLE SERVICE
(
    ID                  RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT SERVICE_PK
        PRIMARY KEY,
    SERVICE_NAME        VARCHAR2(256) NOT NULL,
    SERVICE_PRICE       NUMBER NOT NULL,
    SERVICE_DESCRIPTION VARCHAR2(256)
);

CREATE TABLE SALON_SERVICE
(
    ID         RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT SALON_SERVICE_PK
        PRIMARY KEY,
    SALON_ID   RAW(16) NOT NULL,
    SERVICE_ID RAW(16) NOT NULL,

    CONSTRAINT SALON_SERVICE_SALON_FK FOREIGN KEY (SALON_ID) REFERENCES SALON (ID),
    CONSTRAINT SALON_SERVICE_SERVICE_FK FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE (ID)
);

CREATE TABLE APPOINTMENT
(
    ID                  RAW(16) DEFAULT sys_guid() NOT NULL
        CONSTRAINT APPOINTMENT_PK
        PRIMARY KEY,
    APPOINTMENT_DATE    DATE   NOT NULL,
    CLIENT_FIRST_NAME   VARCHAR2(256) NOT NULL,
    CLIENT_LAST_NAME    VARCHAR2(256) NOT NULL,
    CLIENT_PHONE_NUMBER NUMBER NOT NULL,
    CONFIRMED           NUMBER(1) CHECK (CONFIRMED IN (0,1)) NOT NULL,
    SALON_ID            RAW(16) NOT NULL,
    SERVICE_ID          RAW(16) NOT NULL,
    EMPLOYEES_ID        RAW(16) NOT NULL,

    CONSTRAINT APPOINTMENT_SALON_FK FOREIGN KEY (SALON_ID) REFERENCES SALON (ID),
    CONSTRAINT APPOINTMENT_SERVICE_FK FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE (ID),
    CONSTRAINT APPOINTMENT_EMPLOYEES_FK FOREIGN KEY (EMPLOYEES_ID) REFERENCES EMPLOYEE (ID)
);
