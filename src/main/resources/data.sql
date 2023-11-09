-- 初期データ投入

-- user
INSERT INTO user (employee_code, name, password, role, icon) VALUES
	(1, '疋田', '123', 'MEMBER', NULL),
	(2, '片川', '456', 'MANAGER', NULL),
	(3, '千田', '678', 'MEMBER', NULL);

-- task
INSERT INTO task (employee_code, id, task_name, progress_rate) VALUES
	(1, 1, 'A機能追加', 28),
	(2, 2, 'B機能改修', 99),
	(3, 3, 'エラー調査', 77);

-- report_task_link
INSERT INTO report_task_link (report_id, task_id) VALUES
	(2, 2),
	(1, 2),
	(1, 1),
	(1, 3);

-- report
INSERT INTO report_task_link (report_id, task_id) VALUES
	(2, 2),
	(1, 2),
	(1, 1),
	(1, 3);
