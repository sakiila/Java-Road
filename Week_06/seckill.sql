create table if not exists seckill.t_goods
(
    id bigint auto_increment comment '主键'
        primary key,
    goods_name varchar(20) not null comment '商品名称',
    goods_price bigint not null comment '商品价格',
    is_on_sale tinyint unsigned default 1 not null comment '是否上架：1：上架，0：下架',
    is_delete tinyint unsigned default 0 not null comment '是否已经删除，1：已经删除 0：未删除',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table if not exists seckill.t_order
(
    id bigint auto_increment comment '主键'
        primary key,
    user_id bigint not null comment '下单用户Id',
    goods_id bigint not null comment '商品Id',
    goods_count bigint not null comment '商品数量',
    is_delete tinyint unsigned default 0 not null comment '是否已经删除，1：已经删除 0：未删除',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
);

create table if not exists seckill.t_user
(
    id bigint auto_increment comment '主键'
        primary key,
    user_name varchar(20) not null comment '用户姓名',
    user_password varchar(20) not null comment '密码',
    is_delete tinyint unsigned default 0 not null comment '是否已经删除，1：已经删除 0：未删除',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    modify_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
);

