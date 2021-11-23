-- 阿里云数据库密码 C-y-/CF-7)Qo

-- 课题表（每个课题下面有多个表单）
drop table if exists subject;
CREATE TABLE subject
(
    id           bigint PRIMARY KEY AUTO_INCREMENT COMMENT '课题编号',
    subject_name varchar(64) COMMENT '课题名称',
    subject_type varchar(32) COMMENT '研究类型',
    unit         varchar(128) COMMENT '课题牵头单位',
    owner        varchar(16) COMMENT '课题负责人',
    created      datetime default current_timestamp COMMENT '登记日期',
    updated      datetime default current_timestamp
        on update current_timestamp COMMENT '更新日期',
    state        varchar(8) COMMENT '目前状态',
    postscript   varchar(128) COMMENT '备注',
    user_id      bigint comment '用户id',
    INDEX subject_created_index (created),
    INDEX subject_updated_index (updated),
    INDEX subject_name_index (subject_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

-- 表单表（多个表单属于同一课题）（未完成）
drop table if exists form;
CREATE TABLE form
(
    id           bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    form_name    varchar(32) COMMENT '表单名称',
    subject_name varchar(32) COMMENT '课题名称',
    subject_id   bigint      comment '课题id',
    creator      varchar(16) COMMENT '登记人',
    created      datetime default current_timestamp COMMENT '登记时间',
    updated      datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',
    postscript   varchar(128) COMMENT '表单描述',

    INDEX form_name_index (form_name),
    INDEX form_created_index (created),
    INDEX form_updated_index (updated)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

-- 问项表（问项组成了表单）(未完成)
drop table if exists item;
CREATE TABLE item
(
    id         bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    item_name  varchar(32) COMMENT '问项名称',
    title      varchar(32) COMMENT '问项标题',
    options    varchar(128) COMMENT '问项选项',
    type       varchar(32) COMMENT '问项类型',
    creator    varchar(16) comment '登记人',
    created      datetime default current_timestamp COMMENT '登记时间',
    updated      datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',
    user_id bigint comment '用户id',
    INDEX item_name_index (item_name),
    INDEX item_title_index (title),
    index item_user_id_index(user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

-- 问项与表单的关联
create table form_item
(
    id      bigint primary key auto_increment comment '主键',
    form_id varchar(32) comment '表单id',
    item_id varchar(32) comment '问项id',
    creator varchar(32) comment '创建人',
    created datetime default current_timestamp COMMENT '登记时间',
    updated datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',

    index form_id_index (form_id),
    index item_id_index (item_id),
    UNIQUE INDEX (form_id, item_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

-- 问项数据(通过form——id 和item——id来获取)
drop table if exists data;
create table data
(
    id      bigint primary key auto_increment comment '主键',
    form_id varchar(32) comment '表单id',
    item_id varchar(32) comment '问项id',
    data    varchar(512) comment '问项数据',
    created datetime default current_timestamp COMMENT '登记时间',
    updated datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',

    creator varchar(32) comment '登记人',

    UNIQUE INDEX (form_id, item_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;



-- 用户（账号密码邮箱手机号）(完成)
create table user
(
    id       bigint primary key auto_increment comment '主键',
    username varchar(32) comment '用户名',
    nickname varchar(16) comment '昵称',
    password varchar(64) comment '用户密码',
    email    varchar(32) comment '用户邮箱',
    iphone   varchar(15) comment '手机号',

    index user_name_index (username),
    index user_iphone_index (iphone)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

-- 问项与用户关联:(创建用户与问项联系)
create table user_item
(
    id      bigint primary key auto_increment comment '主键',
    user_id varchar(32) comment '表单id',
    item_id varchar(32) comment '问项id',
    created datetime default current_timestamp COMMENT '登记时间',
    updated datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',
    UNIQUE INDEX (user_id, item_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;


-- 课题类型(用户自定义)
drop table if exists user_subject_type;
create table user_subject_type
(
    id      bigint primary key auto_increment comment '主键',
    user_id bigint comment '表单id',
    subject_type varchar(32) comment '课题类型',
    created datetime default current_timestamp COMMENT '登记时间',
    updated datetime default current_timestamp
        on update current_timestamp COMMENT '更新时间',
    UNIQUE INDEX (user_id, subject_type)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1;

# -- 问项类型(用户自定义)
# create table user_item_type
# (
#     id      bigint primary key auto_increment comment '主键',
#     user_id varchar(32) comment '表单id',
#     item_type varchar(32) comment '问项类型',
#     created datetime default current_timestamp COMMENT '登记时间',
#     updated datetime default current_timestamp
#         on update current_timestamp COMMENT '更新时间',
#     UNIQUE INDEX (user_id, item_type)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8
#   AUTO_INCREMENT = 1;


# -- 课题与用户关联:(创建用户与课题联系)
# create table user_subject
# (
#     id      bigint primary key auto_increment comment '主键',
#     user_id varchar(32) comment '用户id',
#     subject_id varchar(32) comment '课题id',
#     created datetime default current_timestamp COMMENT '登记时间',
#     updated datetime default current_timestamp
#         on update current_timestamp COMMENT '更新时间',
#     UNIQUE INDEX (user_id, item_id)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8
#   AUTO_INCREMENT = 1;


# -- 课题与表单关联:(创建表单与课题联系)
# create table subject_form
# (
#     id      bigint primary key auto_increment comment '主键',
#     form_id varchar(32) comment '用户id',
#     subject_id varchar(32) comment '课题id',
#     created datetime default current_timestamp COMMENT '登记时间',
#     updated datetime default current_timestamp
#         on update current_timestamp COMMENT '更新时间',
#     UNIQUE INDEX (user_id, item_id)
# ) ENGINE = InnoDB
#   DEFAULT CHARSET = utf8
#   AUTO_INCREMENT = 1;