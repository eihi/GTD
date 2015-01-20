# Begin - Actions 
DROP PROCEDURE IF EXISTS CreateAction;

DELIMITER $$
CREATE PROCEDURE CreateAction(IN in_description VARCHAR(45), IN in_notes TEXT, IN in_action_date DATE, IN in_statuschange_date DATE, IN in_done TINYINT(1), IN in_projects_id INT(11), IN in_context_id INT(11), IN in_statuses_id INT(11))
BEGIN
	INSERT INTO actions (description, notes, action_date, statuschange_date, done, projects_id, context_id, statuses_id)
	VALUES (in_description, in_notes, in_action_date, in_statuschange_date, in_done, in_projects_id, in_context_id, in_statuses_id);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS UpdateAction;

DELIMITER $$
CREATE PROCEDURE UpdateAction(IN in_id INT(11), IN in_description VARCHAR(45), IN in_notes TEXT, IN in_action_date DATE, IN in_statuschange_date DATE, IN in_done TINYINT(1), IN in_projects_id INT(11), IN in_context_id INT(11), IN in_statuses_id INT(11))
BEGIN
	UPDATE actions 
	SET description = in_description, notes = in_notes, action_date = in_action_date, statuschange_date = in_statuschange_date, done = in_done, projects_id = in_projects_id, context_id = in_context_id, statuses_id = in_statuses_id
	WHERE id = in_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS DeleteAction;

DELIMITER $$
CREATE PROCEDURE DeleteAction(IN in_id INT(11))
BEGIN
	DELETE FROM actions WHERE id = in_id;
END $$
DELIMITER ;
# End - Actions


# Begin - Thoughts
DROP PROCEDURE IF EXISTS CreateThought;

DELIMITER $$
CREATE PROCEDURE CreateThought(IN in_notes TEXT)
BEGIN
	INSERT INTO thoughts (notes)
	VALUES (in_notes);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS UpdateThought;

DELIMITER $$
CREATE PROCEDURE UpdateThought(IN in_id INT(11), IN in_notes TEXT)
BEGIN
	UPDATE thoughts 
	SET notes = in_notes
	WHERE id = in_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS DeleteThought;

DELIMITER $$
CREATE PROCEDURE DeleteThought(IN in_id INT(11))
BEGIN
	DELETE FROM thoughts WHERE id = in_id;
END $$
DELIMITER ;
# End - Thoughts


# Begin - Context
DROP PROCEDURE IF EXISTS CreateContext;

DELIMITER $$
CREATE PROCEDURE CreateContext(IN in_name VARCHAR(45))
BEGIN
	INSERT INTO context (context.name)
	VALUES (in_name);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS UpdateContext;

DELIMITER $$
CREATE PROCEDURE UpdateContext(IN in_id INT(11), IN in_name VARCHAR(45))
BEGIN
	UPDATE context 
	SET context.name = in_name
	WHERE id = in_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS DeleteContext;

DELIMITER $$
CREATE PROCEDURE DeleteContext(IN in_id INT(11))
BEGIN
	DELETE FROM context WHERE id = in_id;
END $$
DELIMITER ;
# End - Context


# Begin - Projects
DROP PROCEDURE IF EXISTS CreateProject;

DELIMITER $$
CREATE PROCEDURE CreateProject(IN in_name VARCHAR(45), IN in_notes TEXT)
BEGIN
	INSERT INTO projects (projects.name, notes)
	VALUES (in_name, in_notes);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS UpdateProject;

DELIMITER $$
CREATE PROCEDURE UpdateProject(IN in_id INT(11), IN in_name VARCHAR(45), IN in_notes TEXT)
BEGIN
	UPDATE projects 
	SET projects.name = in_name, notes = in_notes
	WHERE id = in_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS DeleteProject;

DELIMITER $$
CREATE PROCEDURE DeleteProject(IN in_id INT(11))
BEGIN
	DELETE FROM projects WHERE id = in_id;
END $$
DELIMITER ;
# End - Projects


# Begin - Statuses
DROP PROCEDURE IF EXISTS CreateStatus;

DELIMITER $$
CREATE PROCEDURE CreateStatus(IN in_name VARCHAR(45))
BEGIN
	INSERT INTO statuses (statuses.name)
	VALUES (in_name);
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS UpdateStatus;

DELIMITER $$
CREATE PROCEDURE UpdateStatus(IN in_id INT(11), IN in_name VARCHAR(45))
BEGIN
	UPDATE statuses 
	SET statuses.name = in_name
	WHERE id = in_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS DeleteStatus;

DELIMITER $$
CREATE PROCEDURE DeleteStatus(IN in_id INT(11))
BEGIN
	DELETE FROM statuses WHERE id = in_id;
END $$
DELIMITER ;
# End - Statuses