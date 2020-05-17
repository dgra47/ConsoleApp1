CREATE TABLE tasks(
    Id integer NOT NULL,
    Name VARCHAR(128) NOT NULL,
    Assigned VARCHAR(128) NOT NULL,
    Priority integer NOT NULL,
    Description VARCHAR(128) NOT NULL,
    Completed BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);
