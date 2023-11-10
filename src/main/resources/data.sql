
-- user
INSERT INTO user (employee_code, name, password, role, icon) VALUES
	(1, '疋田', '123', 'MEMBER', NULL),
	(2, '片川', '456', 'MANAGER', NULL),
	(3, '千田', '678', 'MEMBER', NULL);
--
-- task
INSERT INTO task (employee_code, id, name, progress_rate) VALUES
	(1, 1, 'A機能追加', 28),
	(2, 2, 'B機能改修', 99),
	(3, 3, 'エラー調査', 77);

-- report
INSERT INTO `report` (`id`, `employee_code`, `condition`, `impressions`, `tomorrow_schedule`, `date`, `start_time`, `end_time`, `is_lateness`, `lateness_reason`, `is_left_early`) VALUES
    (1, 1, '不調', '特になし', '開発', '2023-11-06', '09:00:00', '18:00:00', 1, '寝坊', 0),
    (2, 2, '良好', '頑張った', '監督', '2023-11-07', '10:00:00', '19:00:00', 0, NULL, 1),
    (3, 3, '普通', '頑張ったよ', '開発2', '2023-11-08', '11:00:00', '20:00:00', 1, NULL, 0);

-- report_task_link
INSERT INTO report_task_link (report_id, task_id) VALUES
	(2, 2),
	(1, 2),
	(1, 1),
	(1, 3);

