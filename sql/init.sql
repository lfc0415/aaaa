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

CREATE TABLE `poem` (
    `id`            INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `uuid`          VARCHAR(64)  NOT NULL COMMENT '诗歌ID',
    `author`        VARCHAR(32)  NOT NULL COMMENT '作者',
    `author_id`     VARCHAR(64)  NOT NULL DEFAULT '-1' COMMENT '作者ID',
    `title`         VARCHAR(250) NOT NULL COMMENT '标题',
    `like_count`    INT(11)      NOT NULL DEFAULT '0' COMMENT '点赞数',
    `read_count`    INT(11)      NOT NULL DEFAULT '0' COMMENT '阅读数',
    `collect_count` INT(11)      NOT NULL DEFAULT '0' COMMENT '收藏数',
    `text`          TEXT         NOT NULL DEFAULT '' COMMENT '正文',
    `create_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
    `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_poetry_uuid`(`uuid`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4 COMMENT ='诗歌'
