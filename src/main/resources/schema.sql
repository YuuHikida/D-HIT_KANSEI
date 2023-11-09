
-- user テーブル定義
CREATE TABLE IF NOT EXISTS user (
    employee_code int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    password char(64) NOT NULL,
    role varchar(255) NOT NULL,
    icon blob,
);

-- task テーブル定義
CREATE TABLE IF NOT EXISTS task (
    id int NOT NULL PRIMARY KEY,
    employee_code int NOT NULL,
    name varchar(255) NOT NULL,
    progress_rate tinyint NOT NULL,
    FOREIGN KEY (employee_code) REFERENCES user(employee_code)
);

-- report_task_link テーブル定義
CREATE TABLE IF NOT EXISTS report_task_link (
    report_id int NOT NULL,
    task_id int NOT NULL,
    FOREIGN KEY (report_id) REFERENCES report(id),
    FOREIGN KEY (task_id) REFERENCES task(id)
);

-- report テーブル定義
CREATE TABLE IF NOT EXISTS report (
  　id int NOT NULL PRIMARY KEY,
    employee_code int NOT NULL,
    condition varchar(10) NOT NULL,
    impressions text NOT NULL,
    tomorrow_schedule text NOT NULL,
    date date NOT NULL,
    start_time time NOT NULL,
    end_time time NOT NULL,
    is_lateness tinyint NOT NULL,
    lateness_reason text,
    is_left_early tinyint NOT NULL,
    FOREIGN KEY (employee_code) REFERENCES user(employee_code)
);
