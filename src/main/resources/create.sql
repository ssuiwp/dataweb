-- 课题表（每个课题下面有多个表单）
CREATE TABLE subject (
    id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '课题编号',
    name varchar(16) COMMENT '课题名称',
    type varchar(32) COMMENT '研究类型',
    Unit varchar(64) COMMENT '课题牵头单位',
    owner varchar(16) COMMENT '课题负责人',
    create_time datetime COMMENT '登记日期',
    state varchar(16) COMMENT '目前状态',
    postscript varchar(32) COMMENT '备注',

    INDEX task_id_index(id),
    INDEX create_time_index(create_time),
    INDEX task_name_index(name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- 表单表（多个表单属于同一课题）（未完成）
CREATE TABLE form (
    id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    name varchar(32) COMMENT '表单名称',
    subject_name varchar(32) COMMENT '课题名称',
    creator varchar(16) COMMENT '登记人',
    create_time datetime COMMENT '登记时间',
    postscript datetime COMMENT '表单描述',
    INDEX form_name_index(name),
    INDEX create_time_index(create_time),
    INDEX form_id_index(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- 问项表（问项组成了表单）(未完成)
CREATE TABLE multiItem (
    id bigint PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    name varchar(32) COMMENT '问项名称',
    title varchar(32) COMMENT '问项标题',
    options varchar(16) COMMENT '问项选项',
    type varchar(16) COMMENT '问项类型',
    postscript datetime COMMENT '表单描述',
    INDEX item_name_index(name),
    INDEX item_title_index(title),
    INDEX item_id_index(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- 问项与表单的关联
create table item_id_form_id_relation(
    id bigint primary key auto_increment comment '主键',
    form_id varchar(32) comment '表单',
    item_id varchar(32) comment '问项',
    creator varchar(32) comment '创建人',
    index form_id_index (form_id),
    index item_id_index (item_id),
    UNIQUE INDEX (form_id , item_id),
    index id_index (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- 用户（账号与密码等信息）
create table user(
    id bigint primary key auto_increment comment '主键',
    name varchar(32) comment '用户名',
    password varchar(32) comment '用户密码',
    email varchar(32) comment '用户邮箱',
    iphone integer(11) comment '用户电话',
    creator varchar(5) comment '创建人',
    index user_id_index (id),
    index user_name_index(name),
    index user_iphone_index(iphone),
    index creator_index(creator)
)