CREATE TABLE client
(
    id       UUID NOT NULL,
    name     VARCHAR(255),
    document VARCHAR(255),
    email    VARCHAR(255),
    phone    VARCHAR(255),
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE equipment
(
    id            UUID NOT NULL,
    model         VARCHAR(255),
    manufacturer  VARCHAR(255),
    serial_number VARCHAR(255),
    comments      VARCHAR(255),
    CONSTRAINT pk_equipment PRIMARY KEY (id)
);

CREATE TABLE service_order
(
    id           UUID    NOT NULL,
    pulic_id     INTEGER NOT NULL,
    client_id    UUID,
    equipment_id UUID,
    CONSTRAINT pk_serviceorder PRIMARY KEY (id)
);

ALTER TABLE service_order
    ADD CONSTRAINT FK_SERVICEORDER_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE service_order
    ADD CONSTRAINT FK_SERVICEORDER_ON_EQUIPMENT FOREIGN KEY (equipment_id) REFERENCES equipment (id);