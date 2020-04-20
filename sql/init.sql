CREATE TABLE user (
    id            INT(11)     NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    account       VARCHAR(32) NOT NULL DEFAULT '' COMMENT '账号',
    username      VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
    password      VARCHAR(32) NOT NULL DEFAULT '' COMMENT '密码',
    mobile_phone  VARCHAR(16) NOT NULL DEFAULT '' COMMENT '手机号',
    email_address VARCHAR(64) NOT NULL DEFAULT '',
    gender        CHAR(1)     NOT NULL DEFAULT 'M',
    birth_date    DATETIME             DEFAULT '1990-01-01 00:00:00',
    address       VARCHAR(250)         DEFAULT '',
    role          VARCHAR(16) NOT NULL DEFAULT '' COMMENT '角色',
    last_login    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
    create_time   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
    update_time   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY idx_account(ACCOUNT)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';
