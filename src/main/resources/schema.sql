CREATE TABLE IF NOT EXISTS temperature_setpoint
(
    id          BIGSERIAL PRIMARY KEY,
    fridge_id   VARCHAR(100)     NOT NULL,
    temperature INTEGER          NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_setpoint_fridge_time
    ON temperature_setpoint (fridge_id);