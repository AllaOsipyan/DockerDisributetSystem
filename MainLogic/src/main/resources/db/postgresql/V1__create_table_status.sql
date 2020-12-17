CREATE TABLE IF NOT EXISTS linksSchema.status (
    id SERIAL PRIMARY KEY,
    status INT,
    url TEXT NOT NULL
);