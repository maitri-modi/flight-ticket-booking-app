CREATE TABLE IF NOT EXISTS user_profile (
        id     SERIAL NOT NULL PRIMARY KEY,
        email  varchar(50) NOT NULL UNIQUE,
        mobileNumber varchar(10)
    );