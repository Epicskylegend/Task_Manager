DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Categories;

CREATE TABLE IF NOT EXISTS Tasks(
    id SERIAL PRIMARY KEY,
    task_name VARCHAR(255),
    task_description TEXT,
    category_name VARCHAR(255),
    category_color VARCHAR(255),
    priority_level INT,
    task_completion boolean
);

CREATE TABLE IF NOT EXISTS Categories (
    category_name VARCHAR(255),
    category_color VARCHAR(255)
    );


DO $$
BEGIN

    IF (SELECT count(*) FROM information_schema.columns WHERE table_name = 'tasks') < 7 THEN

        ALTER TABLE Tasks ADD COLUMN task_completion BOOLEAN;
        RAISE NOTICE 'task_completion column succesfully added.';
    END IF;
END $$;



