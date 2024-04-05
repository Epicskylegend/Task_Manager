TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    priority VARCHAR(50),
    category VARCHAR(50)
);


DELIMITER //

FUNCTION add_task(
    task_name VARCHAR(255),
    task_description TEXT
)
RETURNS INT
BEGIN
    DECLARE task_id INT;

    INSERT INTO tasks (name, description)
    VALUES (task_name, task_description);

    SET task_id = LAST_INSERT_ID();

    RETURN task_id;
END//

DELIMITER ;

// category_name and category color, priority as column in database