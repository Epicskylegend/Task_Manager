CREATE TABLE Tasks (
    id SERIAL PRIMARY KEY,
    task_name VARCHAR(255),
    task_description TEXT,
    category_name VARCHAR(255),
    category_color VARCHAR(255),
    priority_level INT
);
