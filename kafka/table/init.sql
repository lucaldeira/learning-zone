CREATE TABLE IF NOT EXISTS public.users
(
    id                 SERIAL PRIMARY KEY,
    full_name          VARCHAR(100),
    email              VARCHAR(100),
    birth_date         DATE,
    score              INT,
    country_code       VARCHAR(10),
    preferred_language VARCHAR(10),
    books_read         INT
);
