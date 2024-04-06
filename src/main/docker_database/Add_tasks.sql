CREATE OR REPLACE FUNCTION get_task (
    task_name VARCHAR(255),
    task_description TEXT,
    category_name VARCHAR(255),
    category_color VARCHAR(255),
    priority_level INT
)
RETURNS BOOLEAN AS $$
DECLARE
    task_count INT;
BEGIN
    SELECT COUNT(*)
    INTO task_count
    FROM tasks
    WHERE
        task_name = task_name
        AND task_description = task_description
        AND category_name = category_name
        AND category_color = category_color
        AND priority_level = priority_level;

    RETURN task_count > 0;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_task (
    new_task Task
)
RETURNS INT AS $$
DECLARE
    task_id INT;
BEGIN
    INSERT INTO tasks (task_name, task_description, category_name, category_color, priority_level)
    VALUES (new_task.name, new_task.description, new_task.category_name, new_task.category_color, new_task.priority_level)
    RETURNING id INTO task_id;

    RETURN task_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION edit_task (
    task_id INT,
    new_task Task
)
RETURNS VOID AS $$
BEGIN
    UPDATE tasks
    SET
        task_name = new_task.name,
        task_description = new_task.description,
        category_name = new_task.category_name,
        category_color = new_task.category_color,
        priority_level = new_task.priority_level
    WHERE id = task_id;
END;
$$ LANGUAGE plpgsql;
